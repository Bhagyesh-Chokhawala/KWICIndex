package resources;

import framework.ResourceManager;

import java.util.List;

public abstract class Constants
{
    private static final String THREADPOOL_SIZE_PROP = "KwicIndices.threadpool.size";
    private static final String IOOPTION_DEF_PROP = "KwicIndices.defaultIOOption";
    private static final String IOOPTION_PROP = "KwicIndices.ioOptions";
    private static final String SORTOPTION_PROP = "KwicIndices.sortOptions";
    private static final String INPUTPROCESSOR_PROP ="KwicIndices.inputProcessor";
    private static final String OUTPUTPROCESSOR_PROP ="KwicIndices.outputProcessor";
    private static final String ALPHABETIZERROCESSOR_PROP ="KwicIndices.AlphabetizerProcessor";
    private static final String SHIFTIGNOREWORDS_PROP ="KwicIndices.shiftIgnoreWords";
    private static final String WORDTOIGNORESTRATEGIES_PROP ="KwicIndices.WordToIgnoreStrategies";


    private static final int THREADPOOL_SIZE_DEF = 5;
    private static final String IOOPTION_DEF_VAL = "S";
    private static final String IOOPTION_PROP_DEF = "[{'iD':'F','displayName':'File','attributes':['FileName']},{'iD':'S','displayName':'Console','attributes':[]}]";
    private static final String SORTOPTION_DEF = "{'Y':'CaseIgnore'}";
    private static final String INPUTPROCESSOR_DEF = "KwicIndices.inputStrategies.ConsoleInputProcessing";
    private static final String OUTPUTPROCESSOR_DEF = "KwicIndices.outputStrategies.ConsoleOutputProcessing";
    private static final String ALPHABETIZERROCESSOR_DEF = "KwicIndices.alphabetizerStrategies.CaseIgnoreAlphabetizer";
    private static final String SHIFTIGNOREWORDS_DEF ="";
    private static final String WORDTOIGNORESTRATEGIES_DEF = "KwicIndices.processors.WordsToIgnoreCaseSensitive";


    public static final int THREADPOOL_SIZE = ResourceManager.getResources().getPropertyOrDefault(Constants.THREADPOOL_SIZE_PROP, Constants.THREADPOOL_SIZE_DEF);
    public static final String IOOPTION_DEF = ResourceManager.getResources().getPropertyOrDefault(Constants.IOOPTION_DEF_PROP,IOOPTION_DEF_VAL);
    public static final String IOPREFERENCE_KEY ="preference";
    public static final String SORTPREFERENCE_KEY = "sortPreference";
    public static final String IOOPTION = ResourceManager.getResources().getPropertyOrDefault(Constants.IOOPTION_PROP,IOOPTION_PROP_DEF);
    public static final String SORTOPTION = ResourceManager.getResources().getPropertyOrDefault(Constants.SORTOPTION_PROP,SORTOPTION_DEF);
    public static final String INPUTPROCESSOR = ResourceManager.getResources().getPropertyOrDefault(Constants.INPUTPROCESSOR_PROP,INPUTPROCESSOR_DEF);
    public static final String OUTPUTPROCESSOR = ResourceManager.getResources().getPropertyOrDefault(Constants.OUTPUTPROCESSOR_PROP,OUTPUTPROCESSOR_DEF);
    public static final String ALPHABETIZERROCESSOR = ResourceManager.getResources().getPropertyOrDefault(Constants.ALPHABETIZERROCESSOR_PROP,ALPHABETIZERROCESSOR_DEF);
    public static final List<String> SHIFTIGNOREWORDS = ResourceManager.getResources().getCommaSeperatedPropertyOrDefault(Constants.SHIFTIGNOREWORDS_PROP,SHIFTIGNOREWORDS_DEF.split(","));
    public static final String WORDTOIGNORESTRATEGIES = ResourceManager.getResources().getPropertyOrDefault(Constants.WORDTOIGNORESTRATEGIES_PROP,WORDTOIGNORESTRATEGIES_DEF);

}
