package com.empresax.security.repository;

import com.empresax.security.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findUserByUsername(String username);

    @Query(value = "select * from user_entity where username = :option or email = :option", nativeQuery = true)
    Optional<UserEntity> findByUsernameOrEmail(@Param("option") String option);

    boolean existsByUsernameOrEmail(String username, String email);

}
