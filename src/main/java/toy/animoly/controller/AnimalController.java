package toy.animoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.animoly.service.AnimalService;


@RestController
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping("/api/fetch-animals")
    public String fetchAnimals() {
        return animalService.fetchAnimals();
    }
}
