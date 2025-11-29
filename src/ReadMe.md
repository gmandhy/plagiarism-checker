# Plagiarism Checker (Java, JavaFX, NLP)

This project implements a multi-stage plagiarism detection system using Natural Language Processing (NLP) techniques. It provides both a command-line interface (CLI) and a JavaFX graphical application. The checker analyzes two documents using semantic and lexical similarity methods and produces a detailed HTML report including matched phrases and a semantic heatmap.

---

## Overview

The system performs layered text analysis through:

### Semantic Analysis
- TF-IDF (Term Frequency–Inverse Document Frequency)
- Cosine similarity between TF-IDF vectors
- Text normalization, tokenization, stopword removal, and stemming

### Lexical Analysis
- Shingling (n-grams)
- Jaccard similarity over shared shingles

### Phrase Detection
- Exact matching of 2-word and 3-word repeated phrases

### HTML Report Generation
- Produces a structured report (`report.html`)
- Includes similarity scores, phrase matches, and a red-scaled semantic heatmap
- Automatically opens in the browser when launched from the JavaFX interface

---

## Running the Project

### Command-Line Interface

To run the project in command-line mode, compile all Java source files and then execute the Main class with two document paths as arguments. The program will process both documents, compute similarity scores, and generate an HTML report named report.html in the project directory.

---

### JavaFX Graphical Interface

To use the graphical interface, open IntelliJ and configure the PlagiarismApp run configuration. Add the required JavaFX VM options that point to your local JavaFX installation. Once the configuration is set, run PlagiarismApp. A window will appear allowing you to select two input files and start the analysis. When processing finishes, the application automatically opens the generated report.html file in your default browser.

## Requirements

This project relies on standard Java tooling along with JavaFX for the graphical interface. The following components are required to build and run the system successfully:

- A compatible Java Development Kit (JDK), version 17 or later.  
  The project has been validated on JDK 24.
- A JavaFX SDK installation corresponding to your JDK version.  
  JavaFX must be available locally so that the JavaFX modules can be added through VM options when running the GUI.
- An IDE or build environment capable of managing JavaFX module paths.  
  IntelliJ IDEA is recommended due to its straightforward configuration of JavaFX applications.
- A operating system where JavaFX is supported, such as Windows, macOS, or Linux.
- Standard UTF-8 plain text files to be used as input documents for analysis.  
  Other formats (PDF, DOCX, HTML, etc.) should be converted to plain text before use.
- Sufficient permissions for the application to read input files and write the generated HTML report to the project directory.

These requirements ensure that both the command-line interface and the JavaFX GUI run as intended, with full support for the NLP pipeline and HTML report generation.


## How the System Works

The plagiarism checker operates through a multi-stage Natural Language Processing pipeline designed to capture both literal overlap and deeper semantic similarity between two documents. The process begins with text preprocessing, where the input documents are normalized, converted to lowercase, stripped of punctuation, tokenized into individual terms, and filtered using a stopword list to remove high-frequency, low-information words. After this, the tokens undergo stemming using the Porter stemming algorithm to reduce words to their base forms, improving the consistency of comparison across morphological variants.

Once preprocessing is complete, the semantic similarity analysis begins. Each document is converted into a TF-IDF vector, where term frequency measures how often a term appears in the document, and inverse document frequency measures how unique that term is relative to the pair. These values combine to emphasize terms that are meaningful for comparison. Cosine similarity is then applied to the TF-IDF vectors, producing a semantic similarity score that captures conceptual overlap rather than exact wording.

The system also calculates lexical similarity through shingling. Each document is transformed into overlapping sequences of fixed-length word groups (typically two words). These shingles are compared using the Jaccard similarity coefficient, which measures the proportion of shared shingles between the documents. This allows the system to detect literal copying even when the surrounding context differs. In addition to this, the checker identifies exact matching phrases of two and three words, providing insight into specific repeated segments.

The final output is an HTML report that includes both similarity scores, matched phrase lists, and a semantic heatmap. The heatmap highlights words in the original documents according to their TF-IDF significance, allowing readers to visually identify semantically important regions and repeated concepts. This combination of semantic modeling, lexical comparison, and visual explanation provides a thorough and interpretable evaluation of similarity between the two documents.

---

## Supported File Types

The system is designed to accept plain text files as its primary input format. Standard UTF-8 text files are fully supported, and the system reads their content directly through the built-in file loader. Since the analysis pipeline operates on unformatted text, files should either be native text files or exported from other formats into plain text prior to analysis. The system does not currently process PDF, Word documents, HTML files, or images; these formats would need to be converted manually into plain text before use. 

Although limited to text input for now, the internal architecture of the system allows for future extension. Additional parsers for formats such as PDF or DOCX can be integrated into the file loader without modifying the rest of the pipeline. In its present state, the system expects two standard text documents and performs all subsequent operations based solely on the textual content extracted from these files.

## Example Output

If run properly, the output should look something like this:

![Report Screenshot](src/img.png)



