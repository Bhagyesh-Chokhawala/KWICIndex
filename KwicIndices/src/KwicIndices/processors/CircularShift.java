package KwicIndices.processors;

import framework.ResourceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * This class is to generate a list of all circular shifts based on the string given.
 */
public class CircularShift {
    public static final String DELIMITER = ResourceManager.getResources().getPropertyOrDefault("KwicIndices.delimiter","a");
    private final String _line;
    private WordsToIgnore _wordsToIgnore;
    /**
     * input should not be null
     * @param line
     */
    public CircularShift(String line) {
        assert(line != null);
        this._line = line;
        this._wordsToIgnore = WordsToIgnore.getWordsToIgnore();
    }

    public String[] getCircularShifts() {
        String[] words = this._line.split(DELIMITER);
        String[] shifts = new String[words.length];
        shifts[0] = this._line;

        for (int i=1;i<words.length;i++) {
            shifts[i] = removeNonAlphanumericInShift(this.getShiftedLine(i, words));
        }

        String[] filteredShifts = getShiftsWithoutIgnoredWordLeading(shifts);

        return filteredShifts;
    }

    private String getShiftedLine(int index, String[] words) {
        StringBuilder builder = new StringBuilder();

        for (int i=index;i<words.length;i++) {
            builder.append(words[i]);
            builder.append(DELIMITER);
        }
        for (int i=0;i<index;i++) {
            builder.append(words[i]);
            builder.append(DELIMITER);
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

    private String[] getShiftsWithoutIgnoredWordLeading(String[] shifts) {
        List<String> shiftList = new ArrayList<String>(Arrays.asList(shifts));

        Iterator<String> iter = shiftList.iterator();
        while (iter.hasNext()) {
            if (isShiftStartingWithIgnoredWord(iter.next())) {
                iter.remove();
            }
        }

        return shiftList.toArray(new String[shiftList.size()]);
    }

    private boolean isShiftStartingWithIgnoredWord(String line) {
        return this._wordsToIgnore.isWordIgnored(line.split(DELIMITER)[0]);
    }
    private String removeNonAlphanumericInShift(String shift) {
        String[] words = shift.split(DELIMITER);
        StringBuilder builder = new StringBuilder();

        for (String str : words) {
            if (str.trim().isEmpty()) {
                continue;
            } else {
                str = str.replaceAll(
                        "[^a-zA-Z0-9]", "");
                builder.append(str);
            }
            builder.append(DELIMITER);
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }
}
