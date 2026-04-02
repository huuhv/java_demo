package com.example.demo.controller;

//public class ThreadController extends Thread {
//    public static int amount = 0;

//    public static void main(String[] args) {
//        ThreadController threadController = new ThreadController();
//        threadController.start();
//        // Wait for the thread to finish
//        while(threadController.isAlive()){
//            try {
//                Thread.sleep(1000);
//                System.out.println("Waiting for thread to finish...");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("Amount of main: " + amount);
//        amount++;
//        System.out.println("Amount of second: " + amount);
//
//    }

    public class ThreadController implements Runnable {
        public static int amount = 0;

        public static void main(String[] args) {
            ThreadController threadController = new ThreadController();
            // Wait for the thread to finish
            Thread thread = new Thread(threadController);
            thread.start();
            while(thread.isAlive()){
                try {
                    Thread.sleep(1000);
                    System.out.println("Waiting for thread to finish...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Amount of main: " + amount);
            amount++;
            System.out.println("Amount of second: " + amount);

        }

    public void run() {
        System.out.println("ThreadController run");
        amount++;
    }
}
