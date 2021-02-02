package KwicIndices.outputFormat;

import dataStructure.LineStore;

import java.util.Arrays;
import java.util.Formatter;

public class FooterOutput extends OutputDecorator{
    private Output output;
    public FooterOutput(Output output)
    {
        this.output = output;
    }
    public StringBuilder loadOutput(String[] body) {
        StringBuilder painTextWithFooterOutput = new StringBuilder();
        painTextWithFooterOutput.append(output.loadOutput(body));
        painTextWithFooterOutput.append(System.lineSeparator());
        painTextWithFooterOutput.append("==========================================================================================");
        Formatter formatter = new Formatter(painTextWithFooterOutput);
        painTextWithFooterOutput.append(System.lineSeparator());
        formatter.format("Total Lines in Output : %d", Arrays.stream(body).count());
        return painTextWithFooterOutput;

    }
}
