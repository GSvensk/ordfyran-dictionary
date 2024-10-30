package com.gustavsvensk.ordfyran.parsers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class WordMerger {

  public static void main(String[] args) {
    // Define file paths
    String svSeFile = "./sv_SE.txt";
    String folketsFile = "./folkets.txt";
    String outputFile = "./lib/src/main/resources/dictionary.txt";

    try {
      // Read words from both files
      Set<String> svSeWords = readWords(svSeFile);
      Set<String> folketsWords = readWords(folketsFile);

      // Merge words, remove duplicates using a set, and sort alphabetically
      TreeSet<String> mergedWords = new TreeSet<>();
      mergedWords.addAll(svSeWords);
      mergedWords.addAll(folketsWords);

      // Save the merged words to a new text file
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
        for (String word : mergedWords) {
          writer.write(word);
          writer.newLine();
        }
      }

      System.out.println("Merged words have been saved to " + outputFile);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Function to read words from a text file
  private static Set<String> readWords(String filePath) throws IOException {
    Set<String> words = new HashSet<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        words.add(line);
      }
    }
    return words;
  }
}
