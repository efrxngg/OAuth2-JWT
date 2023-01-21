package com.empresax.security.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
    @Type(type = "uuid-char")
    private UUID tokenId;

    @JoinColumn(name = "user_fk")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @Basic(optional = false)
    @NotNull(message = "Refresh Token is requiered!")
    @Column(name = "refresh_token")
    private String refreshToken;

}