package KwicIndices;

import dataStructure.IOPreference;
import dataStructure.LineStore;
import KwicIndices.ioProcessing.IOProcessingContext;
import resources.Constants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MasterController {
    public static final ExecutorService executor = Executors.newFixedThreadPool(Constants.THREADPOOL_SIZE);
    public static int executionCount=0;
    public static void main(String[] args) {
        IOPreference.getInstance().acceptIOPreference(IOPreference.IOMethods.INPUT);
        IOPreference.getInstance().acceptIOPreference(IOPreference.IOMethods.OUTPUT);
        IOProcessingContext IOProcessingContext = new IOProcessingContext(IOPreference.IOMethods.INPUT);
        IOProcessingContext.executeStrategy();
        long startTime = System.currentTimeMillis();
       // System.out.println("Total executions - "+ LineStore.getInstance().totalInputCount);
        do {
           // System.out.println("Total executions completed - " + executionCount);
        } while (MasterController.executionCount != LineStore.getInstance().totalInputCount || LineStore.getInstance().totalInputCount == 0);
        executor.shutdown();
        while (true) {
            if (executor.isTerminated()) break;
        }
        IOProcessingContext = new IOProcessingContext(IOPreference.IOMethods.OUTPUT);
        IOProcessingContext.executeStrategy();
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
        System.exit(0);
    }


}