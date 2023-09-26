package de.ait.ec.dto;

import de.ait.ec.models.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Schema(name = "Course", description = "Information about a course")
public class CourseDto {

    @Schema(description = "id", example = "1")
    private Long id;
    @Schema(description = "title", example = "Java")
    private String title;
    @Schema(description = "description", example = "java-core course")
    private String description;
    @Schema(description = "begin date of course", example = "2023-10-05")
    private String beginDate;
    @Schema(description = "end date of course", example = "2024-10-05")
    private String endDate;
    @Schema(description = "price", example = "2000.00")
    private double price;
    @Schema(description = "state", example = "DRAFT or PUBLISHED")
    private String state;

    public static CourseDto from(Course course){
        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .beginDate(course.getBeginDate().toString())
                .endDate(course.getEndDate().toString())
                .price(course.getPrice())
                .state(course.getState().toString())
                .build();
    }

    public static List<CourseDto> from(List<Course> courses){
        return courses.stream()
                .map(CourseDto::from)
                .collect(Collectors.toList());
    }
}
