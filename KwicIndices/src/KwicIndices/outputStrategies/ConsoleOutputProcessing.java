package KwicIndices.outputStrategies;

import KwicIndices.outputFormat.FooterOutput;
import KwicIndices.outputFormat.Output;
import KwicIndices.outputFormat.PainTextOutput;

public class ConsoleOutputProcessing implements KwicIndices.outputStrategies.OutputProcessor {
    @Override
    public void processData(String[] result )
    {


        Output output = new PainTextOutput();
        output = new FooterOutput(output);
        System.out.println( output.loadOutput(result).toString());
    }
}
