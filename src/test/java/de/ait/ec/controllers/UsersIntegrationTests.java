package de.ait.ec.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
@DisplayName("Endpoint /users is works:")
@DisplayNameGeneration(value =
        DisplayNameGenerator.ReplaceUnderscores.class)
public class UsersIntegrationTests {

    @Autowired
    private MockMvc mockMvc;


    @Nested
    @DisplayName("POST:/users/register")
    public class RegisterUser{


        @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)  ///clean DB
    public void return_created_user() throws Exception {
        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)   //"application/json"
                .content("{\n" +
                        "  \"email\": \"anna22@gmail.com\",\n" +
                        "  \"password\": \"Qwerty002!\"\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.role",is("USER")));

    }

        @Test
        public void return_400_for_bad_format_email() throws Exception {
            mockMvc.perform(post("/api/users/register")
                            .contentType(MediaType.APPLICATION_JSON)   //"application/json"
                            .content("{\n" +
                                    "  \"email\": \"anna22gmail.com\",\n" +
                                    "  \"password\": \"Qwerty002!\"\n" +
                                    "}"))
                    .andExpect(status().isBadRequest());

        }


        @Test
        @Sql(scripts = {"sql/data.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)  ///clean DB
        public void return_409_for_not_unique_email() throws Exception {
            mockMvc.perform(post("/api/users/register")
                            .contentType(MediaType.APPLICATION_JSON)   //"application/json"
                            .content("{\n" +
                                    "  \"email\": \"anna22@gmail.com\",\n" +
                                    "  \"password\": \"Qwerty002!\"\n" +
                                    "}"))
                    .andExpect(status().isConflict());

        }

    }




}
