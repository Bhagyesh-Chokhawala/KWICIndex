package KwicIndices.alphabetizerStrategies;

        import dataStructure.LineStore;

        import java.util.Arrays;
        import java.util.Comparator;

public class CaseIgnoreAlphabetizer implements Alphabetizer {
    @Override
    public String[] getSortedLines()
    {
        String [] shiftedLines = LineStore.getInstance().getShiftedLines();
        Arrays.sort(shiftedLines, new SortIgnoreCase());
        return shiftedLines;
    }
    public static class SortIgnoreCase implements Comparator<Object> {
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        }
    }
}
