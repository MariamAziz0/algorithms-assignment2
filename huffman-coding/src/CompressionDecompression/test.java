package CompressionDecompression;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import HandlingInputOutput.*;

public class test {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int bufferSize = 10 * 1024 * 1024;
        int n = 5;
//        String inputPath = "D:\\College\\Third Year\\First Term\\Algorithms\\Labs\\Assignment2\\files\\time\\gbbct10.seq";
//
//        Compressor compressor = new Compressor(inputPath, n, bufferSize);
//        compressor.compress();

        long end = System.currentTimeMillis();

//        System.out.println("Time of Compression taken: " + (end - start) / 1000.0);

//        try {
//            long inputFileSize = Files.size(Paths.get(inputPath));
//            long outputFileSize = Files.size(Paths.get(HelpingFunctions.getCompressedPath(inputPath, n)));
//            System.out.println("Compression ratio: " + ((double) outputFileSize / inputFileSize));
//        }
//        catch (Exception e) {
//            System.out.println("Getting the size of the file is not present, the compression ratio can't be computed.");
//        }


//        Reader reader = new Reader("D:\\College\\Third Year\\First Term\\Algorithms\\Labs\\Assignment2\\files\\20011889.1.huffman_test1.txt.hc", bufferSize);
//        byte[] bytes = reader.readChunk();
//        System.out.println("Read bytes after compression: ");
//        for (byte b : bytes) {
//            System.out.print(b + " ");
//        }
//        System.out.println();
//        System.out.println(bytes.length);

//        Map<String, String> reversedCodes = compressor.getReversedCodeWords();
//        long totalBytes = compressor.getOutputBytes();
//        byte bitsInLAstByte = compressor.getBitsInLastByte();
//        System.out.println("Total bytes in main: " + totalBytes);
//        System.out.println("bits in last byte in main: " + bitsInLAstByte);
//        for (Map.Entry<String, String> entry : reversedCodes.entrySet()) {
//            System.out.println("Main bit:" + entry.getKey() + ", hex: " + entry.getValue());
//        }

        start = System.currentTimeMillis();

        String outputPath = "D:\\College\\Third Year\\First Term\\Algorithms\\Labs\\Assignment2\\files\\time\\20011889.5.gbbct10.seq.hc";
        Decompressor decompressor = new Decompressor(outputPath, bufferSize);

        decompressor.decompress();

        end = System.currentTimeMillis();
        System.out.println("Time of Decompression: " + (end - start) / 1000.0);

    }
}
