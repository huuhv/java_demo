package com.example.demo.controller;

import com.example.demo.classes.Calculator;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

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


//        // Tạo đối tượng Scanner, truyền System.in (Bàn phím) vào Constructor
//        Scanner myObj = new Scanner(System.in);
//
//        System.out.println("Nhập tên của bạn:");
//
//        // Đọc nguyên 1 dòng văn bản (String) người dùng nhập
//        String userName = myObj.nextLine();
//
//        System.out.println("Tên bạn là: " + userName);
//
//
//        Scanner myObj2 = new Scanner(System.in);
//
//        System.out.println("Nhập tên, tuổi và lương:");
//
//        String name = myObj2.nextLine(); // Chờ nhập chuỗi
//        myObj2.nextLine(); // Dòng này đóng vai trò "ngốn" cái phím Enter bị thừa
//        int age = myObj2.nextInt();      // Chờ nhập số nguyên
//        double salary = myObj2.nextDouble(); // Chờ nhập số thập phân

//        System.out.println("Tên: " + name + " - Tuổi: " + age + " - Lương: " + salary);

        LocalDate myDate = LocalDate.now(); // Ví dụ in ra: 2026-03-19
        System.out.println(myDate);
        LocalTime myTime = LocalTime.now(); // Ví dụ in ra: 16:45:47.123456
        System.out.println(myTime);
        LocalDateTime myObj3 = LocalDateTime.now(); // Ví dụ in ra: 2026-03-19T16:45:47.123456
        System.out.println(myObj3);

        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Trước khi format: " + myDateObj);

        // Tạo một khuôn mẫu định dạng (Pattern) theo ý muốn
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Ép object thời gian vào khuôn mẫu đó để đẻ ra chuỗi String hiển thị
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println("Sau khi format: " + formattedDate);

        File myFile = new File("C:\\Users\\hoang.van.huu\\Downloads\\ZCREATE_EKES.ABAP2");
        try {
            // IDE biết FileReader có thể gây ra FileNotFoundException
            FileReader fr = new FileReader(myFile);
            // Get content of files
            BufferedReader br = new BufferedReader(fr);
            System.out.println(br.toString());
            System.out.println("Đọc file thành công!");
        } catch (FileNotFoundException e) {
            // Nếu lỗi xảy ra, code sẽ nhảy vào đây chứ không làm sập hệ thống
            System.out.println("Cảnh báo: Không tìm thấy file.");
            // e.printStackTrace(); // In ra chi tiết lỗi để debug
        }

        // Java không ép bạn phải try-catch đoạn code này, dù nó chắc chắn sẽ lỗi
        String name = null;
        try {
            System.out.println(name.length());
        } catch (NullPointerException e) {
            System.out.println("Cảnh báo: Biến name đang là null, không thể gọi hàm length() trên nó.");
            // e.printStackTrace();
        }

        // 1. Khai báo mở file ngay trong ngoặc đơn của try()
        try (FileOutputStream output = new FileOutputStream("filename.txt", true)) {

            output.write("Hello".getBytes());
            // add \n\r to line
            output.write("\n".getBytes());
            output.write("Hello 2".getBytes());
            output.write("\n".getBytes());
            output.write("Hello 3".getBytes());
            output.write("\n".getBytes());
            System.out.println("Ghi file thành công!");

            // 2. BẠN KHÔNG CẦN VIẾT output.close() Ở ĐÂY NỮA! Java sẽ tự lo việc đó.

        } catch (IOException e) {
            System.out.println("Có lỗi khi ghi file.");
        }

        File myObj = new File("filename2.txt"); // Specify the filename
        try (FileWriter writer = new FileWriter(myObj, true)) {
            writer.write("Hello, this is a test file. Xin chào, đây là file test");
            writer.write("\n"); // Add a new line
            writer.write("This file is created using FileWriter.");
            writer.write("\n");
            writer.write("Hello, this is a test file. Cũng là file test thôi");
            writer.write("\n");
            writer.write("This file is created using FileWriter.");
            writer.write("\n");
            System.out.println("File created and written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        // try-with-resources: Scanner will be closed automatically
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            System.out.println("Absolute path: " + myObj.getAbsolutePath());
            System.out.println("Writeable: " + myObj.canWrite());
            System.out.println("Readable " + myObj.canRead());
            System.out.println("File size in bytes " + myObj.length());
        } else {
            System.out.println("The file does not exist.");
        }

//        File myObj2 = new File("filename.txt");
//        if (myObj2.delete()) {
//            System.out.println("Deleted the file: " + myObj2.getName());
//        } else {
//            System.out.println("Failed to delete the file.");
//        }

        System.out.println("===========FileInputStream============");
        // try-with-resources: FileInputStream will be closed automatically
        try (FileInputStream input = new FileInputStream("filename2.txt")) {

            int i;  // variable to store each byte that is read

            // Read one byte at a time until end of file (-1 means "no more data")
            while ((i = input.read()) != -1) {
                // Convert the byte to a character and print it to the console
                System.out.print((char) i);
            }

        } catch (IOException e) {
            // If an error happens (e.g. file not found), print an error message
            System.out.println("Error reading file.");
        }
        System.out.println("\n");
        System.out.println("============FileInputStream===========");


        System.out.println("================BufferedReader=FileReader======****************");
        // 1. Try-with-resources để tự động đóng file
        try (BufferedReader br = new BufferedReader(new FileReader("filename.txt"))) {
            String line;

            // 2. Đọc từng dòng (line by line)
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        System.out.println("==================BufferedReader=FileReader====****************");

        System.out.println("*********=========BufferedReader=FileReader=============****************");
        // 1. Try-with-resources để tự động đóng file
        try (BufferedReader br = new BufferedReader(new FileReader("filename2.txt"))) {
            String line;

            // 2. Đọc từng dòng (line by line)
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        System.out.println("*********=============BufferedReader=FileReader=========****************");

//        Scanner
//                  Tệp văn bản nhỏ / Cần tách dữ liệu.
//                  Có các hàm cực kỳ tiện như nextInt(), next(), giúp tách các số liệu hoặc từ ngữ rất dễ dàng. Tuy nhiên, nó khá chậm và tốn bộ nhớ.
//        BufferedReader
//                  Tệp văn bản lớn / Đọc theo dòng.
//                  Rất nhanh, quản lý bộ nhớ tốt. Nếu bạn chỉ cần đọc text thuần túy từ file 1GB, đây là lựa chọn số 1.
//        FileInputStream
//                  Tệp nhị phân (ảnh, video, âm thanh, PDF).
//                  Đọc dữ liệu thô (raw byte), không quan tâm đến mã hóa ký tự (encoding).


        try (BufferedWriter bw = new BufferedWriter(new FileWriter("filename.txt", true))) {
            bw.newLine();
            bw.write("Appended line");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }

//        FileWriter
//                    Tệp văn bản nhỏ, đơn giản.
//                    Ngắn gọn, dễ viết code. Nhưng chậm nếu phải ghi nhiều vì không có buffer.
//        BufferedWriter
//                    Tệp văn bản lớn, ghi nhiều dòng.
//                    Hiệu suất cao (nhờ buffer). Có hàm newLine() cực kỳ tiện lợi để tách dòng.
//        FileOutputStream
//                    Tệp nhị phân (ảnh, âm thanh...).
//                    Xử lý dữ liệu thô (raw byte), không dùng cho text trừ khi bắt buộc.

        System.out.println("===========ArrayList============");
        ArrayList<String> cars2 = new ArrayList<String>();
        cars2.add("Volvo");
        cars2.add("BMW");
        cars2.add("Ford");
        cars2.add("Mazda");
        cars2.add("Jenny");
        cars2.add(0, "Liam");
        System.out.println(cars2);
        // Get an iterator for ArrayList
        Iterator itr = cars2.iterator();
        while (itr.hasNext()) {
            String car = (String) itr.next();
            System.out.println(car);
        }
        System.out.println(cars2.get(cars2.size() - 1));
        System.out.println("===========HashSet============");
        HashSet<String> cars3 = new HashSet<String>();
        cars3.add("Volvo");
        cars3.add("BMW");
        cars3.add("Ford");
        cars3.add("Mazda");
        cars3.add("BMW");
        System.out.println(cars3);
        // Get an iterator for HashSet
        Iterator itr2 = cars3.iterator();
        while (itr2.hasNext()) {
            String car = (String) itr2.next();
            System.out.println(car);
        }

        System.out.println("===========HashMap============");
        HashMap<String, String> cars4 = new HashMap<String, String>();
        cars4.put("England", "London");
        cars4.put("France", "Paris");
        cars4.put("Germany", "Berlin");
        cars4.put("Vietnam", "Hanoi");
        System.out.println(cars4);
        System.out.println(cars4.get("England"));
        // Get iterator for HashMap
        Iterator itr3 = cars4.keySet().iterator();
        while (itr3.hasNext()) {
            String car = (String) itr3.next();
            System.out.println(car);
        }

        System.out.println("===========LinkedList============");
        LinkedList<String> cars5 = new LinkedList<String>();
        cars5.add("Volvo");
        cars5.add("BMW");
        cars5.add("Ford");
        cars5.add("Mazda");
        System.out.println(cars5);
        // Get an iterator for LinkedList
        Iterator itr4 = cars5.iterator();
        while (itr4.hasNext()) {
            String car = (String) itr4.next();
            System.out.println(car);
        }

        System.out.println("===========List Sorting============");
        Collections.sort(cars2);  // Sort cars

        for (String i : cars2) {
            System.out.println(i);
        }

        ArrayList<Integer> myNumberArrs = new ArrayList<Integer>();
        myNumberArrs.add(33);
        myNumberArrs.add(15);
        myNumberArrs.add(20);
        myNumberArrs.add(34);
        myNumberArrs.add(8);
        myNumberArrs.add(12);

        System.out.println(myNumberArrs);
        Collections.sort(myNumberArrs);  // Sort myNumberArrs
        System.out.println(myNumberArrs);

        for (int i : myNumberArrs) {
            System.out.println(i);
        }
        myNumberArrs.sort(Collections.reverseOrder());  // Sort myNumberArrs
        System.out.println(myNumberArrs);

        TreeMap<String, String> capitalCities = new TreeMap<>();
        capitalCities.put("England", "London");
        capitalCities.put("India", "New Dehli");
        capitalCities.put("Austria", "Wien");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("USA", "Washington DC");
        capitalCities.put("Norway", "Oslo"); // Duplicate

        System.out.println(capitalCities);

        ArrayList<String> names = new ArrayList<>();
        names.add("Liam");
        names.add("Jenny");
        names.add("Kasper");
        names.add("Angie");

        Collections.sort(names); // must be sorted first
        System.out.println(names);
        int index = Collections.binarySearch(names, "Angie");
        System.out.println("Angie is at index: " + index);

        System.out.println("===========Lambda Expressions============");
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);
        numbers.forEach((m) -> { System.out.println(m); });

        Consumer<Integer> method = (l) -> { System.out.println(l); };
        numbers.forEach(method);

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

