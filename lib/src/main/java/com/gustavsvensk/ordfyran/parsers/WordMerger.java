package com.gustavsvensk.ordfyran.parsers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Set;
import java.util.TreeSet;

public class WordMerger {

  public static void main(String[] args) {
    String outputFile = "./lib/src/main/resources/dictionary.txt";

    // Read words from both files
    Set<String> svSeWords = ParseOrdlistan.parseWords("./ordlistan/swedish.dic");
    Set<String> folketsWords = ParseFolkets.parseWords("./folkets/folkets_sv_en_public.xml");

    // Merge words, remove duplicates using a set, and sort alphabetically
    TreeSet<String> mergedWords = new TreeSet<>();
    mergedWords.addAll(svSeWords);
    mergedWords.addAll(folketsWords);

    // Save the merged words to a new text file
    saveWordsToFile(mergedWords, outputFile);

    System.out.println("Merged words have been saved to " + outputFile);
  }


  public static void saveWordsToFile(Set<String> words, String txtFile) {
    // Sort the words alphabetically
    TreeSet<String> sortedWords = new TreeSet<>(words);

    // Save to plain text file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile))) {
      for (String word : sortedWords) {
        writer.write(word);
        writer.newLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
