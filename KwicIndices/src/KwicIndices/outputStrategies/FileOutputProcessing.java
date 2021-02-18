package KwicIndices.outputStrategies;


import KwicIndices.outputFormat.Output;
import com.google.gson.Gson;
import dataStructure.IOPreference;
import resources.Constants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Constructor;

public class FileOutputProcessing implements OutputProcessor {
   private  static final String attrFileName = "FileName";
    @Override
    public void processData(String[] result )
    {
        IOPreference.Preference preference = IOPreference.getInstance().preferenceList.stream()
                .filter(p -> p.iD.equalsIgnoreCase("F"))
                .findAny()
                .orElse(null);
        String fileName = IOPreference.getInstance().getIOPreferenceData(IOPreference.IOMethods.OUTPUT,preference.attributes.stream().toArray(String[]::new)).get(attrFileName);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(
                    fileName));
            Gson gson = new Gson();
            String[] outputs = gson.fromJson(Constants.FILEOUTPUTLAYOUT, String[].class);
            Output output = null;
            int size = outputs.length;
            ClassLoader classLoader = this.getClass().getClassLoader();
            for (int i=0; i<size; i++)
            {
                Class loadedMyClass = classLoader.loadClass(outputs[i]);
                Constructor constructor = loadedMyClass.getConstructor(Output.class);
                output= (Output) constructor.newInstance(output);
            }
            writer.write( output.loadOutput(result).toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("System is unable to write the file.");
            System.exit(1);
        }
    }
}
