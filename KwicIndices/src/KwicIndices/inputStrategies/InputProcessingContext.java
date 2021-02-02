package KwicIndices.inputStrategies;

import dataStructure.IOPreference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import resources.Constants;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Map;

public class InputProcessingContext {
    private KwicIndices.inputStrategies.InputProcessor strategy;
    private static Map<String,String> inputProcessor;
    private static String[] ioPreference = {Constants.IOPREFERENCE_KEY};
    private static void InputStrategies()
    {
        String jsonString = Constants.INPUTPROCESSOR;
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String,String>>(){}.getType();
        inputProcessor = gson.fromJson(jsonString, type);;
    }

    public InputProcessingContext(String inputMethod){
        InputStrategies();
        try {
        // Create a new JavaClassLoader
        ClassLoader classLoader = this.getClass().getClassLoader();
           // System.out.println(inputProcessor.get(IOPreference.getInstance().getIOPreferenceData(IOPreference.IOMethods.INPUT,ioPreference).get(Constants.IOPREFERENCE_KEY)));
        // Load the target class using its binary name
        Class loadedMyClass = classLoader.loadClass(inputProcessor.get(IOPreference.getInstance().getIOPreferenceData(IOPreference.IOMethods.INPUT,ioPreference).get(Constants.IOPREFERENCE_KEY)));

      //  System.out.println("Loaded class name: " + loadedMyClass.getName());

        // Create a new instance from the loaded class
        Constructor constructor = loadedMyClass.getConstructor();
        this.strategy= (KwicIndices.inputStrategies.InputProcessor) constructor.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("System is unable to proceed and read input.");
            System.exit(2);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("System is unable to proceed and read input.");
            System.exit(2);
        }
    }

    public void executeStrategy(){
         strategy.processData();
    }
}