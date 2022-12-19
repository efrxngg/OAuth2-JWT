package com.empresax.security.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "user_token")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserTokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "token_id", updatable = false, nullable = false, columnDefinition = "varchar(16) not null")
    private String tokenId;

    @JoinColumn(name = "user_fk", referencedColumnName = "user_id", columnDefinition = "varchar(16) not null")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @Basic(optional = false)
    @NotNull(message = "Refresh Token is requiered!")
    @Column(name = "refresh_token")
    private String refreshToken;

}