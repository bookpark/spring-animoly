package toy.animoly.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnimalService {
    @SneakyThrows // Exception 처리 해주는 lombok annotation
    public String fetchAnimals() {
        // api 호출 후 결과 값 xmlString에 담기
        RestTemplate rt = new RestTemplate();
        String apiEndPoint = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?serviceKey=R6f1Dk8rd%2FXqp0BREWtNhJxXEnNnkhhkae%2FlK8ZMBqCwP2PxOLczhCcxDupBB4Vf96JwjjjATYybZ3c6uXRr8Q%3D%3D";
        URI uri = new URI(apiEndPoint);
        String xmlString = rt.getForObject(uri, String.class);

        // org.json 라이브러리로 xmlString을 jsonString으로 변환
        JSONObject jsonObject = XML.toJSONObject(Objects.requireNonNull(xmlString));
        return jsonObject.toString();
    }
}
