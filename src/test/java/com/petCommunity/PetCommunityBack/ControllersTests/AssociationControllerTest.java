package com.petCommunity.PetCommunityBack.ControllersTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.petCommunity.PetCommunityBack.Controllers.AssociationController;
import com.petCommunity.PetCommunityBack.DTOs.AssociationReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.AssociationRespDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.Services.AssociationCrudServ;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;


import static com.petCommunity.PetCommunityBack.Mappers.AssociationMapper.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AssociationController.class)
public class AssociationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    AssociationCrudServ crudService;

    private final List<AssociationRespDTO> associationsDTOList = new ArrayList<>();
    private AssociationReqDTO associationReqDTO;

    @BeforeEach
    void init(){
        Faker faker = new Faker();

        for (Long i = 0L; i < 5 ; i++) {
            associationsDTOList.add(AssociationRespDTO.builder()
                    .id(i)
                    .name(faker.funnyName().name())
                    .logo(faker.avatar().image())
                    .adress(faker.address().cityName())
                    .capacity(faker.number().numberBetween(30,50))
                    .build()
            );
        }

        associationReqDTO = AssociationReqDTO.builder()
                .name(faker.funnyName().name())
                .password(faker.random().hex(8))
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

        assertThat(objectMapper.readValue(sut, new TypeReference<List<AssociationRespDTO>>() {}))
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
        var expected = mapToAssociationRespDTO(mapToAssociation(associationReqDTO));

        when(crudService.save(ArgumentMatchers.any(AssociationReqDTO.class))).thenReturn(expected);


    }
}
