package KwicIndices.outputFormat;

import java.util.Arrays;
import java.util.Formatter;

public class OutPutLineCountOutput extends OutputDecorator{
   // private Output output;
    public OutPutLineCountOutput(Output output)
    {
        super(output);
      //  this.output = output;
    }
    public StringBuilder loadOutput(String[] body) {
        StringBuilder lineCountOutput = new StringBuilder();
        if (output!=null)
        lineCountOutput.append(output.loadOutput(body));
        Formatter formatter = new Formatter(lineCountOutput);
        lineCountOutput.append(System.lineSeparator());
        formatter.format("Total Lines in Output : %d", Arrays.stream(body).count());
        lineCountOutput.append(System.lineSeparator());
        return lineCountOutput;

    }
}
