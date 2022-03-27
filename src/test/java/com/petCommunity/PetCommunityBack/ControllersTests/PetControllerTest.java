package com.petCommunity.PetCommunityBack.ControllersTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.petCommunity.PetCommunityBack.Controllers.PetController;
import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import com.petCommunity.PetCommunityBack.DomainModels.User;
import com.petCommunity.PetCommunityBack.DomainModels.PetImg;
import com.petCommunity.PetCommunityBack.Services.IPetCrudService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.petCommunity.PetCommunityBack.Mappers.UserMapper.mapToUserReqDTO;
import static com.petCommunity.PetCommunityBack.Mappers.UserMapper.mapToUserRespDTO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PetController.class)
class PetControllerTest {
    @Autowired   MockMvc mockMvc;
    @Autowired   ObjectMapper objectMapper;
    @MockBean    IPetCrudService petCrudService;
//    @MockBean    PetImgCrudService petImgCrudService;
//    @MockBean   ImgsStorageService cloudinaryImpl;
//    @MockBean   IAuthenticationFacade authenticationFacade;

    public List<PetRespDTO>pets = new ArrayList<>();
    public PetReqDTO petReqDTO;
    public List<PetImg> petImgs = new ArrayList<>();
    public User user = User.builder()
            .id(1L)
            .username("patitas")
            .adress("street 123")
            .logo("logo.jpg")
            .password("asdfr4321")
            .capacity(50)
            .build();

    @BeforeEach
    void init(){

        Faker faker = new Faker();


        for (Long i = 0L; i < 8  ; i++) {
            petImgs.add(PetImg.builder()
                    .id(i)
                    .url(faker.avatar().image())
                    .build());
        }

        for (Long i = 0L; i < 10; i++) {
            pets.add(PetRespDTO.builder()
                    .id(i)
                    .chip(true)
                    .race(faker.lorem().characters(8,14))
                    .size(Pet.Sizes.medium)
                    .age(faker.dog().size())
                    .specie(Pet.Specie.cat)
                    .gender(Pet.Gender.male)
                    .priority(Pet.Priority.high)
                    .vaccines(faker.random().nextBoolean())
                    .description(faker.dog().memePhrase())
                    .userRespDTO(mapToUserRespDTO(user))
                    .petImg(petImgs.subList(0,3))
                    .build());
        }

        petReqDTO = PetReqDTO.builder()
                .id(1L)
                .name("firulais")
                .chip(true)
                .race(faker.lorem().characters(8,14))
                .size(Pet.Sizes.medium)
                .age(faker.dog().age())
                .gender(Pet.Gender.female)
                .priority(Pet.Priority.low)
                .specie(Pet.Specie.cat)
                .vaccines(faker.random().nextBoolean())
                .description(faker.dog().memePhrase())
                .userReqDTO(mapToUserReqDTO(user))
                .build();
    };

    public String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }


    @Test
    public void getAllMethodShouldReturnAListOfPetsDTOS() throws Exception {
        when(petCrudService.getAll()).thenReturn(pets);
        //doReturn(pets).when(crudService).getAll();


        var sut = mockMvc.perform(get("/pets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var expected = pets;

        assertThat(objectMapper.readValue(sut, new TypeReference<List<PetRespDTO>>() {}))
                .usingRecursiveComparison().isEqualTo(expected);
    }

//    @Test
//    public void  getByIdMethodShouldReturnPetDTO() throws Exception {
//        doReturn(pets.get(5)).when(petCrudService).getById(5L);
//
//        var sut = mockMvc.perform(get("/pets/5"))
//                .andReturn().getResponse().getContentAsString();
//
//        var expected = pets.get(5);
//
//        assertThat(objectMapper.readValue(sut, expected.getClass()))
//                .usingRecursiveComparison().isEqualTo(expected);
//
//    }
//
//    @Test
//    public void whenCreatingANewPetGetObjectCreated() throws Exception {
//
//        doReturn(petImgs.subList(0,3)).when(petImgCrudService).findAllByPet(mapToPet(petReqDTO));
//        String url = "img.jpg";
//        when(petCrudService.save(ArgumentMatchers.any(PetReqDTO.class),ArgumentMatchers.any(String.class))).thenReturn(mapToPetRespDTO(mapToPet(petReqDTO)));
//        var expected = mapToPetRespDTO(mapToPet(petReqDTO));
//        var sut = mockMvc.perform(post("/pets")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(petReqDTO))
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isOk())
//                        .andReturn().getResponse().getContentAsString();
//
//        assertThat(objectMapper.readValue(sut, expected.getClass()))
//                .usingRecursiveComparison().isEqualTo(expected);
//    }
//
//    @Test
//    public void whenUpdatingAPetReturnPetUpdated() throws Exception {
//        var expected = mapToPetRespDTO(mapToPet(petReqDTO));
//
//        when(petCrudService.save(ArgumentMatchers.any(PetReqDTO.class),"img.jpg")).thenReturn(expected);
//
//        var sut = mockMvc.perform(post("/pets")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(petReqDTO))
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isOk())
//                        .andReturn().getResponse().getContentAsString();
//
//        assertThat(objectMapper.readValue(sut, expected.getClass()))
//                .usingRecursiveComparison().isEqualTo(expected);
//    }
//
//    @Test
//    public void whenDeletingAPetReturnConfirmationString() throws Exception {
//        String expected = "Pet errased correctly.";
//        when(petCrudService.deleteId(1L)).thenReturn(expected);
//
//        var sut = mockMvc.perform(delete("/pets/1")
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andReturn().getResponse().getContentAsString();
//
//        assertEquals(expected,sut);
//    }
}



