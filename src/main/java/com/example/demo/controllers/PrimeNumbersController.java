package com.example.demo.controllers;

import com.example.demo.model.PrimeNumbers;
import com.example.demo.services.PrimeNumbersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.LongStream;


@RestController
@RequiredArgsConstructor
public class PrimeNumbersController {

    private final PrimeNumbersService primeNumbersService;

    @GetMapping
    public Flux<PrimeNumbers> getMultiplePrimeNumbers(@RequestParam("numbers") Optional<Long[]> numbers) {
        return primeNumbersService
                .getMultiplePrimeNumbers(
                        Arrays.asList(numbers.orElse(
                                LongStream.rangeClosed(1, 500).boxed().toArray(Long[]::new))
                        )
                );
    }
}
