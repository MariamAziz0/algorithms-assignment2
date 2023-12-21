package Analysis;

import CompressionDecompression.Compressor;
import HandlingInputOutput.HelpingFunctions;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Analysis {

    public static void main(String[] args) {
        String directory = "D:\\College\\Third Year\\First Term\\Algorithms\\Labs\\Assignment2\\files\\Analysis\\";
        int bufferSize = 10 * 1024 * 1024;

        int[] n = {1, 2, 3, 4, 5};
        String[] fileName = {"Algorithms - Lectures 7 and 8 (Greedy algorithms).pdf", "gbbct10.seq"};

        for (String name : fileName) {
            System.out.println("For the file: " + name + "\n");
            for (int i = 0 ; i < n.length ; i++) {
                System.out.println("At n = " + n[i]);
                long start = System.currentTimeMillis();

                String inputPath = directory + name;

                Compressor compressor = new Compressor(inputPath, n[i], bufferSize);
                compressor.compress();

                long end = System.currentTimeMillis();

                System.out.println("Time of Compression taken: " + (end - start) / 1000.0);

                try {
                    long inputFileSize = Files.size(Paths.get(inputPath));
                    long outputFileSize = Files.size(Paths.get(HelpingFunctions.getCompressedPath(inputPath, n[i])));
                    System.out.println("Compression ratio: " + ((double) outputFileSize / inputFileSize));
                }
                catch (Exception e) {
                    System.out.println("Getting the size of the file is not present, the compression ratio can't be computed.");
                }
                System.out.println("-----------------------------------------------------------------------------------------");
            }

        }

        for (int i = 0 ; i < n.length ; i++) {
            
        }

    }

}
