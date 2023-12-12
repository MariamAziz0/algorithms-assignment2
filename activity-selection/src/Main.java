import Algorithm.ActivitySelection;
import Algorithm.HelpingFunctions;

public class Main {
    public static void main(String[] args) {

        String filePath = "";

        if(args.length > 0)
            filePath = args[0];
        else {
            System.out.println("Please enter the file path");
            System.exit(-1);
        }

        int[][] activities = HelpingFunctions.getInput(filePath);

        if (activities == null) {
            System.out.println("There is problem in reading the input");
            System.exit(-1);
        }

        int maxProfit = ActivitySelection.getMaxWeight(activities);

        String outputPath = HelpingFunctions.getNameOfOutputFile(filePath);

        HelpingFunctions.saveOutputInFile(outputPath, maxProfit);

    }
}