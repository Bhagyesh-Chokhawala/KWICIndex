package KwicIndices.outputStrategies;

import KwicIndices.outputFormat.FooterOutput;
import KwicIndices.outputFormat.HeaderOutput;
import KwicIndices.outputFormat.Output;
import KwicIndices.outputFormat.PainTextOutput;
import dataStructure.IOPreference;

import java.io.BufferedWriter;
import java.io.FileWriter;

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
            Output output = new PainTextOutput();
            output = new HeaderOutput(output);
            output = new FooterOutput(output);
            writer.write( output.loadOutput(result).toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("System is unable to write the file.");
            System.exit(1);
        }
    }
}
