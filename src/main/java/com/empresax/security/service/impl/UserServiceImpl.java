package com.empresax.security.service.impl;

import com.empresax.security.domain.dto.RefreshToken;
import com.empresax.security.domain.dto.SignedInUser;
import com.empresax.security.domain.entity.UserEntity;
import com.empresax.security.domain.entity.UserTokenEntity;
import com.empresax.security.exception.GenericAlreadyExistsException;
import com.empresax.security.exception.InvalidRefreshToken;
import com.empresax.security.repository.IUserRepository;
import com.empresax.security.repository.IUserTokenRepository;
import com.empresax.security.security.JwtManager;
import com.empresax.security.service.IUserService;
import com.empresax.security.util.SecurityRandomID;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtManager jwtManager;
    private IUserTokenRepository userTokenRepository;

    @Override
    @Transactional
    public Optional<SignedInUser> createUser(UserEntity user) {

        if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail()))
            throw new GenericAlreadyExistsException("Use diferent username or email");

        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var userEntity = userRepository.save(user);

        return Optional.of(createSignedUserWithRefreshToken(userEntity));
    }

    private SignedInUser createSignedUserWithRefreshToken(UserEntity userEntity) {
        var signeduser = createSignedInUser(userEntity);
        signeduser.setRefreshToken(createRefreshToken(userEntity));
        return signeduser;
    }

    private SignedInUser createSignedInUser(UserEntity user) {
        var token = jwtManager.create(User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Objects.nonNull(user.getRole()) ? user.getRole().name() : "")
                .build());
                
        return SignedInUser.builder()
                .userId(user.getId().toString())
                .username(user.getUsername())
                .accessToken(token)
                .build();
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        final String uname = username.trim();

        return userRepository.findByUsernameOrEmail(uname).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username or Email %s not found", uname)));
    }

    @Override
    @Transactional
    public SignedInUser getSignedInUser(UserEntity userEntity) {
        userTokenRepository.deleteByUserId(userEntity.getId());
        return createSignedUserWithRefreshToken(userEntity);
    }

    @Override
    @Transactional
    public Optional<SignedInUser> getAccessToken(RefreshToken refreshToken) {
        var userTokenEntity = userTokenRepository.findByRefreshToken(refreshToken.getRefreshToken())
                .orElseThrow(InvalidRefreshToken::new);

        var signedInUser = createSignedInUser(userTokenEntity.getUser());
        signedInUser.setRefreshToken(refreshToken.getRefreshToken());

        return Optional.of(signedInUser);
    }

    @Override
    public void removeRefreshToken(RefreshToken refreshToken) {
        userTokenRepository.findByRefreshToken(refreshToken.getRefreshToken()).ifPresentOrElse(
                userTokenRepository::delete,
                () -> {throw new InvalidRefreshToken();}
        );
    }

    private String createRefreshToken(UserEntity user) {
        String token = SecurityRandomID.generate(128);
        userTokenRepository.save(new UserTokenEntity(null, user, token));
        return token;
    }

}
