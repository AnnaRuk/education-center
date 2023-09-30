package de.ait.ec.controllers;


import de.ait.ec.dto.NewUserDto;
import de.ait.ec.dto.StandardResponseDto;
import de.ait.ec.dto.UserDto;
import de.ait.ec.services.UserService;
import de.ait.ec.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController /// zamenjaet annotation @ResponseBody
@RequestMapping("/api/users")
@Tags(value = {
        @Tag(name = "Users")
})
public class UsersController {

    private final UserService userService;

    @Operation(summary = "Регистрация пользователя", description = "Доступно всем. По умолчанию роль - USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Пользователь зарегистрирован",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Ошибка валидации",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),
            @ApiResponse(responseCode = "409",
                    description = "Пользователь с таким email уже есть",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class))),
    })

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid NewUserDto newUserDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(newUserDto));
    }

}
