package com.empresax.security.service;

import com.empresax.security.domain.dto.RefreshToken;
import com.empresax.security.domain.dto.SignedInUser;
import com.empresax.security.domain.entity.UserEntity;

import java.util.Optional;

public interface IUserService {

    Optional<SignedInUser> createUser(UserEntity user);

    UserEntity findUserByUsername(String username);

    SignedInUser getSignedInUser(UserEntity userEntity);

    Optional<SignedInUser> getAccessToken(RefreshToken refreshToken);

    void removeRefreshToken(RefreshToken refreshToken);


}
