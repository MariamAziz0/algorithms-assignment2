import CompressionDecompression.Compressor;
import CompressionDecompression.Decompressor;
import HandlingInputOutput.HelpingFunctions;
import HandlingInputOutput.Reader;
import HandlingInputOutput.Writer;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * I acknowledge that I am aware of the academic integrity guidelines of this course, and that I worked on this assignment independently without any unauthorized help
 */
public class Main {
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("please enter the character c or d");
        }
        String requiredMethod = args[0];

        if (args.length < 2) {
            System.out.println("please enter the path");
        }
        String inputPath = args[1];
        int n = 0;

        if (requiredMethod.equals("c")) {
            if(args.length < 3) {
                System.out.println("please enter the n");
            }
            n = Integer.parseInt(args[2]);
        }

        int bufferSize = 10 * 1024 * 1024;

        if (requiredMethod.equals("c")) {
            long start = System.currentTimeMillis();

            Compressor compressor = new Compressor(inputPath, n, bufferSize);
            compressor.compress();

            long end = System.currentTimeMillis();

            System.out.println("Time of Compression taken: " + (end - start) / 1000.0);

            try {
                long inputFileSize = Files.size(Paths.get(inputPath));
                long outputFileSize = Files.size(Paths.get(HelpingFunctions.getCompressedPath(inputPath, n)));
                System.out.println("Compression ratio: " + ((double) outputFileSize / inputFileSize));
            }
            catch (Exception e) {
                System.out.println("Getting the size of the file is not present, the compression ratio can't be computed.");
            }
        }
        else if (requiredMethod.equals("d")) {
            long start = System.currentTimeMillis();

            Decompressor decompressor = new Decompressor(inputPath, bufferSize);

            decompressor.decompress();

            long end = System.currentTimeMillis();
            System.out.println("Time of Decompression: " + (end - start) / 1000.0);
        }

    }
}