package CompressionDecompression;
import java.util.*;

import Convertion.ByteToBinary;
import Convertion.ByteToHex;
import Convertion.MetaDataConverter;
import HandlingInputOutput.*;
import HuffmanCoding.HuffmanCodingAlgorithm;

public class Compressor {
    private int bufferSize;
    private Reader reader;
    private Writer writer;
    private String inputPath;
    private String outputPath;
    private int n;
    private HuffmanCodingAlgorithm huffman;
    private Map<String, String> codeWords;
    private long outputBytes;
    private byte bitsInLastByte;

    public Compressor (String inputPath, int n, int bufferSize) {
        this.inputPath = inputPath;
        this.n = n;
        this.bufferSize = bufferSize;

        this.reader = new Reader(this.inputPath, this.bufferSize);
        this.outputPath = HelpingFunctions.getCompressedPath(this.inputPath, this.n);
        this.writer = new Writer(this.outputPath);
        this.huffman = new HuffmanCodingAlgorithm();
        this.codeWords = new HashMap<>();
        this.outputBytes = 0;
        this.bitsInLastByte = 0;
    }

    public void compress () {

//        long start = System.currentTimeMillis();
        byte[] bytesRead;
        while ((bytesRead = this.reader.readChunk()) != null) {
            String[] hexRepresentation = ByteToHex.byteArrayToHexStringArray(bytesRead, this.n);
            this.huffman.updateFrequency(hexRepresentation);
        }
//        System.out.println("After frequency: " + (System.currentTimeMillis() - start) / 1000.0);

//        Map<String, String> codeWords = this.huffman.applyHuffman();
        this.codeWords = this.huffman.applyHuffman();
        this.outputBytes = this.huffman.getNumberBytesOutput();
        this.bitsInLastByte = this.huffman.getNumberBitsInLastByte();


        this.writeMetaData();

//        System.out.println("After huffman: " + (System.currentTimeMillis() - start) / 1000.0);

        // Open new Reader to read file from beginning and apply huffman
        this.reader = new Reader(this.inputPath, this.n);
        StringBuilder bitCompressed = new StringBuilder();

        while ((bytesRead = this.reader.readChunk()) != null) {
            String[] hexRepresentation = ByteToHex.byteArrayToHexStringArray(bytesRead, this.n);
            for (String sequence : hexRepresentation) {
                bitCompressed.append(codeWords.get(sequence));
            }

            if (bitCompressed.length() > 8) {
                String readyToWrite = bitCompressed.substring(0, (bitCompressed.length() / 8) * 8);
                bitCompressed = new StringBuilder(bitCompressed.substring((bitCompressed.length() / 8) * 8));

                byte[] bytesToWrite = ByteToBinary.bitStringToByteArray(readyToWrite);
                this.writer.writeChunk(bytesToWrite);
            }
        }
//        System.out.println("After compressing: " + (System.currentTimeMillis() - start) / 1000.0);

        // If there is remaining bits to written in separate byte
        if (!bitCompressed.toString().equals("")) {
            byte lastByte = ByteToBinary.binaryStringToByteWithTrailingZeros(bitCompressed.toString());
            byte[] lastByteArray = {lastByte};
            this.writer.writeChunk(lastByteArray);
        }
//        System.out.println("After last byte: " + (System.currentTimeMillis() - start) / 1000.0);

        this.writer.close();

    }

    public Map<String, String> getReversedCodeWords () {

        Map<String, String> reversedCodeWords = new HashMap<>();
        for (Map.Entry<String, String> entry : this.codeWords.entrySet()) {
            reversedCodeWords.put(entry.getValue(), entry.getKey());
        }

        return reversedCodeWords;

    }

    private void writeMetaData () {
        byte[] metaData = new byte[this.bufferSize];
        int currentIndex = 0;
        currentIndex = MetaDataConverter.convertMetaDataHeader(this.outputBytes, this.bitsInLastByte, this.codeWords.size(), metaData, currentIndex);
        Map<String, String> reversedCodeWords = this.getReversedCodeWords();

        for (Map.Entry<String, String> entry : reversedCodeWords.entrySet()) {
            int totalEntryLength = 9 + (int) Math.ceil(entry.getKey().length() / 8.0) + (int) Math.ceil(entry.getValue().length() / 2.0);

            if (currentIndex + totalEntryLength > this.bufferSize) {
                byte[] chunk = new byte[currentIndex];
                System.arraycopy(metaData, 0, chunk, 0, currentIndex);
                this.writer.writeChunk(chunk);
                metaData = new byte[this.bufferSize];
                currentIndex = 0;
            }

            byte bitsPadded = (byte) (Math.ceil(entry.getKey().length() / 8.0) * 8 - entry.getKey().length());
            int bytesBit = (int) Math.ceil(entry.getKey().length() / 8.0);
            int bytesHex = (int) Math.ceil(entry.getValue().length() / 2.0);
            currentIndex = MetaDataConverter.convertEntry(bitsPadded, bytesBit, bytesHex, entry.getKey(), entry.getValue(), metaData, currentIndex);

        }

        // if currentIndex > 0 there is data to be written
        if (currentIndex > 0) {
            byte[] chunk = new byte[currentIndex];
            System.arraycopy(metaData, 0, chunk, 0, currentIndex);
            this.writer.writeChunk(chunk);
        }
    }

    // TODO: to be removed


    public long getOutputBytes() {
        return outputBytes;
    }

    public byte getBitsInLastByte() {
        return bitsInLastByte;
    }
}
