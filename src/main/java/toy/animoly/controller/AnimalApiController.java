package toy.animoly.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.animoly.dto.AnimalListDto;
import toy.animoly.entity.Animal;
import toy.animoly.service.AnimalService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AnimalApiController {
    private final AnimalService animalService;

    @GetMapping("/api/animalsV1")
    public List<Animal> getAnimalsV1() {
        return animalService.getAnimals();
    }

    @GetMapping("/api/animalsV2")
    public Result getAnimalsV2() {
        List<Animal> animals = animalService.getAnimals();
        List<AnimalListDto> collect = animals.stream()
                .map(a -> new AnimalListDto(
                        a.getDesertionNo(),
                        a.getFilename(),
                        a.getAge(),
                        a.getSexCd(),
                        a.getProcessState()
                ))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
