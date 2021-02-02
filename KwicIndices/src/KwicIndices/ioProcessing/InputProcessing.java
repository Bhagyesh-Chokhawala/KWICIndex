package KwicIndices.ioProcessing;

import dataStructure.IOPreference;
import resources.Constants;
import KwicIndices.inputStrategies.InputProcessingContext;

public class InputProcessing implements IOProcessing {
    @Override
    public void processData() {
        String[] ioPreference = {Constants.IOPREFERENCE_KEY};
        InputProcessingContext processingContext = new InputProcessingContext(IOPreference.getInstance().getIOPreferenceData(IOPreference.IOMethods.INPUT,ioPreference).get(Constants.IOPREFERENCE_KEY));
        processingContext.executeStrategy();
    }
}
