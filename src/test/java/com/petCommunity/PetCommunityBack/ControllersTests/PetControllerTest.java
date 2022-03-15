package com.petCommunity.PetCommunityBack.ControllersTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.petCommunity.PetCommunityBack.Controllers.PetController;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Association;
import com.petCommunity.PetCommunityBack.Services.PetCrudService;
import org.assertj.core.api.ObjectAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PetController.class)
class PetControllerTest {
    @Autowired   MockMvc mockMvc;
    @Autowired   ObjectMapper objectMapper;
    @MockBean    PetCrudService crudService;

    static List<PetRespDTO>pets = new ArrayList<>();

    Association authUser = Association.builder()
            .id(1L)
            .name("Patitas de Perros")
            .build();

    @BeforeAll
    static void init(){

        Faker faker = new Faker();

        for (Long i = 0L; i < 10; i++) {
            pets.add(PetRespDTO.builder()
                    .id(i)
                    .hasChip(true)
                    .race(faker.lorem().characters(8,14))
                    .size(faker.dog().size())
                    .age(faker.dog().size())
                    .specie("canino")
                    .vaccinated(faker.random().nextBoolean())
                    .description(faker.dog().memePhrase())
                    .build());
        }

    };

    public String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    private <T> ObjectAssert<T> assertBody(ResultActions result, T expected) throws Exception {
        String body = result.andReturn().getResponse().getContentAsString();
        //noinspection unchecked
        return (ObjectAssert<T>) assertThat(objectMapper.readValue(body, expected.getClass()),samePropertyValuesAs(expected));
    }

    @Test
    public void getAllMethodShouldReturnAListOfPetsDTOS() throws Exception {

        doReturn(pets).when(crudService).getAll();


        mockMvc.perform(get("/pets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(10)));
    }

//    @Test
//    public void  getByIdMethodShouldReturnPetDTOasJSON() throws Exception {
//        doReturn(pets.get(5)).when(crudService).getById(5L);
//
//        ResultActions result = mockMvc.perform(get("/pets/5"));
//
//        var expected = pets.get(5);
//        assertBody(result,expected);
//
//
//    }
}

