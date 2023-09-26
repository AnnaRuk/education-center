package de.ait.ec.validation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Schema(name = "ValidationError", description = "about error of validation")
public class ValidationErrorDto {

    @Schema(description = "field witch has an error", example = "price")
    private String field;
    @Schema(description = "value from user was rejected by server", example = "1000.00")
    private String rejectedValue;
    @Schema(description = "message for user", example = "must be less than or equal to 200")
    private String message;

}
