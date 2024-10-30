package com.gustavsvensk.ordfyran.dictionaries;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SwedishDictionaryTest {

  private final SwedishDictionary underTest = new SwedishDictionary();

  @Test
  void shouldHandleLowercase() {
    Assertions.assertTrue(underTest.contains("test"));
  }

  @Test
  void shouldHandleUppercase() {
    Assertions.assertTrue(underTest.contains("TEST"));
  }

  @Test
  void getLanguage() {
    Assertions.assertEquals(Dictionary.Language.SWEDISH, underTest.getLanguage());
  }
}

