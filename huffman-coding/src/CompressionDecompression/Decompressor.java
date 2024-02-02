package CompressionDecompression;
import java.util.*;

import Convertion.ByteToBinary;
import Convertion.ByteToHex;
import Convertion.MetaDataConverter;
import HandlingInputOutput.*;

public class Decompressor {

    private int bufferSize;
    private Reader reader;
    private Writer writer;
    private String inputPath;
    private String outputPath;
    private Map<String, String> reversedCode;
    private long totalBytes;
    private byte bitsLastByte;


    public Decompressor (String inputPath, int bufferSize) {
        this.inputPath = inputPath;
        this.bufferSize = bufferSize;

        this.reader = new Reader(this.inputPath, this.bufferSize);
        this.outputPath = HelpingFunctions.getDecompressionPath(this.inputPath);
        this.writer = new Writer(this.outputPath);
        this.reversedCode = new HashMap<>();
        this.totalBytes = 0;
        this.bitsLastByte = 0;
    }

    public void decompress () {

        this.readMetaData();

        byte[] bytesRead;
        long totalBytesRead = 0;

        // This code will work with hex char, if this modified the following code should be modified
        StringBuilder binary = new StringBuilder();

        while ((bytesRead = this.reader.readChunk()) != null) {
//            System.out.println("Read chunk...");
            totalBytesRead += bytesRead.length;
//            System.out.println(bytesRead.length);
            binary.append(ByteToBinary.byteArrayToBinaryString(bytesRead));

            if (totalBytesRead == this.totalBytes && this.bitsLastByte != 0) {
                binary = new StringBuilder(binary.substring(0, binary.length() - (8 - this.bitsLastByte)));
            }

            binary = new StringBuilder(getBytesToBeWritten(binary.toString()));

        }
        this.writer.close();

    }

    private String getBytesToBeWritten (String binary) {
        StringBuilder hexDecompressed = new StringBuilder();

        StringBuilder current = new StringBuilder();
        for (int i = 0 ; i < binary.length() ; i++) {
            current.append(binary.charAt(i));
            String currentReversedCode = this.reversedCode.get(current.toString());

            if (currentReversedCode != null) {
                hexDecompressed.append(currentReversedCode);
                current.setLength(0);
            }

        }
        this.writer.writeChunk(ByteToHex.hexStringToByteArray(hexDecompressed.toString()));

        return current.toString();
    }

    private void readMetaData () {
//        this.reader = new Reader(this.inputPath, 48836262);

        this.totalBytes = MetaDataConverter.convertBytesToLong(this.reader.getBytes(8), 0, 7);
//        System.out.println("total bytes: " + totalBytes);

        this.bitsLastByte = this.reader.getBytes(1)[0];
//        System.out.println("bits in last byte: " + bitsLastByte);

        int entries = MetaDataConverter.convertBytesToInt(this.reader.getBytes(4), 0, 3);
//        System.out.println("entries: " + entries);

        while (entries > 0) {

            byte bitsPadded = this.reader.getBytes(1)[0];
            int byteBits = MetaDataConverter.convertBytesToInt(this.reader.getBytes(4), 0, 3);
            int byteHex = MetaDataConverter.convertBytesToInt(this.reader.getBytes(4), 0, 3);

            byte[] byteArrayBits = this.reader.getBytes(byteBits);
            byte[] byteArrayHex = this.reader.getBytes(byteHex);

            String bitString = ByteToBinary.byteArrayToBinaryString(byteArrayBits);
            bitString = bitString.substring(bitsPadded);

            String hexString = ByteToHex.byteArrayToHexString(byteArrayHex);
            this.reversedCode.put(bitString, hexString);

            entries --;
        }

    }



}
