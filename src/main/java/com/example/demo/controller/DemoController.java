package com.example.demo.controller;

import com.example.demo.classes.Calculator;

import java.util.ArrayList;
import java.util.Scanner;

public class DemoController {
    // Create main function
    public static void main(String[] args) {
        System.out.println("Hello from DemoController!");

        int myNum = 5;               // Integer (whole number)
        float myFloatNum = 5.99f;    // Floating point number
        char myLetter = 'D';         // Character
        boolean myBool = true;       // Boolean
        String myText = "Hello";     // String

        System.out.println(myNum);
        System.out.println(myFloatNum);
        System.out.println(myLetter);
        System.out.println(myBool);
        System.out.println(myText);

        // ASCII values
        char myVar1 = 65, myVar2 = 66, myVar3 = 67;
        System.out.println(myVar1);
        System.out.println(myVar2);
        System.out.println(myVar3);

        // Create variables of different data types
        int items = 50;
        float costPerItem = 9.99f;
        float totalCost = items * costPerItem;
        char currency = '$';

        // Print variables
        System.out.println("Number of items: " + items);
        System.out.println("Cost per item: " + costPerItem + currency);
        System.out.println("Total cost = " + totalCost + currency);

        String txt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println("The length of the txt string is: " + txt.length());

        String txt2 = "Hello World";
        System.out.println(txt2.toUpperCase());   // Outputs "HELLO WORLD"
        System.out.println(txt2.toLowerCase());   // Outputs "hello world"

        String txt3 = "Please locate where 'locate' occurs!";
        System.out.println(txt3.indexOf("locate")); // Outputs 7

        String txt4 = "Hello";
        System.out.println(txt4.charAt(0));  // H
        System.out.println(txt4.charAt(4));  // o

        String txt11 = "Hello";
        String txt21 = "Hello";

        String txt31 = "Greetings";
        String txt41 = "Great things";

        System.out.println(txt11.equals(txt21));  // true
        System.out.println(txt31.equals(txt41));  // false

        String txt5 = "   Hello World   ";
        System.out.println("Before: [" + txt5 + "]");
        System.out.println("After:  [" + txt5.trim() + "]");

        var cars = new ArrayList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");
        System.out.println(cars);
        // Get first item of cars
        System.out.println("Get item of cars: " + cars.get(1));

        // Using for loop:
        System.out.println("Using for loop: ");
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i));
        }
        // Using forEach loop:
        System.out.println("Using forEach loop: ");
        for (String car : cars) {
            System.out.println(car);
        }

        // Using forEach Lambda
        System.out.println("Using forEach Lambda: ");
        // cars.forEach(System.out::println);
        cars.forEach(car -> System.out.println(car));

        int n = 5;
        int fact = 1;
        int total = 0;

        for (int i = 1; i <= n; i++) {
            fact *= i;
            total = fact *= i;
        }

        System.out.println("Factorial of " + n + " is " + fact);
        System.out.println("Total is:  " + total);

        int[] myNum2 = {10, 20, 30, 40};
        System.out.println(myNum2[0]);
        for (int i = 0; i < myNum2.length; i++) {
            System.out.println(myNum2[i]);
        }

        for (int j : myNum2) {
            System.out.println(j);
        }

        String[] seats = {"Jenny", "Liam", "Angie", "Bo"};

        for (int i = 0; i < seats.length; i++) {
            System.out.println("Seat number " + i + " is taken by " + seats[i]);
        }

        int[][] myNumbers = { {1, 4, 2}, {3, 6, 8} };
        System.out.println(myNumbers[1][2]); // Outputs 8
        System.out.println("Multi Arrays: ");
        for (int i = 0; i < myNumbers.length; i++) {
            System.out.println("Row " + (i + 1) + " is :");
            for (int j = 0; j < myNumbers[i].length; j++) {
                System.out.print(myNumbers[i][j] + " ");
            }
            System.out.println("---");
        }

        myMethod();
        int number = 200, number2 = 600;
        System.out.println("Multi number of methods is: "+ myMethod2(number, number2));

        int myNum11 = plusMethod(8, 5);
        double myNum22 = plusMethod(4.3, 6.26);
        System.out.println("int: " + myNum11);
        System.out.println("double: " + myNum22);

        // How to use Calculator class
        int sum1 = Calculator.plus(10, 20);
        double sum2 = Calculator.plus(5.5, 3.3);
        System.out.println("Sum of integers: " + sum1);
        System.out.println("Sum of doubles: " + sum2);


        // Tạo đối tượng Scanner, truyền System.in (Bàn phím) vào Constructor
        Scanner myObj = new Scanner(System.in);

        System.out.println("Nhập tên của bạn:");

        // Đọc nguyên 1 dòng văn bản (String) người dùng nhập
        String userName = myObj.nextLine();

        System.out.println("Tên bạn là: " + userName);


        Scanner myObj2 = new Scanner(System.in);

        System.out.println("Nhập tên, tuổi và lương:");

        String name = myObj2.nextLine(); // Chờ nhập chuỗi
        myObj2.nextLine(); // Dòng này đóng vai trò "ngốn" cái phím Enter bị thừa
        int age = myObj2.nextInt();      // Chờ nhập số nguyên
        double salary = myObj2.nextDouble(); // Chờ nhập số thập phân

        System.out.println("Tên: " + name + " - Tuổi: " + age + " - Lương: " + salary);
    }

    // Create method
    public static void myMethod() {
        System.out.println("I just got executed!");
    }

    static int myMethod2(int number, int number2)  {
        return number * number2;
    }

    static int plusMethod(int x, int y) {
        return x + y;
    }

    static double plusMethod(double x, double y) {
        return x + y;
    }
}

