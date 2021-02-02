package KwicIndices.inputStrategies;

import dataStructure.LineStore;

import java.util.Scanner;

public class ConsoleInputProcessing implements KwicIndices.inputStrategies.InputProcessor {
    @Override
    public void processData()
    {
        int totalInputCount = 0;

                Scanner sc = new Scanner(System.in);
                System.out.println("Enter lines (terminate input by entering empty line)");
                String userInput = sc.nextLine();
                while (!userInput.isEmpty()) {
                    totalInputCount = totalInputCount+1;
                    LineStore.getInstance().addInputLine(userInput);
                    userInput = sc.nextLine();
                }
        LineStore.getInstance().totalInputCount = totalInputCount;
       // System.out.println(totalInputCount);
    }
}
