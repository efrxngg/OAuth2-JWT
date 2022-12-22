package com.empresax.security.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInReq implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "The username or email cannot be null")
    @NotBlank(message = "The username or email cannot be blank")
    private String username;

    @NotNull(message = "The password cannot be null")
    @NotBlank(message = "The password cannot be blank")
    private String password;

}
