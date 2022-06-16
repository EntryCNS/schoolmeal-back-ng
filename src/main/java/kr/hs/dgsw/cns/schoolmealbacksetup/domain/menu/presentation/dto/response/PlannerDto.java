package kr.hs.dgsw.cns.schoolmealbacksetup.domain.menu.presentation.dto.response;

import kr.hs.dgsw.cns.schoolmealbacksetup.global.infra.neis.MealPlannerInfra;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlannerDto {
    private List<MealPlannerInfra.MealItem> planner;
}
