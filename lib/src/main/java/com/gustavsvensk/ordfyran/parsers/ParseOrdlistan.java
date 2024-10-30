package com.gustavsvensk.ordfyran.parsers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

  public static void main(String[] args) {
    try (BufferedReader reader = new BufferedReader(new FileReader("./ordlistan/swedish.dic"));
         BufferedWriter writer = new BufferedWriter(new FileWriter("./sv_SE.txt"))) {

      String line;
      while ((line = reader.readLine()) != null) {
        String[] words = line.split("/");
        String word = words[0].trim();

        try {
          String description = words.length > 1 ? words[1].trim() : "";

          boolean discard = false;
          if ("A".equals(description)) {
            //System.out.println("discarded " + word + " because " + description + " is a name");
            discard = true;
          }

          if ("X".equals(description)) {
            System.out.println("discarded " + word + " because " + description + " is city or company");
            discard = true;
          }

          if ("r".equals(description)) {
            System.out.println("discarded " + word + " because " + description + " is an abbreviation");
            discard = true;
          }

          for (char letter : word.toCharArray()) {
            if (!LETTERS.contains(letter)) {
              System.out.println("discarded " + word + " because " + letter + " is not a lowercase letter");
              discard = true;
              break;
            }
          }

          if (word.length() > 5) {
            //System.out.println("discarded " + word + " because too long");
            discard = true;
          }

          var weirdSet = Arrays.stream("abcdefghjklmnopqrstuvwxyzäéàáè".split("")).collect(Collectors.toSet());
          if (weirdSet.contains(word)) {
            System.out.println("discarded " + word + " because it is in weird set");
            discard = true;
          }

          if (!discard) {
            writer.write(word + "\n");
          }

        } catch (Exception e) {
          System.out.println("failed " + line);
          System.out.println("failed " + words.length);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
