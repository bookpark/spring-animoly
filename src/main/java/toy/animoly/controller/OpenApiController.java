package toy.animoly.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import toy.animoly.entity.Animal;
import toy.animoly.repository.AnimalRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class OpenApiController {
    private final AnimalRepository animalRepository;
    @Value("${toy.animoly.api.endPoint}")
    private String apiEndPoint;
    @Value("${toy.animoly.api.detail}")
    private String detail;
    @Value("${toy.animoly.api.encodingKey}")
    private String encodingKey;
    @Value("${toy.animoly.api.decodingKey}")
    private String decodingKey;
    @Value("${toy.animoly.api.numOfRows}")
    private int numOfRows;

    @GetMapping("/api/fetch-animals")
    public Result fetchAnimals(@RequestParam(defaultValue = "1", required = false) String pageNo,
                               @RequestParam(defaultValue = "", required = false) String upKind,
                               @RequestParam(defaultValue = "", required = false) String kind,
                               @RequestParam(defaultValue = "", required = false) String uprCd) throws URISyntaxException {
        RestTemplate rt = new RestTemplate();
        try {
            URI uri = new URI(apiEndPoint + detail + "?serviceKey=" + encodingKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&upkind=" + upKind + "&kind=" + kind + "&upr_cd=" + uprCd);
            String xmlString = rt.getForObject(uri, String.class);
            // org.json 라이브러리로 xmlString을 jsonString으로 변환
            JSONObject jsonObject = XML.toJSONObject(Objects.requireNonNull(xmlString));

            // json parsing
            JSONObject jsonResponse = (JSONObject) jsonObject.get("response");
            JSONObject jsonBody = (JSONObject) jsonResponse.get("body");
            JSONObject jsonItems = (JSONObject) jsonBody.get("items");
            JSONArray jsonItem = (JSONArray) jsonItems.get("item");

            List<AnimalDto> collect = new ArrayList<>();
            for (Object o : jsonItem) {
                JSONObject item = (JSONObject) o;
                collect.add(selectItem(item));
            }

            // 총 갯수 가져오기
            Object totalCountObject = jsonBody.get("totalCount");
            String totalCountString = totalCountObject.toString();

            // 페이지 당 갯수 가져오기
            Object numOfRowsObject = jsonBody.get("numOfRows");
            String numOfRowsString = numOfRowsObject.toString();

            return new Result(collect, pageNo, jsonItem.length(), totalCountString);
        } catch (ClassCastException e) {
            return new Result(e);
        }
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
        private T pageNo;
        private T numOfRows;
        private T totalCount;

        public Result(ClassCastException e) {
        }
    }

    @GetMapping("/api/save-animals")
    @SneakyThrows
    public void saveAnimals() {
        RestTemplate rt = new RestTemplate();
        URI uri = new URI(apiEndPoint + detail + "?serviceKey=" + encodingKey + "&numOfRows=" + numOfRows);
        String xmlString = rt.getForObject(uri, String.class);

        // org.json 라이브러리로 xmlString을 jsonString으로 변환
        JSONObject jsonObject = XML.toJSONObject(Objects.requireNonNull(xmlString));

        // json parsing
        JSONObject jsonResponse = (JSONObject) jsonObject.get("response");
        JSONObject jsonBody = (JSONObject) jsonResponse.get("body");
        JSONObject jsonItems = (JSONObject) jsonBody.get("items");
        JSONArray animals = (JSONArray) jsonItems.get("item");

        ObjectMapper mapper = new ObjectMapper();
        for (Object o : animals) {
            JSONObject animalO = (JSONObject) o;
            Animal animal = mapper.readValue(animalO.toString(), Animal.class);
            animalRepository.save(animal);
        }
    }

    @Builder
    // No serializer found for class 에러를 해결하기 위한 annotation
    // private field에도 접근하게 해주어 json으로 변환하는데 사용되게 함
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class AnimalDto {
        private Long desertionNo;
        private String noticeNo;
        private String processState;
        private String happenDt;
        private String sex;
        private String kind;
        private String age;
        private String weight;
        private String color;
        private String specialMark;
        private String thumbnail;
        private String originalImage;
        private String careNm;
        private String careAddr;
        private String careTel;
    }

    // 반환된 json object 중 필요한 field값만 가져와 dto로 전달하는 메서드
    public AnimalDto selectItem(JSONObject item) {
        return AnimalDto.builder()
                .desertionNo((Long) item.get("desertionNo"))
                .noticeNo((String) item.get("noticeNo"))
                .processState((String) item.get("processState"))
                .sex((String) item.get("sexCd"))
                .kind((String) item.get("kindCd"))
                .age((String) item.get("age"))
                .weight((String) item.get("weight"))
                .color((String) item.get("colorCd"))
                .specialMark((String) item.get("specialMark"))
                .thumbnail((String) item.get("filename"))
                .originalImage((String) item.get("popfile"))
                .careNm((String) item.get("careNm"))
                .careTel((String) item.get("careTel"))
                .careAddr((String) item.get("careAddr"))
                .build();
    }
}
