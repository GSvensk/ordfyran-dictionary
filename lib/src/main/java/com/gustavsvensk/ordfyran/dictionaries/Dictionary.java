package com.gustavsvensk.ordfyran.dictionaries;

import java.util.Set;

public interface Dictionary {

    enum Language {
        SWEDISH,
        ENGLISH
    }

    boolean contains(String word);

    Language getLanguage();

    Set<String> getWords();
}
