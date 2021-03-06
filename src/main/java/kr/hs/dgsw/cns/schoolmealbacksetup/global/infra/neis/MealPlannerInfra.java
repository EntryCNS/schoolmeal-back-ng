package kr.hs.dgsw.cns.schoolmealbacksetup.global.infra.neis;

import kr.hs.dgsw.cns.schoolmealbacksetup.global.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.json.simple.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MealPlannerInfra {
    @Getter @ToString
    @AllArgsConstructor
    public static class MealItem {
        private final String date;
        private final String time;
        private final List<String> menuList;
    }

    public static class MealParseFailedException extends BusinessException {
        public MealParseFailedException() { super(HttpStatus.INTERNAL_SERVER_ERROR, "식단표 파싱에 실패했습니다"); }
    }

    private static final String BASE_URL = "https://open.neis.go.kr/hub/mealServiceDietInfo?type=json&ATPT_OFCDC_SC_CODE=D10&SD_SCHUL_CODE=7240454&MLSV_YMD=%s";

    private JSONObject parseFrom(String url) throws IOException {
        URL queryUrl = new URL(url);

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(queryUrl.openStream(), StandardCharsets.UTF_8)) ) {
            String jsonResult = reader.lines().collect(Collectors.joining("\n"));

            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(jsonResult);
        }catch (IOException | ParseException ex) {
            return new JSONObject(Collections.emptyMap());
        }
    }

    private List<MealItem> toResultList(JSONObject json) {
        JSONArray resultArray = (JSONArray) json.get("mealServiceDietInfo");

        JSONObject menuArrayOuter = ((JSONObject)resultArray.get(1));
        JSONArray menuArray = (JSONArray)menuArrayOuter.get("row");

        List<MealItem> list = new ArrayList<>();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        for(int i = 0; i < menuArray.size(); i++) {
            JSONObject menu = (JSONObject) menuArray.get(i);

            String menuTimeString = (String)menu.get("MMEAL_SC_NM");
            String menuString = (String)menu.get("DDISH_NM");

            MealItem menuObject = new MealItem(date, menuTimeString, Arrays.stream(menuString.split("<br/>")).map(it -> it.split(" {2}")[0]).collect(Collectors.toList()));
            list.add(menuObject);
        }

        return list;
    }


    @Cacheable(value = "MealPlannerInfra", key = "#year + #month + #day")
    public List<MealItem> getMealsOfDate(int year, int month, int day) {
        String timeString = String.format("%02d%02d%02d", year, month, day);
        try {
            JSONObject json = parseFrom(String.format(BASE_URL, timeString));
            return toResultList(json);
        } catch (Exception ex) {
            throw new MealParseFailedException();
        }
    }
}
