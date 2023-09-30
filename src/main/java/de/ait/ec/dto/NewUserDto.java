package de.ait.ec.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class NewUserDto {

    @Email
    @NotNull
    @Schema(description = "email", example = "anna22@gmail.com")
    private String email;

    @NotNull
    @Schema(description = "password", example = "Qwerty007!")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    private String password;

}
