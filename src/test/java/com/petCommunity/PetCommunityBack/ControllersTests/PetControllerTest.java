package com.petCommunity.PetCommunityBack.ControllersTests;

import com.petCommunity.PetCommunityBack.Controllers.PetController;
import com.petCommunity.PetCommunityBack.DomainModels.Association;
import com.petCommunity.PetCommunityBack.DomainModels.Pet;
import com.petCommunity.PetCommunityBack.Services.PetCrudService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PetController.class)
class PetControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PetCrudService crudService;

    Association authAssociation = new Association();

    Pet exAnimal1 = new Pet();

    Pet exAnimal2 = new Pet();


    @Test
    public void getAllMethodShouldReturnAListOfPets() throws Exception {

        List<Pet> fakeAnimals = new ArrayList<>(Arrays.asList(exAnimal1, exAnimal2));


        when(crudService.getAll()).thenReturn(fakeAnimals);


        mockMvc.perform(get("/pets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }
}

