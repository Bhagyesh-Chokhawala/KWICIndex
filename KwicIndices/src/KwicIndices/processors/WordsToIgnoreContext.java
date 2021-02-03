package KwicIndices.processors;

import KwicIndices.alphabetizerStrategies.Alphabetizer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dataStructure.IOPreference;
import resources.Constants;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class WordsToIgnoreContext {
    private static Map<String,String> wordToIgnoreStrategies;
    private static String[] ioPreference = {Constants.SORTPREFERENCE_KEY};
    private static void WordToIgnoreStrategies()
    {
        String jsonString = Constants.WORDTOIGNORESTRATEGIES;
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String,String>>(){}.getType();
        wordToIgnoreStrategies = gson.fromJson(jsonString, type);;
    }
    private static WordsToIgnore strategy;
    public static WordsToIgnore getWordsToIgnore(){
        WordToIgnoreStrategies();
        try {
            // Create a new JavaClassLoader
            ClassLoader classLoader = WordsToIgnore.class.getClassLoader();

            // Load the target class using its binary name
            Class loadedMyClass = classLoader.loadClass(wordToIgnoreStrategies.get(IOPreference.getInstance().getIOPreferenceData(IOPreference.IOMethods.INPUT,ioPreference).get(Constants.SORTPREFERENCE_KEY)));

              //System.out.println("Loaded class name: " + loadedMyClass.getName());

            // Invoke static method from loaded strategy class which provide static instance of strategy class
            // Considering the same method name which provides static instance in strategy and Context
            String nameofCurrMethod = new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName();
            strategy = (WordsToIgnore) loadedMyClass.getMethod(nameofCurrMethod).invoke(loadedMyClass);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("System is unable to proceed and write output.");
            System.exit(2);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("System is unable to proceed and write output.");
            System.exit(2);
        }

        return strategy;
    }
    public void addWordToIgnore(String word) {
        assert(word != null);
        strategy.addWordToIgnore(word);
    }
    public void addWordToIgnore(List<String> words) {
        assert(words != null);
        strategy.addWordToIgnore(words);
    }

    public void removeWordToIgnore(String word) {
        assert(word != null);
        strategy.removeWordToIgnore(word);
    }

    public boolean isWordIgnored(String word) {
        assert(word != null);
       return strategy.isWordIgnored(word);
    }
}
