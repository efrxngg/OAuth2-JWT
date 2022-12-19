package com.empresax.security.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignedInUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String refreshToken;
    private String accessToken;
    private String username;
    private String userId;
}
