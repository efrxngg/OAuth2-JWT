package com.empresax.security.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String msg;
    private String path;

}
