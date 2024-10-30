package com.gustavsvensk.ordfyran.parsers;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ParseFolkets {

  private static final Set<Character> LETTERS = new HashSet<>();

  static {
    for (char c : "abcdefghijklmnopqrstuvwxyzåäöéàáè".toCharArray()) {
      LETTERS.add(c);
    }
  }

  public static Set<String> parseWords(String xmlFilePath) {
    Set<String> words = new HashSet<>();
    try {
      // Load the XML file
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(xmlFilePath);

      // Normalize the XML structure
      document.getDocumentElement().normalize();

      NodeList wordNodes = document.getElementsByTagName("word");

      for (int i = 0; i < wordNodes.getLength(); i++) {
        Element wordElement = (Element) wordNodes.item(i);
        String wordValue = wordElement.getAttribute("value");
        String wordClass = wordElement.getAttribute("class");

        // Skip words longer than 5 characters or abbreviations
        if (wordValue.length() > 5 || "abbrev".equals(wordClass)) {
          continue;
        }
        var weirdSet = Arrays.stream("abcdefghjklmnopqrstuvwxyzäéàáè".split("")).collect(Collectors.toSet());
        if (weirdSet.contains(wordValue)) {
          System.out.println("discarded " + wordValue + " because it is in weird set");
          continue;
        }

        boolean discard = false;

        for (char letter : wordValue.toCharArray()) {
          if (!LETTERS.contains(letter)) {
            System.out.println("discarded " + wordValue + " because " + letter + " not a letter or lowercase letter");
            discard = true;
            break;
          }
        }

        if (discard) {
          continue;
        }

        if (!wordValue.isEmpty()) {
          words.add(wordValue);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return words;
  }
}