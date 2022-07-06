package kr.hs.dgsw.cns.schoolmealbacksetup.service.menu;

import kr.hs.dgsw.cns.schoolmealbacksetup.global.infra.neis.MealPlannerInfra;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class MealPlannerInfraTest {

    @Mock
    private MealPlannerInfra mealPlannerInfra;

    private List<String> list(String... args) {
        return List.of(args);
    }

    @DisplayName("식단표 조회")
    @RepeatedTest(10)
    void getMeals() {
        // given
        String date = "20220616";
        List<MealPlannerInfra.MealItem> mealItems = List.of(
                new MealPlannerInfra.MealItem(date, "조식", list("*기장밥", "새알심만두국", "숙주나물무침")),
                new MealPlannerInfra.MealItem(date, "중식", list("*기장밥", "단배추된장국", "오향장육")),
                new MealPlannerInfra.MealItem(date, "석식", list("매콤치킨마요덮밥", "미소된장국", "두부양념구이"))
        );
        when(mealPlannerInfra.getMealsOfDate(anyInt(), anyInt(), anyInt()))
                .thenReturn(mealItems);

        List<MealPlannerInfra.MealItem> actual = mealPlannerInfra.getMealsOfDate(anyInt(), anyInt(), anyInt());

        assertThat(actual).hasSize(3);
        assertThat(actual.get(0).getMenuList()).hasSize(3);
        assertThat(actual.get(1).getMenuList()).hasSize(3);
        assertThat(actual.get(2).getMenuList()).hasSize(3);
    }
}
