package KwicIndices.outputFormat;

public class PainTextOutput implements Output {

    private Output output;

    public PainTextOutput(Output output) {
        this.output = output;
    }

    @Override
    public StringBuilder loadOutput(String[] body) {
        StringBuilder painTextOutput = new StringBuilder();
        if (output!=null)
        painTextOutput.append(output.loadOutput(body));
        painTextOutput.append(System.lineSeparator());
        for (String line : body) {
            painTextOutput.append(line);
            painTextOutput.append(System.lineSeparator());
        }
        return painTextOutput;
    }


}
