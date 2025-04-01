package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

/*
  @author tanus
  @project lab2
  @class IsoscelesTriangleTest
  @version 1.0.0
  @since 01.04.2025 - 10.26
*/

public class IsoscelesTriangleTest {

    IsoscelesTriangle triangle = new IsoscelesTriangle(6, 5);

    @Test
    public void getPerimeter() {
        assertEquals(16.0, triangle.getPerimeter(), 0.001);

    }

    @Test
    public void getArea() {
        assertEquals(12.0, triangle.getArea(), 0.5); // допускаємо похибку
    }

    @Test
    public void getHeight() {
        assertEquals(4.0, triangle.getHeight(), 0.1); // допускаємо похибку
    }

    @Test
    public void isIsosceles() {
        IsoscelesTriangle triangle = new IsoscelesTriangle(4, 5);
        assertTrue(triangle.isIsosceles());
    }

    @Test
    public void setSides() {
        triangle.setSides(8, 6);
        assertEquals(8, triangle.getBase(), 0.001);
        assertEquals(6, triangle.getSide(), 0.001);
    }
}