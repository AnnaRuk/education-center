package de.ait.ec.validation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Schema(name = "ValidationErrors", description = "about error of validation")
public class ValidationErrorsDto {


    @Schema(description = "list of validation errors")
    private List<ValidationErrorDto> errors;
}
