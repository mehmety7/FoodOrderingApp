package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.dto.MealDto;
import com.finartz.restaurantapp.model.request.create.MealCreateRequest;
import com.finartz.restaurantapp.service.MealService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(MealController.class)
public class MealControllerTest {

    private static final String URI_MEAL = "/meal";
    private static final String NAME_KRAL_MENU = "Kral Menü";
    private static final Double PRICE_15_99 = 15.99;


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealService mealService;

    @MockBean
    private UserDetailsService userDetailsService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void whenGetMealById_thenReturnMeal() throws Exception {

        MealDto meal = MealDto.builder()
                .id(1L)
                .name(NAME_KRAL_MENU)
                .price(PRICE_15_99)
                .build();

        Mockito.when(mealService.getMeal(1L)).thenReturn(meal);

        mockMvc.perform(get(URI_MEAL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(NAME_KRAL_MENU)));

    }

    @Test
    public void whenCreateNewMeal_thenReturnMeal() throws Exception {

        MealDto meal = MealDto.builder()
                .id(1L)
                .name(NAME_KRAL_MENU)
                .price(PRICE_15_99)
                .build();

        MealCreateRequest mealCreateRequest = MealCreateRequest.builder().build();

        Mockito.when(mealService.createMeal(mealCreateRequest)).thenReturn(meal);

        String requestJson = objectWriter.writeValueAsString(meal);

        mockMvc.perform(post(URI_MEAL)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated());
    }

}
