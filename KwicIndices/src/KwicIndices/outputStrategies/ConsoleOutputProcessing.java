package KwicIndices.outputStrategies;

import KwicIndices.outputFormat.Output;
import com.google.gson.Gson;
import resources.Constants;

import java.lang.reflect.Constructor;

public class ConsoleOutputProcessing implements KwicIndices.outputStrategies.OutputProcessor {
    @Override
    public void processData(String[] result )
    {
        try {

                /*  Output output = new PainTextOutput();
            output = new FooterOutput(output);*/
           // String[] outputs = {"KwicIndices.outputFormat.PainTextOutput", "KwicIndices.outputFormat.HeaderOutput", "KwicIndices.outputFormat.FooterOutput"};
            Gson gson = new Gson();
            String[] outputs = gson.fromJson(Constants.CONSOLEOUTPUTLAYOUT, String[].class);
            Output output = null;
            int size = outputs.length;
            ClassLoader classLoader = this.getClass().getClassLoader();
            for (int i=0; i<size; i++)
            {
                Class loadedMyClass = classLoader.loadClass(outputs[i]);
                Constructor constructor = loadedMyClass.getConstructor(Output.class);
              //  System.out.println("Loaded class name: " + loadedMyClass.getName());
                output= (Output) constructor.newInstance(output);
            }
            System.out.println( output.loadOutput(result).toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("System is unable to process Output.");
            System.exit(1);
        }
    }
}
