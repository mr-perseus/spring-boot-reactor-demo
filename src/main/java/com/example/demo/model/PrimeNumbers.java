package com.example.demo.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PrimeNumbers {
    private Long number;

    private List<Integer> primeNumbers;
}
