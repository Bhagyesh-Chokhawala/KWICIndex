package KwicIndices.outputFormat;

public class PainTextOutput implements Output {
    @Override
    public StringBuilder loadOutput(String[] body) {
        StringBuilder painTextOutput = new StringBuilder();
        for (String line:body)
        {
            painTextOutput.append(line);
            painTextOutput.append(System.lineSeparator());
        }
        return painTextOutput;
    }
}
