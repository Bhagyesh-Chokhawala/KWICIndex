package KwicIndices.inputStrategies;

import dataStructure.IOPreference;
import resources.Constants;
import dataStructure.LineStore;
import java.io.BufferedReader;
import java.io.FileReader;

public class FileInputProcessing implements KwicIndices.inputStrategies.InputProcessor {
   private  static final String attrFileName = "FileName";
    @Override
    public void processData()
    {
        int totalInputCount = 0;
        String[] ioPreference = {Constants.IOPREFERENCE_KEY,attrFileName};
                String fileName = IOPreference.getInstance().getIOPreferenceData(IOPreference.IOMethods.INPUT,ioPreference).get(attrFileName);
             //   System.out.println(fileName);
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new FileReader(
                            fileName));
                    String line = reader.readLine();
                    while (line != null) {
                      //  System.out.println(line);
                        totalInputCount = totalInputCount+1;
                        LineStore.getInstance().addInputLine(line);
                        line = reader.readLine();
                    }
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("System is unable to read the file.");
                    System.exit(1);
                }

        LineStore.getInstance().totalInputCount = totalInputCount;
    }
}
