package com.petCommunity.PetCommunityBack.ControllersTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.petCommunity.PetCommunityBack.Controllers.UserController;
import com.petCommunity.PetCommunityBack.DTOs.UserReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.UserRespDTO;
import com.petCommunity.PetCommunityBack.Services.IUserCrudServ;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static com.petCommunity.PetCommunityBack.Mappers.UserMapper.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired    MockMvc mockMvc;
    @Autowired    ObjectMapper objectMapper;
    @MockBean
    IUserCrudServ crudService;

    private final List<UserRespDTO> associationsDTOList = new ArrayList<>();
    private UserReqDTO userReqDTO;

    @BeforeEach
    void init(){
        Faker faker = new Faker();

        for (Long i = 0L; i < 5 ; i++) {
            associationsDTOList.add(UserRespDTO.builder()
                    .id(i)
                    .username(faker.funnyName().name())
                    .logo(faker.avatar().image())
                    .adress(faker.address().cityName())
                    .capacity(faker.number().numberBetween(30,50))
                    .build()
            );
        }

        userReqDTO = UserReqDTO.builder()
                .username(faker.funnyName().name())
                .logo(faker.avatar().image())
                .adress(faker.address().cityName())
                .capacity(faker.number().numberBetween(30, 50))
                .build();


    }

    public String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void getAllMethodShouldReturnAListOfAssociationRespDTOS() throws Exception{
        when(crudService.getAll()).thenReturn(associationsDTOList);

        var sut = mockMvc.perform(get("/associations"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var expected = associationsDTOList;

        assertThat(objectMapper.readValue(sut, new TypeReference<List<UserRespDTO>>() {}))
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void getByIdMethodShouldReturnAssociationDTO() throws Exception {
        when(crudService.getById(2L)).thenReturn(associationsDTOList.get(3));

        var sut = mockMvc.perform(get("/associations/2"))
                .andReturn().getResponse().getContentAsString();

        var expected = associationsDTOList.get(3);

        assertThat(objectMapper.readValue(sut, expected.getClass()))
                .usingRecursiveComparison().isEqualTo(expected);

        assertEquals(toJson(expected),sut,false);

    }

    @Test
    public void whenCreatingANewAssociationGetObjectCreated() throws Exception {
        var expected = mapToUserRespDTO(mapToUser(userReqDTO));

        when(crudService.update(ArgumentMatchers.any(UserReqDTO.class))).thenReturn(expected);

        var sut = mockMvc.perform(post("/associations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(userReqDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(objectMapper.readValue(sut, expected.getClass()))
                .usingRecursiveComparison().isEqualTo(expected);

        assertEquals(toJson(expected),sut,false);
    }

    @Test
    public void  whenUpdatingAnAssociationReturnPetUpdated() throws Exception {
        var expected = mapToUserRespDTO(mapToUser(userReqDTO));

        when(crudService.update(ArgumentMatchers.any(UserReqDTO.class))).thenReturn(expected);

        var sut = mockMvc.perform(post("/associations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(userReqDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(objectMapper.readValue(sut, expected.getClass()))
                .usingRecursiveComparison().isEqualTo(expected);

        assertEquals(toJson(expected),sut,false);
    }

    @Test
    public void whenDeletingAPetReturnConfirmationString() throws Exception {
        String expected = "Association errased correctly.";
        when(crudService.deleteId(2L)).thenReturn(expected);

        var sut = mockMvc.perform(delete("/associations/2")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        org.junit.jupiter.api.Assertions.assertEquals(expected,sut);
    }
}
