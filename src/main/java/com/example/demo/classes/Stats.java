package com.example.demo.classes;

public class Stats<T extends Number> {
    T[] numbers;

    // Constructor
    Stats(T[] numbers) {
        this.numbers = numbers;
    }

    // Calculate average
    double average() {
        double sum = 0.0;
        for (T num : numbers) {
            sum += num.doubleValue();
        }
        return sum / numbers.length;
    }
}
