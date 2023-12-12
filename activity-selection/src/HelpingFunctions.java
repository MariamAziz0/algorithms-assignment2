import java.io.*;
import java.util.Arrays;

public class HelpingFunctions {

    public static int[][] getInput(String filePath) {

        File inputFile = new File(filePath);

        if (!inputFile.exists()) {
            System.err.println("File not found: " + filePath);
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            int numActivities = Integer.parseInt(reader.readLine());

            int[][] activities = new int[numActivities][3];

            for (int i = 0; i < numActivities; i++) {
                String line = reader.readLine();
                if (line != null) {
                    String[] pointTokens = line.split(" ");
                    if (pointTokens.length == 3) {
                        activities[i][0] = Integer.parseInt(pointTokens[0]);
                        activities[i][1] = Integer.parseInt(pointTokens[1]);
                        activities[i][2] = Integer.parseInt(pointTokens[2]);
                    } else {
                        System.err.println("Invalid format for point data on line " + (i + 2));
                        return null;
                    }
                } else {
                    System.err.println("File ended prematurely after " + i + " activities.");
                    return null;
                }
            }
            return activities;

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return null;
        }

    }

    public static void printArray(int[][] array) {

        for(int i = 0 ; i < array.length ; i++) {
            for(int j = 0 ; j < array[0].length ; j++)
                System.out.print(array[i][j] + " ");
            System.out.println();
        }
        System.out.println();

    }

    public void printArray(int[] array) {

        for (int i = 0 ; i < array.length ; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

    }

    public static void sortActivities(int[][] activities) {

        Arrays.sort(activities, (a, b) -> {
            int compareFirstElement = Integer.compare(a[0], b[0]);
            return compareFirstElement == 0 ? Integer.compare(a[1], b[1]) : compareFirstElement;
        });

    }

}
