package de.ait.ec.controllers;

import de.ait.ec.dto.CourseDto;
import de.ait.ec.dto.NewCourseDto;
import de.ait.ec.dto.StandardResponseDto;
import de.ait.ec.services.CoursesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@Tags(value = @Tag(name = "Courses"))
public class CoursesController {


    private final CoursesService coursesService;


    @Operation(summary = "Adding a course", description = "Available to system administrator")
    @ApiResponses(
            @ApiResponse(responseCode = "201",description = "Course created successful",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseDto.class)))
    )

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CourseDto> addCourse(@RequestBody @Valid NewCourseDto newCourseDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(coursesService.addCourse(newCourseDto));
    }


    @GetMapping
    @Operation(summary = "Getting all courses", description = "Available to everybody")
    public ResponseEntity<List<CourseDto>> getCourses(){
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(coursesService.getCourses());

        return ResponseEntity.ok()
                .body(coursesService.getCourses());
    }

    @Operation(summary = "Getting one course", description = "Available to everybody")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Request successful",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseDto.class))
    ),
    @ApiResponse(responseCode = "404",
            description = "Course not found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StandardResponseDto.class)))}
    )
    @GetMapping("/{course-id}")
    public ResponseEntity<CourseDto> getCourse(@Parameter(description = "identify course", example = "1")
            @PathVariable("course-id") Long id){
        return ResponseEntity.ok().body(coursesService.getCourseById(id));
    }


}
