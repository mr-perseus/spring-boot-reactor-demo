package com.example.demo.services;

import com.example.demo.model.PrimeNumbers;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class PrimeNumbersService {

    private static final int BUFFER_SIZE = 10;

    private static final int DELAY_PER_BATCH_MILLIS = 500;

    public Flux<PrimeNumbers> getMultiplePrimeNumbers(Iterable<Long> numbers) {
        return Flux
                .fromIterable(numbers)
                .buffer(BUFFER_SIZE)
                .delayElements(Duration.ofMillis(DELAY_PER_BATCH_MILLIS))
                .concatMapIterable(Function.identity())
                .log()
                .map(this::primeFactors);
    }


    // This just represents some resource intensive task. This could instead be an interface which allows only a limited amount of load.
    private PrimeNumbers primeFactors(Long number) {
        long n = number;
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        return PrimeNumbers
                .builder()
                .number(number)
                .primeNumbers(factors)
                .build();
    }

}
