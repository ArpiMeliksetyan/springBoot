package com.example.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UsernameCodeDto {

    @NotBlank
    private String username;

    @NotBlank
    private String code;
}
