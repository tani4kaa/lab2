package org.example;

public class IsoscelesTriangle {
    private double base;
    private double side;

    public IsoscelesTriangle(double base, double side) {
        if (base <= 0 || side <= 0) {
            throw new IllegalArgumentException("Сторони повинні бути додатними.");
        }
        if (base >= 2 * side) {
            throw new IllegalArgumentException("Задані сторони не утворюють рівнобедрений трикутник.");
        }
        this.base = base;
        this.side = side;
    }

    public double getBase() {
        return base;
    }

    public double getSide() {
        return side;
    }

    // 1. Обчислення периметра
    public double getPerimeter() {
        return base + 2 * side;
    }

    // 2. Обчислення площі
    public double getArea() {
        double height = Math.sqrt(side * side - (base / 2) * (base / 2));
        return (base * height) / 2;
    }

    // 3. Обчислення висоти
    public double getHeight() {
        return Math.sqrt(side * side - (base / 2) * (base / 2));
    }

    // 4. Перевірка, чи це рівнобедрений трикутник
    public boolean isIsosceles() {
        return true;
    }

    // 5. Зміна сторін (сеттери)
    public void setSides(double base, double side) {
        if (base <= 0 || side <= 0) {
            throw new IllegalArgumentException("Сторони повинні бути додатними.");
        }
        if (base >= 2 * side) {
            throw new IllegalArgumentException("Задані сторони не утворюють рівнобедрений трикутник.");
        }
        this.base = base;
        this.side = side;
    }
}
