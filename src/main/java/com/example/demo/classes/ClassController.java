package com.example.demo.classes;

public class ClassController {
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
    }
}

