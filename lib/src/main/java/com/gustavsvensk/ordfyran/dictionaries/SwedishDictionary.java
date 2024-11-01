package com.gustavsvensk.ordfyran.dictionaries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SwedishDictionary implements Dictionary {

  private static final Logger logger = LoggerFactory.getLogger(SwedishDictionary.class);
  private static final Set<String> WORDS = new HashSet<>();
  static {
    long start = System.currentTimeMillis();
    try (InputStream inputStream = SwedishDictionary.class.getClassLoader().getResourceAsStream("dictionary.txt")) {
      if (inputStream == null) {
        logger.error("dictionary.txt not found");
        throw new FileNotFoundException("dictionary.txt");
      }

      try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        String line;
        while ((line = reader.readLine()) != null) {
          WORDS.add(line.trim().toLowerCase()); // Normalize to lowercase while loading
        }
      }

      logger.info("Loaded {} words into dictionary in {} seconds", WORDS.size(), (System.currentTimeMillis() - start) / 1000.0);
    } catch (IOException e) {
      logger.error("Failed to load dictionary", e);
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public boolean contains(String word) {
    return WORDS.contains(word.toLowerCase());
  }

  @Override
  public Language getLanguage() {
    return Language.SWEDISH;
  }

  @Override
  public Set<String> getWords() {
    return WORDS;
  }
}
