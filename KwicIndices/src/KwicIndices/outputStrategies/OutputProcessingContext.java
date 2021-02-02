package KwicIndices.outputStrategies;

import dataStructure.IOPreference;
import KwicIndices.alphabetizerStrategies.AlphabetizerContext;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import resources.Constants;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Map;

public class OutputProcessingContext {
    private OutputProcessor strategy;
    private static Map<String,String> outputProcessor;
    private static String[] ioPreference = {Constants.IOPREFERENCE_KEY};
    private String[] result;
    private static void OutputStrategies()
    {
        String jsonString = Constants.OUTPUTPROCESSOR;
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String,String>>(){}.getType();
        outputProcessor = gson.fromJson(jsonString, type);;
    }

    public OutputProcessingContext(String inputMethod){
        OutputStrategies();
        try {
        // Create a new JavaClassLoader
        ClassLoader classLoader = this.getClass().getClassLoader();

        // Load the target class using its binary name
        Class loadedMyClass = classLoader.loadClass(outputProcessor.get(IOPreference.getInstance().getIOPreferenceData(IOPreference.IOMethods.OUTPUT,ioPreference).get(Constants.IOPREFERENCE_KEY)));

      //  System.out.println("Loaded class name: " + loadedMyClass.getName());
            AlphabetizerContext alphabetizerContext =new AlphabetizerContext();
            result = alphabetizerContext.executeStrategy();

        // Create a new instance from the loaded class
        Constructor constructor = loadedMyClass.getConstructor();
        this.strategy= (OutputProcessor) constructor.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("System is unable to proceed and write output.");
            System.exit(2);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("System is unable to proceed and write output.");
            System.exit(2);
        }
    }

    public void executeStrategy(){
         strategy.processData(result);
    }
}