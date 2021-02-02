package KwicIndices.alphabetizerStrategies;

import dataStructure.IOPreference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import resources.Constants;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Map;

public class AlphabetizerContext {
    private  Alphabetizer strategy;
    private static String[] ioPreference = {Constants.SORTPREFERENCE_KEY};
    private static Map<String,String> alphabetizerProcessor;
    private static void AlphabetizerStrategies()
    {
        String jsonString = Constants.ALPHABETIZERROCESSOR;
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String,String>>(){}.getType();
        alphabetizerProcessor = gson.fromJson(jsonString, type);;
    }

    public AlphabetizerContext() {
        AlphabetizerStrategies();
        try {
            // Create a new JavaClassLoader
            ClassLoader classLoader = this.getClass().getClassLoader();

            // Load the target class using its binary name
            Class loadedMyClass = classLoader.loadClass(alphabetizerProcessor.get(IOPreference.getInstance().getIOPreferenceData(IOPreference.IOMethods.INPUT,ioPreference).get(Constants.SORTPREFERENCE_KEY)));

            //  System.out.println("Loaded class name: " + loadedMyClass.getName());

            // Create a new instance from the loaded class
            Constructor constructor = loadedMyClass.getConstructor();
            this.strategy= (Alphabetizer) constructor.newInstance();

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

    public String[] executeStrategy() {
        return strategy.getSortedLines();
    }

}