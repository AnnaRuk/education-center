package de.ait.ec.services.impl;

import de.ait.ec.dto.CourseDto;
import de.ait.ec.dto.NewCourseDto;
import de.ait.ec.exceptions.RestException;
import de.ait.ec.models.Course;
import de.ait.ec.repositories.CoursesRepository;
import de.ait.ec.services.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;
    @Override

    //TODO
    public CourseDto addCourse(NewCourseDto newCourse) {
        Course course = Course.builder()
                .title(newCourse.getTitle())
                .description(newCourse.getDescription())
                .price(newCourse.getPrice())
                .beginDate(LocalDate.parse(newCourse.getBeginDate()))
                .endDate(LocalDate.parse(newCourse.getEndDate()))
                .state(Course.State.DRAFT)
                .build();
        coursesRepository.save(course);

        return CourseDto.from(course);
    }

    @Override
    public List<CourseDto> getCourses() {
       List<Course> courses =  coursesRepository.findAll();
        return CourseDto.from(courses);
    }

    @Override
    public CourseDto getCourseById(Long id) {
/*
        Optional<Course> optionalCourse = coursesRepository.findById(id);
        Course course;
        if (optionalCourse.isPresent()){
            course = optionalCourse.get();
        } else { throw new IllegalArgumentException("Course with id: " + id + " not found");     }
        */
       Course course = coursesRepository.findById(id)
               .orElseThrow(()-> new RestException(HttpStatus.NOT_FOUND, "Course with id: " + id + " not found"));
        return CourseDto.from(course);


    }
}
