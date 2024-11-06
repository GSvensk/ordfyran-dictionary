package com.gustavsvensk.ordfyran.parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ParseOrdlistan {

  private static final Set<Character> LETTERS = new HashSet<>();

  static {
    for (char c : "abcdefghijklmnopqrstuvwxyzåäöéàáè".toCharArray()) {
      LETTERS.add(c);
    }
  }

  public static Set<String> parseWords(String dicFilePath) {
    Set<String> words = new HashSet<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(dicFilePath))) {

      String line;
      while ((line = reader.readLine()) != null) {
        String[] wordsArray = line.split("/");
        String word = wordsArray[0].trim();

        try {
          String description = wordsArray.length > 1 ? wordsArray[1].trim() : "";

          boolean discard = false;
          if (description.contains("Z")) {
            System.out.println("discarded " + word + " because its a non-word, part of a compound word");
            discard = true;
          }

          if (description.contains("r")) {
            System.out.println("discarded " + word + " because " + description + " is an abbreviation");
            discard = true;
          }

          for (char letter : word.toCharArray()) {
            if (!LETTERS.contains(letter)) {
              //System.out.println("discarded " + word + " because " + letter + " is not a lowercase letter");
              discard = true;
              break;
            }
          }

          if (word.length() > 5) {
            discard = true;
          }

          var weirdSet = Arrays.stream("abcdefghjklmnopqrstuvwxyzäéàáè".split("")).collect(Collectors.toSet());
          if (weirdSet.contains(word)) {
            System.out.println("discarded " + word + " because it is in weird set");
            discard = true;
          }

          if (!discard) {
            words.add(word);
          }

        } catch (Exception e) {
          System.out.println("failed " + line);
          System.out.println("failed " + wordsArray.length);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return words;
  }
}