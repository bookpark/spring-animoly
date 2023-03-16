package toy.animoly.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.animoly.service.AnimalService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping("/api/fetch-animals")
    public List<AnimalService.AnimalDto> fetchAnimals() {
        return animalService.fetchAnimals();
    }
}
