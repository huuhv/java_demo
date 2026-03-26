package com.example.demo.classes;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;

public class ClassController {
    // Generic method: works with any type
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello from ClassController!");

        // OuterClass vs InnerClass
        // 1. tạo đối tượng bên ngoài trước
        OuterClass outer = new OuterClass();

        // 2. tạo đối tượng bên trong thông qua đối tượng bên ngoài
        OuterClass.InnerClass inner = outer.new InnerClass();

        System.out.println("Outer class variable x: " + outer.x);
        System.out.println("Inner class variable y: " + inner.y);
        System.out.println("Total of x and y: " + (outer.x + inner.y));

        // Tạo đối tượng từ Animal, nhưng VỪA TẠO VỪA GHI ĐÈ luôn hàm của nó!
        Animal myAnimal = new Animal() {
            public void makeSound() {
                System.out.println("Woof woof");
            }
        }; // LƯU Ý: Chỗ này phải có dấu chấm phẩy (;) để kết thúc lệnh

        myAnimal.makeSound(); // In ra: Woof woof

        // "new" một Interface bằng cách cung cấp luôn thân hàm cho nó
        Greeting greet = new Greeting() {
            public void sayHello() {
                System.out.println("Hello, World!");
            }
        };

        greet.sayHello();

        // Gọn gàng hơn rất nhiều!
        Greeting greet2 = () -> System.out.println("Hello, World!");
        greet2.sayHello();

        Level myVar = Level.MEDIUM;

        switch(myVar) {
            case LOW: // Không cần viết là Level.LOW
                System.out.println("Mức độ thấp");
                break;
            case MEDIUM:
                System.out.println("Mức độ trung bình");
                break;
            case HIGH:
                System.out.println("Mức độ cao");
                break;
        }

        for (Level myVar2 : Level.values()) {
            System.out.println(myVar2);
            System.out.println(myVar2.getDescription());
        }
        // Kết quả in ra lần lượt: LOW, MEDIUM, HIGH

        ErrorCode error = ErrorCode.NOT_FOUND;
        System.out.println("Lỗi số: " + error.getCode() + " - " + error.getMessage());
        // In ra: Lỗi số: 404 - Không tìm thấy dữ liệu


        // Generic Class Example
        // Create a Box to hold a String
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello, Generics!");
        System.out.println("String in box: " + stringBox.get());

        // Create a Box to hold an Integer
        Box<Integer> integerBox = new Box<>();
        integerBox.set(123);
        System.out.println("Integer in box: " + integerBox.get());

        // Generic Method Example
        // Array of Strings
        String[] names = new String[]{"Hello", "World"};
        Arrays.sort(names);
        printArray(names);

        // Array of Integers
        Integer[] numbers = new Integer[]{1, 2, 3};
        Arrays.sort(numbers);
        printArray(numbers);

        // Bounded Types
        // Use with Integer
        Integer[] numbers2 = new Integer[]{1, 2, 3};
        Stats<Integer> intStats = new Stats<>(numbers2);
        System.out.println("Average of numbers: " + intStats.average());
        // Use with Double
        Double[] doubleArray = new Double[]{1.1, 2.2, 3.3};
        Stats<Double> doubleStats = new Stats<>(doubleArray);
        System.out.println("Average of doubles: " + doubleStats.average());
    }
}

