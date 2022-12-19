package com.empresax.security.repository;

import com.empresax.security.domain.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserTokenRepository extends JpaRepository<UserTokenEntity, String> {

    Optional<UserTokenEntity> findByRefreshToken(String refreshToken);

    Optional<UserTokenEntity> deleteByUserUserId(String userId);

}
