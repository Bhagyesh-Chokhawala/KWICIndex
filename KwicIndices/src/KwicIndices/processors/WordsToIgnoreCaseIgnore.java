package KwicIndices.processors;

import resources.Constants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class WordsToIgnoreCaseIgnore implements WordsToIgnore {
    private Set<String> _wordsToIgnore;
    private static WordsToIgnoreCaseIgnore _instatnce;
    private WordsToIgnoreCaseIgnore() {
        this._wordsToIgnore = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        this._wordsToIgnore.addAll(Constants.SHIFTIGNOREWORDS);
    }

    public static WordsToIgnoreCaseIgnore getWordsToIgnore() {
        if (_instatnce == null) {
            _instatnce = new WordsToIgnoreCaseIgnore();
        }

        return _instatnce;
    }

    public void addWordToIgnore(String word) {
        assert(word != null);
        this._wordsToIgnore.add(word);
    }
    public void addWordToIgnore(List<String> words) {
        assert(words != null);
        this._wordsToIgnore.addAll(words);
    }

    public void removeWordToIgnore(String word) {
        assert(word != null);
        this._wordsToIgnore.remove(word);
    }

    public boolean isWordIgnored(String word) {
        assert(word != null);
        return this._wordsToIgnore.contains(word);
    }
}