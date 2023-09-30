package de.ait.ec.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Endpoint /courses is works:")
@DisplayNameGeneration(value =
        DisplayNameGenerator.ReplaceUnderscores.class)
class CoursesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /courses:")
    public class GetCourses {

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_empty_list_of_courses_for_emptyDB_test() throws Exception {
            mockMvc.perform(get("/api/courses"))
                    .andExpect(jsonPath("$.size()",is(0)))
                    .andExpect(status().isOk());

        }


        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Sql(scripts = {"sql/data.sql"})
        public void return_list_of_courses_for_not_emptyDB_test() throws Exception {
            mockMvc.perform(get("/api/courses"))
                    .andExpect(jsonPath("$.size()",is(4)))
                    .andExpect(jsonPath("$.[0].id",is(1)))
                    .andExpect(jsonPath("$.[1].id",is(2)))
                    .andExpect(jsonPath("$.[2].id",is(3)))
                    .andExpect(jsonPath("$.[3].id",is(4)))
                    .andExpect(jsonPath("$.[1].title", is("testTitle2")))
                    .andExpect(status().isOk());
        }

    }

    @Nested
    @DisplayName("POST /courses:")
    public class PostCourses {
        @Test
        @Sql(scripts = {"sql/data.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void created_new_course_test() throws Exception {
         mockMvc.perform(post("/api/courses")
        .contentType("application/json")
        .content("{\n" +
                "  \"title\": \"Новый курс\",\n" +
                "  \"beginDate\": \"2022-02-02\",\n" +
                "  \"endDate\": \"2023-02-02\",\n" +
                "  \"description\": \"Описание нового курса\",\n" +
                "  \"price\": 100.0\n" +
                "}"))
                         .andExpect(jsonPath("$.id",is(5)))
                 .andExpect(jsonPath("$.title",is("Новый курс")))
                 .andExpect(jsonPath("$.beginDate",is("2022-02-02")))
                 .andExpect(jsonPath("$.endDate",is("2023-02-02")))
                 .andExpect(jsonPath("$.description",is("Описание нового курса")))
                 .andExpect(jsonPath("$.price",is(100.00)))
                 .andExpect(status().isCreated());


        }
    }


    @Nested
    @DisplayName("Get /courses/{course-id}:")
    public class GetCourse {
        @Test
        @Sql(scripts = {"sql/data.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_course_by_id_test() throws Exception {
            mockMvc.perform(get("/api/courses/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id",is(1)))
                    .andExpect(jsonPath("$.title",is("testTitle1")));

        }

        @Test
        @Sql(scripts = {"sql/data.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_null_404_course_by_id_test() throws Exception {
            mockMvc.perform(get("/api/courses/5"))
                    .andExpect(status().isNotFound());


        }

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_400_for_not_valid_course_test() throws Exception {
            mockMvc.perform(post("/api/courses")
                            .contentType("application/json")
                            .content("{\n" +
                                    "  \"title\": \"Новый курс\",\n" +
                                    "  \"beginDate\": \"2022-02-02\",\n" +
                                    "  \"endDate\": \"2023-500000-02\",\n" +
                                    "  \"description\": \"Описание нового курса\",\n" +
                                    "  \"price\": 1009876.0\n" +
                                    "}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errors.size()",is(2)));


        }



    }



}