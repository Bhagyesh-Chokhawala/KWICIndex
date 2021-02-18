package KwicIndices.outputFormat;

import java.util.Arrays;
import java.util.Formatter;

public class FooterOutput extends OutputDecorator{
    public FooterOutput(Output output)
    {
        super(output);
    }
    public StringBuilder loadOutput(String[] body) {
        StringBuilder footerOutput = new StringBuilder();
        if (output!=null)
        footerOutput.append(output.loadOutput(body));
        footerOutput.append(System.lineSeparator());
        footerOutput.append("==========================================================================================");
        Formatter formatter = new Formatter(footerOutput);
        footerOutput.append(System.lineSeparator());
        formatter.format("Total Lines in Output : %d", Arrays.stream(body).count());
        return footerOutput;

    }
}
