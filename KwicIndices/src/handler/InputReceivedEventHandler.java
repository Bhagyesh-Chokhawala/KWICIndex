package handler;

import KwicIndices.processors.IndicesProcessor;
import KwicIndices.MasterController;
import event.InputReceivedEvent;
import framework.Handler;

public class InputReceivedEventHandler implements Handler<InputReceivedEvent> {
    @Override
    public void onEvent(InputReceivedEvent event) {
      //  System.out.println(event.getInputString());
        Runnable worker = new IndicesProcessor(event.getInputString());
        MasterController.executionCount = MasterController.executionCount+1;
       MasterController.executor.execute(worker);
    }
}
