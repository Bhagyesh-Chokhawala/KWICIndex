package KwicIndices.outputFormat;

import dataStructure.LineStore;

import java.util.Date;
import java.util.Formatter;

public class HeaderOutput extends OutputDecorator{
    private Output output;

    public HeaderOutput(Output output) {
        this.output = output;
    }
    public StringBuilder loadOutput(String[] body) {
        StringBuilder painTextWithHeaderOutput = new StringBuilder();
        Formatter formatter = new Formatter(painTextWithHeaderOutput);
        formatter.format("Date : %tc++++++++++++++++++++++++++++++++++", new Date());
        formatter.format("Total Input lines : %d", LineStore.getInstance().totalInputCount);
        painTextWithHeaderOutput.append(System.lineSeparator());
        painTextWithHeaderOutput.append("==========================================================================================");
        painTextWithHeaderOutput.append(System.lineSeparator());
        painTextWithHeaderOutput.append(System.lineSeparator());
        painTextWithHeaderOutput.append(output.loadOutput(body));
        return painTextWithHeaderOutput;
    }
}
