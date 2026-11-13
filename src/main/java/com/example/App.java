package com.example;

public class App {

    public static int add(int a, int b) {
        return a + b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        System.out.println("2 + 4 = " + add(2, 4));
        System.out.println("2 * 3 = " + multiply(2, 3));
    }
}
