package edu.mamontova;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
  @author tanus
  @project lab4
  @class ConverterTest
  @version 1.0.0
  @since 13.04.2025 - 21.16
*/class ConverterTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void romanToArabic() {
    }

    @Test
    void testNullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Converter.romanToArabic(null);
        });
        assertEquals("Input cannot be null or empty", exception.getMessage());
    }

    @Test
    void testEmptyInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Converter.romanToArabic("");
        });
        assertEquals("Input cannot be null or empty", exception.getMessage());
    }

    @Test    void whenInputHasSpecialCharacters_thenThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {            Converter.romanToArabic("MM#IV");
        });        assertTrue(exception.getMessage().contains("cannot be converted"));
    }

    @Test
    void testInvalidCharacters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Converter.romanToArabic("ABC");
        });
        assertEquals("ABC cannot be converted to a Roman Numeral", exception.getMessage());
    }

    @Test
    void testPartialInvalidCombination() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Converter.romanToArabic("MXZ");
        });
        assertEquals("MXZ cannot be converted to a Roman Numeral", exception.getMessage());
    }

    @Test
    void testIncorrectOrdering() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Converter.romanToArabic("IIV");
        });
        assertEquals("IIV cannot be converted to a Roman Numeral", exception.getMessage());
    }

    @Test
    void testNonSubtractiveInvalidPattern() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Converter.romanToArabic("VX");
        });
        assertEquals("VX cannot be converted to a Roman Numeral", exception.getMessage());
    }


    @Test
    void testLowerCaseInputWithInvalidSymbol() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Converter.romanToArabic("xivz");
        });
        assertEquals("xivz cannot be converted to a Roman Numeral", exception.getMessage());
    }

    @Test
    void testOnlyInvalidSymbol() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Converter.romanToArabic("Z");
        });
        assertEquals("Z cannot be converted to a Roman Numeral", exception.getMessage());
    }

    @Test
    void testCombinationOfValidAndInvalidSymbols() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Converter.romanToArabic("XMIY");
        });
        assertEquals("XMIY cannot be converted to a Roman Numeral", exception.getMessage());
    }
}