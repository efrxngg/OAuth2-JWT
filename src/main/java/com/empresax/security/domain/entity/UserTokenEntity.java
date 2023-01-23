package com.empresax.security.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.util.UUID;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user_token")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserTokenEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "token_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private UUID tokenId;

    @JoinColumn(name = "user_fk")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @Basic(optional = false)
    @NotNull(message = "Refresh Token is requiered!")
    @Column(name = "refresh_token")
    private String refreshToken;

}