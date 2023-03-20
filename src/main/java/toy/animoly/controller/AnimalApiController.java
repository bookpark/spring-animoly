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

    @GetMapping("/api/animals")
    public Result getAnimals() {
        List<Animal> animals = animalService.getAnimals();
        List<AnimalListDto> collect = animals.stream()
                .map(a -> new AnimalListDto(
                        a.getDesertionNo(),
                        a.getPopfile(),
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
