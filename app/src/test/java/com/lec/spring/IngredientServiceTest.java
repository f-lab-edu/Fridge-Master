package com.lec.spring;

import com.lec.spring.entity.Ingredient;
import com.lec.spring.repository.IngredientRepository;
import com.lec.spring.service.IngredientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {
    @Mock
    private IngredientRepository ingredientRepository;

//    @InjectMocks

    @Mock
    Ingredient ingredient = new Ingredient();

    @BeforeEach
    void setUp() {
        ingredient.setName("고추");
    }

    @Test
    @DisplayName("재료 저장")
    void registerIngredient() {
        when(ingredientRepository.save(ingredient)).thenReturn(ingredient);

        IngredientServiceImpl ingredientService = new IngredientServiceImpl(ingredientRepository);
        Ingredient result = ingredientService.registerIngredient(ingredient);

        assertThat(result.getName()).isEqualTo("고추");
    }
}
