package com.example.demo.services;

import com.example.demo.model.PrimeNumbers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PrimeNumbersServiceTest {

    @Autowired
    private PrimeNumbersService primeNumbersService;

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
    }

    @Test
    public void getMultiplePrimeNumbersTest() {
        primeNumbersService
                .getMultiplePrimeNumbers(Arrays.asList(300L, 300L))
                .as(StepVerifier::create)
                .expectNext(PrimeNumbers.builder().number(300L).primeNumbers(Arrays.asList(2, 2, 3, 5, 5)).build())
                .expectNext(PrimeNumbers.builder().number(300L).primeNumbers(Arrays.asList(2, 2, 3, 5, 5)).build())
                .expectComplete()
                .verify();
    }

    @Test
    public void bigLoopTest() {
        primeNumbersService
                .getMultiplePrimeNumbers(LongStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList()))
                .subscribe(System.out::println);
    }
}
