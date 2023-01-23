package com.empresax.security.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "The refresh token cannot be null")
    @NotBlank(message = "The refresh token cannot be blank")
    private String refreshToken;

}
