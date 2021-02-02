package KwicIndices.ioProcessing;

import dataStructure.IOPreference;

public class IOProcessingContext {
    private IOProcessing strategy;

    public IOProcessingContext(IOPreference.IOMethods aIOMethod){
        if (aIOMethod == IOPreference.IOMethods.INPUT)
            this.strategy = new InputProcessing();
        else if(aIOMethod == IOPreference.IOMethods.OUTPUT)
            this.strategy = new OutputProcessing();
    }

    public void executeStrategy(){
         strategy.processData();
    }
}