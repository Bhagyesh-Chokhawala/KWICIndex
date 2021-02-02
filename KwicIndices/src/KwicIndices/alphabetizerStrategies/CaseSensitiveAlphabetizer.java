package KwicIndices.alphabetizerStrategies;

import dataStructure.LineStore;

import java.util.Arrays;

public class CaseSensitiveAlphabetizer implements Alphabetizer {
    @Override
    public String[] getSortedLines()
    {
        String [] shiftedLines = LineStore.getInstance().getShiftedLines();
        Arrays.sort(shiftedLines);
        return shiftedLines;
    }
}
