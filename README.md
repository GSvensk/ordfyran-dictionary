# Ordfyran Dictionaries

This project provides a Swedish dictionary. It merges and modifies existing Swedish dictionaries under compatible open-source licenses.

## Features

- Comprehensive Swedish word list with spell-checking support
- License-compliant combination of Creative Commons and LGPL-licensed works

### Prerequisites

- Java 21 or higher

### Installation

```bash
./gradlew clean build
```

```bash
./gradlew publishToMavenLocal
```


## Analysis

Letter frequency
```bash
cat assets/words/dictionary.txt | tr '[:upper:]' '[:lower:]' | tr -d '[:punct:]' | fold -w1 | sort | uniq -c | sort -rn | awk '{print $2, $1}'
```


Prefix frequency
```bash
cat assets/words/dictionary.txt | tr '[:upper:]' '[:lower:]' | tr -d '[:punct:]' | cut -c1-2 | sort | uniq -c | sort -rn | awk '{print $2, $1}'
```