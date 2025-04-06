import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
  @author tanus
  @project lab3
  @class ConverterTest
  @version 1.0.0
  @since 06.04.2025 - 20.42
*/
class ConverterTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void convertToRoman() {
    }
    @Test
    void whenArabic_1_ThenRoman_I() {
        Assertions.assertEquals("I", Converter.convertToRoman(1));
    }

    @Test
    void whenArabic_6_ThenRoman_VI() {
        Assertions.assertEquals("VI", Converter.convertToRoman(6));
    }

    @Test
    void whenArabic_9_ThenRoman_IX() {
        Assertions.assertEquals("IX", Converter.convertToRoman(9));
    }

    @Test
    void whenArabic_11_ThenRoman_XI() {
        Assertions.assertEquals("XI", Converter.convertToRoman(11));
    }

    @Test
    void whenArabic_14_ThenRoman_XIV() {
        Assertions.assertEquals("XIV", Converter.convertToRoman(14));
    }

    @Test
    void whenArabic_15_ThenRoman_XV() {
        Assertions.assertEquals("XV", Converter.convertToRoman(15));
    }

    @Test
    void whenArabic_19_ThenRoman_XIX() {
        Assertions.assertEquals("XIX", Converter.convertToRoman(19));
    }

    @Test
    void whenArabic_23_ThenRoman_XXIII() {
        Assertions.assertEquals("XXIII", Converter.convertToRoman(23));
    }

    @Test
    void whenArabic_31_ThenRoman_XXXI() {
        Assertions.assertEquals("XXXI", Converter.convertToRoman(31));
    }

    @Test
    void whenArabic_33_ThenRoman_XXXIII() {
        Assertions.assertEquals("XXXIII", Converter.convertToRoman(33));
    }

    @Test
    void whenArabic_39_ThenRoman_XXXIX() {
        Assertions.assertEquals("XXXIX", Converter.convertToRoman(39));
    }

    @Test
    void whenArabic_44_ThenRoman_XLIV() {
        Assertions.assertEquals("XLIV", Converter.convertToRoman(44));
    }

    @Test
    void whenArabic_58_ThenRoman_LVIII() {
        Assertions.assertEquals("LVIII", Converter.convertToRoman(58));
    }

    @Test
    void whenArabic_77_ThenRoman_LXXVII() {
        Assertions.assertEquals("LXXVII", Converter.convertToRoman(77));
    }

    @Test
    void whenArabic_94_ThenRoman_XCIV() {
        Assertions.assertEquals("XCIV", Converter.convertToRoman(94));
    }

    @Test
    void whenArabic_123_ThenRoman_CXXIII() {
        Assertions.assertEquals("CXXIII", Converter.convertToRoman(123));
    }

    @Test
    void whenArabic_256_ThenRoman_CCLVI() {
        Assertions.assertEquals("CCLVI", Converter.convertToRoman(256));
    }

    @Test
    void whenArabic_349_ThenRoman_CCCXLIX() {
        Assertions.assertEquals("CCCXLIX", Converter.convertToRoman(349));
    }

    @Test
    void whenArabic_444_ThenRoman_CDXLIV() {
        Assertions.assertEquals("CDXLIV", Converter.convertToRoman(444));
    }

    @Test
    void whenArabic_777_ThenRoman_DCCLXXVII() {
        Assertions.assertEquals("DCCLXXVII", Converter.convertToRoman(777));
    }

    @Test
    void whenArabic_888_ThenRoman_DCCCLXXXVIII() {
        Assertions.assertEquals("DCCCLXXXVIII", Converter.convertToRoman(888));
    }

    @Test
    void whenArabic_1234_ThenRoman_MCCXXXIV() {
        Assertions.assertEquals("MCCXXXIV", Converter.convertToRoman(1234));
    }

    @Test
    void whenArabic_2024_ThenRoman_MMXXIV() {
        Assertions.assertEquals("MMXXIV", Converter.convertToRoman(2024));
    }

    @Test
    void whenArabic_3998_ThenRoman_MMMCMXCVIII() {
        Assertions.assertEquals("MMMCMXCVIII", Converter.convertToRoman(3998));
    }

    @Test
    void whenArabic_4001_ThenException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Converter.convertToRoman(4001));
    }
}