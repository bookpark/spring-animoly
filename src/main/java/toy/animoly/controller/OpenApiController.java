package toy.animoly.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import toy.animoly.entity.Animal;
import toy.animoly.repository.AnimalRepository;

import java.net.URI;
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
    public List<AnimalDto> fetchAnimals() {
        JSONArray animals = getAnimals();

        // 결과 값 DTO로 반환 후 리턴
        List<AnimalDto> result = new ArrayList<>();
        for (Object o : animals) {
            JSONObject item = (JSONObject) o;
            result.add(selectItem(item));
        }
        return result;
    }

    @GetMapping("/api/save-animals")
    public void saveAnimals() throws JsonProcessingException {
        JSONArray animals = getAnimals();
        ObjectMapper mapper = new ObjectMapper();
        for (Object o : animals) {
            JSONObject animalO = (JSONObject) o;
            Animal animal = mapper.readValue(animalO.toString(), Animal.class);
            animalRepository.save(animal);
        }
    }

    @SneakyThrows // Exception 처리 해주는 lombok annotation
    private JSONArray getAnimals() {
        // api 호출 후 결과 값 xmlString에 담기
        RestTemplate rt = new RestTemplate();
        URI uri = new URI(apiEndPoint + detail + "?serviceKey=" + encodingKey + "&numOfRows=" + numOfRows);
        String xmlString = rt.getForObject(uri, String.class);

        // org.json 라이브러리로 xmlString을 jsonString으로 변환
        JSONObject jsonObject = XML.toJSONObject(Objects.requireNonNull(xmlString));

        // json parsing
        JSONObject jsonResponse = (JSONObject) jsonObject.get("response");
        JSONObject jsonBody = (JSONObject) jsonResponse.get("body");
        JSONObject jsonItems = (JSONObject) jsonBody.get("items");
        JSONArray jsonItemList = (JSONArray) jsonItems.get("item");
        return jsonItemList;
    }

    @Builder
    // No serializer found for class 에러를 해결하기 위한 annotation
    // private field에도 접근하게 해주어 json으로 변환하는데 사용되게 함
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class AnimalDto {
        private String noticeNo;
        private String processState;
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
