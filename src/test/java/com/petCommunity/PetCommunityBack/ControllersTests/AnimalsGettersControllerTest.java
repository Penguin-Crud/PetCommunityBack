package com.petCommunity.PetCommunityBack.ControllersTests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petCommunity.PetCommunityBack.Controllers.AnimalsController;
import com.petCommunity.PetCommunityBack.DomainModels.Animal;
import com.petCommunity.PetCommunityBack.Services.AnimalsCrudService;
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

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AnimalsController.class)
class AnimalsGettersControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AnimalsCrudService crudService;

    Animal exAnimal1 = new Animal(1, "barcelona", true, "pitbull", "pitbul.img", "mediano", "4 años", "canino", true, "patitas de perros", "hembra con caracter dominante, con buena relación con personas y niños.");

    Animal exAnimal2 = new Animal(
            1,
            "barcelona",
            true,
            "american standford",
            "animal2.img",
            "mediano",
            "1 años",
            "canino",
            true,
            "animal to the rescue",
            "macho con buena relación con personas y niños. Agresivo con otros perros."
    );

    @Test
    public void getAllMethodShouldReturnAListOfAnimals() throws Exception {

        List<Animal> fakeAnimals = new ArrayList<>(Arrays.asList(exAnimal1, exAnimal2));

        when(crudService.getAll()).thenReturn(fakeAnimals);


        mockMvc.perform(get("/animals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }
}

