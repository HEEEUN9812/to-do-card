package com.sparta.todocard.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SignupRequestDto {

    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]+$")
    @NotBlank
    private String username;

    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @NotBlank
    private String password;

}
