package de.ait.ec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Message", description = "any message from server")
public class StandardResponseDto {
    @Schema(description = "possible: message about mistake from server", example = "Course not found")
    private String message;
}
