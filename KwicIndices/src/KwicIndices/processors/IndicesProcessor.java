package KwicIndices.processors;

import dataStructure.LineStore;

public class IndicesProcessor implements Runnable{
    private final String _line;

    public IndicesProcessor(String aLine){
        this._line=aLine;
    }
    @Override
    public void run() {
     //   System.out.println(Thread.currentThread().getName()+" Start. processing for = "+_line);
        processCommand();
      //  System.out.println(Thread.currentThread().getName()+" End.");
    }

    private void processCommand() {
        try {
            CircularShift shifter = new CircularShift(_line);
            LineStore.getInstance().addShiftedLines(shifter.getCircularShifts());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("System is unable to generate indices for phrase - "+_line);
        }

       // System.out.println(MasterController.executionCount);
    }


}
