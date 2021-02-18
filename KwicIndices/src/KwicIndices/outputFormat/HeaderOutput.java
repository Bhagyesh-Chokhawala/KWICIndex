package KwicIndices.outputFormat;

import dataStructure.LineStore;

import java.util.Date;
import java.util.Formatter;

public class HeaderOutput extends OutputDecorator{

    public HeaderOutput(Output output) {
        super(output);
    }
    public StringBuilder loadOutput(String[] body) {
        StringBuilder headerOutput = new StringBuilder();
        Formatter formatter = new Formatter(headerOutput);
        formatter.format("Date : %tc++++++++++++++++++++++++++++++++++", new Date());
        formatter.format("Total Input lines : %d", LineStore.getInstance().totalInputCount);
        headerOutput.append(System.lineSeparator());
        headerOutput.append("==========================================================================================");
        headerOutput.append(System.lineSeparator());
        if (output!=null)
        headerOutput.append(output.loadOutput(body));
        return headerOutput;
    }
}
