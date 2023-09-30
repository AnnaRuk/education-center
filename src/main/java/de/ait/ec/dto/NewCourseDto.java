package de.ait.ec.dto;

import de.ait.ec.models.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Schema(name = "New course", description = "Information for adding a course")
public class NewCourseDto {



    @Schema(description = "title", example = "Java")
    @NotNull
    @NotBlank
    @NotEmpty
    private String title;


    @Schema(description = "description", example = "java-core course")
    @NotNull
    @NotBlank
    @Size(min = 5, max = 1000)
    private String description;

    @Schema(description = "begin date of course", example = "2023-10-05")
    @Pattern(regexp = "^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[0-1])$")
    private String beginDate;

    @Schema(description = "end date of course", example = "2024-10-05")
    @Pattern(regexp = "^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[0-1])$")
    private String endDate;

    @Schema(description = "price", example = "2000.00")
    @Min(value = 0)
    @Max(value = 2000)
    private double price;

}
