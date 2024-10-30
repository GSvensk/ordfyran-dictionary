package com.gustavsvensk.ordfyran.dictionaries;

public interface Dictionary {

    enum Language {
        SWEDISH,
        ENGLISH
    }

    boolean contains(String word);

    Language getLanguage();
}
