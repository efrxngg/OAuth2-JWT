package com.empresax.security.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "user_token")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserTokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "token_id")
    private UUID tokenId;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userFk;

    @Column(name = "refreshToken")
    @Basic(optional = false)
    @NotNull(message = "Refresh Token is requiered!")
    private String refreshToken;

}