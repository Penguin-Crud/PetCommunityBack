package com.petCommunity.PetCommunityBack.ControllersTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.petCommunity.PetCommunityBack.Controllers.PetController;
import com.petCommunity.PetCommunityBack.DTOs.PetReqDTO;
import com.petCommunity.PetCommunityBack.DTOs.PetRespDTO;
import com.petCommunity.PetCommunityBack.DomainModels.Association;
import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import com.petCommunity.PetCommunityBack.Services.PetCrudService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.petCommunity.PetCommunityBack.Mappers.AssociationMapper.mapToAssociationReqDTO;
import static com.petCommunity.PetCommunityBack.Mappers.AssociationMapper.mapToAssociationRespDTO;
import static com.petCommunity.PetCommunityBack.Mappers.PetMapper.mapToPet;
import static com.petCommunity.PetCommunityBack.Mappers.PetMapper.mapToPetRespDTO;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PetController.class)
class PetControllerTest {
    @Autowired   MockMvc mockMvc;
    @Autowired   ObjectMapper objectMapper;
    @MockBean    PetCrudService crudService;

    public List<PetRespDTO>pets = new ArrayList<>();
    public Pet pet;
    public PetReqDTO petReqDTO;
    public Association authUser;

    @BeforeEach
    void init(){

        Faker faker = new Faker();

        var authUser = Association.builder()
                .id(1L)
                .name("Patitas de Perros")
                .build();

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
                    .associationRespDTO(mapToAssociationRespDTO(authUser))
                    .build());
        }

        Pet pet = Pet.builder()
                .id(1L)
                .hasChip(true)
                .race(faker.lorem().characters(8,14))
                .size(faker.dog().size())
                .age(faker.dog().age())
                .specie("canino")
                .vaccinated(faker.random().nextBoolean())
                .description(faker.dog().memePhrase())
                .association(authUser)
                .build();

        petReqDTO = PetReqDTO.builder()
                .id(1L)
                .hasChip(true)
                .race(faker.lorem().characters(8,14))
                .size(faker.dog().size())
                .age(faker.dog().age())
                .specie("canino")
                .vaccinated(faker.random().nextBoolean())
                .description(faker.dog().memePhrase())
                .associationReqDTO(mapToAssociationReqDTO(authUser))
                .build();
    };

    public String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }


    @Test
    public void getAllMethodShouldReturnAListOfPetsDTOS() throws Exception {
        when(crudService.getAll()).thenReturn(pets);
        //doReturn(5).when(crudService).getAll();


        mockMvc.perform(get("/pets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(10)));
    }

    @Test
    public void  getByIdMethodShouldReturnPetDTOasJSON() throws Exception {
        doReturn(pets.get(5)).when(crudService).getById(5L);

        var sut = mockMvc.perform(get("/pets/5"))
                .andReturn().getResponse().getContentAsString();

        var expected = pets.get(5);

        assertThat(objectMapper.readValue(sut, expected.getClass()))
                .usingRecursiveComparison().isEqualTo(expected);

    }

    @Test
    public void whenCreatingANewPetGetObjectCreated() throws Exception {
        var expected = mapToPetRespDTO(mapToPet(petReqDTO));

        when(crudService.save(ArgumentMatchers.any(PetReqDTO.class))).thenReturn(expected);

        var sut = mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(petReqDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(objectMapper.readValue(sut, expected.getClass()))
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void whenUpdatingAPetReturnPetUpdated() throws Exception {
        var expected = mapToPetRespDTO(mapToPet(petReqDTO));

        when(crudService.save(ArgumentMatchers.any(PetReqDTO.class))).thenReturn(expected);

        var sut = mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(petReqDTO))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        assertThat(objectMapper.readValue(sut, expected.getClass()))
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void whenDeletingAPetReturnConfirmationString() throws Exception {
        String expected = "Pet errased correctly.";
        when(crudService.deleteId(petReqDTO.getId())).thenReturn(expected);

        var sut = mockMvc.perform(delete("/pets/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(expected,sut);
    }
}



