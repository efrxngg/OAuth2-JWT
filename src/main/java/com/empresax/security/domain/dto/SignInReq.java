package com.empresax.security.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SignInReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
}
