package KwicIndices.processors;

import resources.Constants;

import java.util.HashSet;
import java.util.List;

public class WordsToIgnore {
    private HashSet<String> _wordsToIgnore;
    private static WordsToIgnore _instatnce;
    private WordsToIgnore() {
        this._wordsToIgnore = new HashSet<String>();
        this._wordsToIgnore.addAll(Constants.SHIFTIGNOREWORDS);
    }

    public static WordsToIgnore getWordsToIgnore() {
        if (_instatnce == null) {
            _instatnce = new WordsToIgnore();
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