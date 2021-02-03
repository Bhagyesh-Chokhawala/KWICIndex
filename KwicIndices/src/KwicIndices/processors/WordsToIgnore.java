package KwicIndices.processors;

import java.util.List;

public interface WordsToIgnore {
    void addWordToIgnore(String word);
    void addWordToIgnore(List<String> words);
    void removeWordToIgnore(String word);
    boolean isWordIgnored(String word);
}
