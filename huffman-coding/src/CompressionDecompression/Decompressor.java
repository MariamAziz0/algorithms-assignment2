package CompressionDecompression;
import java.util.*;

import Convertion.ByteToBinary;
import Convertion.ByteToHex;
import HandlingInputOutput.*;

public class Decompressor {

    private int bufferSize;
    private Reader reader;
    private Writer writer;
    private String inputPath;
    private String outputPath;


    public Decompressor (String inputPath, int bufferSize) {
        this.inputPath = inputPath;
        this.bufferSize = bufferSize;

        this.reader = new Reader(this.inputPath, this.bufferSize);
        this.outputPath = HelpingFunctions.getDecompressionPath(this.inputPath);
        this.writer = new Writer(this.outputPath);
    }

    public void decompress (Map<String, String> reversedCode, long totalBytes, byte bitsLastByte) {
        byte[] bytesRead;
        long totalBytesRead = 0;

        // This code will work with hex char, if this modified the following code should be modified
        StringBuilder binary = new StringBuilder();

        while ((bytesRead = this.reader.readChunk()) != null) {
            totalBytesRead += bytesRead.length;

            binary.append(ByteToBinary.byteArrayToBinaryString(bytesRead));

            if (totalBytesRead == totalBytes && bitsLastByte != 0) {
                binary = new StringBuilder(binary.substring(0, binary.length() - (8 - bitsLastByte)));
            }

            binary = new StringBuilder(getBytesToBeWritten(binary.toString(), reversedCode));

        }
        this.writer.close();

    }

    private String getBytesToBeWritten (String binary, Map<String, String> reversedCode) {
        StringBuilder hexDecompressed = new StringBuilder();

        StringBuilder current = new StringBuilder();
        for (int i = 0 ; i < binary.length() ; i++) {
            current.append(binary.charAt(i));
            String currentReversedCode = reversedCode.get(current.toString());

            if (currentReversedCode != null) {
                hexDecompressed.append(currentReversedCode);
                current.setLength(0);
            }

        }
        this.writer.writeChunk(ByteToHex.hexStringToByteArray(hexDecompressed.toString()));

        return current.toString();
    }

}
