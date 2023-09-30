package de.ait.ec.dto;


import de.ait.ec.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Schema(name = "User", description = "Information about an User")
public class UserDto {

    @Schema(description = "id", example = "1")
    private Long id;

    @Schema(description = "Name", example = "Anna")
    private String firstName;
    @Schema(description = "Last name", example = "Bieliaieva")
    private String lastName;
    @Schema(description = "email", example = "anna22@gmail.com")
    private String email;
    @Schema(description = "role", example = "USER")
    private String role;

    public static UserDto from(User user){
        return UserDto.builder().id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }

}
