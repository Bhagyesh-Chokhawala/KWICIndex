package KwicIndices.ioProcessing;

import dataStructure.IOPreference;
import KwicIndices.outputStrategies.OutputProcessingContext;
import resources.Constants;


public class OutputProcessing implements IOProcessing {
    @Override
    public void processData()
    {
        String[] ioPreference = {Constants.IOPREFERENCE_KEY};
        OutputProcessingContext processingContext = new OutputProcessingContext(IOPreference.getInstance().getIOPreferenceData(IOPreference.IOMethods.OUTPUT,ioPreference).get(Constants.IOPREFERENCE_KEY));
        processingContext.executeStrategy();
    }
}
