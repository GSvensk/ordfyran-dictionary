package com.gustavsvensk.ordfyran.parsers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public final class BlacklistLoader {

  private static final String RESOURCE_NAME = "blacklist.txt";

  private BlacklistLoader() {
    // utility class
  }

  public static Set<String> load() {
    Set<String> blacklist = new HashSet<>();

    ClassLoader classLoader = BlacklistLoader.class.getClassLoader();
    try (InputStream is = classLoader.getResourceAsStream(RESOURCE_NAME)) {
      if (is == null) {
        throw new IllegalStateException("Resource not found: " + RESOURCE_NAME);
      }

      try (BufferedReader reader =
             new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

        String line;
        while ((line = reader.readLine()) != null) {
          line = line.trim();
          if (!line.isEmpty()) {
            blacklist.add(line);
          }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Failed to load blacklist", e);
    }

    return blacklist;
  }
}

