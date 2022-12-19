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
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

        if (userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()) > 0)
            throw new GenericAlreadyExistsException("Use diferent username or email");

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
                .authorities(List.of(user.getRole()))
                .build());
        return SignedInUser.builder()
                .userId(user.getUserId().toString())
                .username(user.getUsername())
                .accessToken(token)
                .build();
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        if (Strings.isBlank(username))
            throw new UsernameNotFoundException("Invalid User");

        final String uname = username.trim();

        return userRepository.findUserByUsername(uname).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found", uname)));
    }

    @Override
    public SignedInUser getSignedInUser(UserEntity userEntity) {
        userTokenRepository.deleteByUserFk(userEntity.getUserId());
        return createSignedUserWithRefreshToken(userEntity);
    }

    @Override
    public Optional<SignedInUser> getAccessToken(RefreshToken refreshToken) {
        var userTokenEntity = userTokenRepository.findByRefreshToken(refreshToken.getRefreshToken())
                .orElseThrow(() -> new InvalidRefreshToken("Invalid Token"));

        var signedInUser = createSignedUserWithRefreshToken(userTokenEntity.getUserFk());
        signedInUser.setRefreshToken(refreshToken.getRefreshToken());

        return Optional.of(signedInUser);
    }

    @Override
    public void removeRefreshToken(RefreshToken refreshToken) {
        userTokenRepository.findByRefreshToken(refreshToken.getRefreshToken()).ifPresentOrElse(
                userTokenRepository::delete,
                () -> {throw new InvalidRefreshToken("Invalid token");}
        );
    }

    private String createRefreshToken(UserEntity user) {
        String token = RandomStringUtils.randomAlphanumeric(128);
        userTokenRepository.save(new UserTokenEntity(null, user, token));
        return token;
    }

}
