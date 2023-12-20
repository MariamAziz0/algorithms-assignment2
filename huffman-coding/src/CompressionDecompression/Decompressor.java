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

        // TODO: call to read meta data and apply decompressing for the remaining part in decompressing
        this.readMetaData();

        byte[] bytesRead;
        long totalBytesRead = 0;

        // This code will work with hex char, if this modified the following code should be modified
        StringBuilder binary = new StringBuilder();

        while ((bytesRead = this.reader.readChunk()) != null) {
            totalBytesRead += bytesRead.length;

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
        byte[] metaData = this.reader.readChunk();
        int currentIndex = 0;

        this.totalBytes = MetaDataConverter.convertBytesToLong(metaData, 0, 7);
        currentIndex += 8;
        System.out.println("Decompressor Total bytes: " + this.totalBytes);

        this.bitsLastByte = metaData[currentIndex];
        currentIndex++;
        System.out.println("Decompressor Bits in last byte: " + this.bitsLastByte);

        int entries = MetaDataConverter.convertBytesToInt(metaData, currentIndex, currentIndex + 3);
        currentIndex += 4;
        System.out.println("Decompressor Entries: " + entries);

        while (entries > 0) {

            if (currentIndex == this.bufferSize) {
                metaData = this.reader.readChunk();
                currentIndex = 0;
            }

            // number of bits padded at first byte of bits
            byte bitsPadded = metaData[currentIndex];
            currentIndex++;

            // If the meta-data ended before getting the number of bytes of bits
            if (currentIndex + 3 > this.bufferSize) {
                byte[] tempIntegerBytes = new byte[4];
                int tempIndex = 0;
                for ( ; currentIndex < this.bufferSize ; currentIndex++) {
                    tempIntegerBytes[tempIndex++] = metaData[currentIndex];
                }
                metaData = this.reader.readChunk();
                currentIndex = 0;

            }
            else {
                int bytesBits = MetaDataConverter.convertBytesToInt(metaData, currentIndex, currentIndex + 3);
                currentIndex += 4;
            }




            entries --;
        }

    }



}
