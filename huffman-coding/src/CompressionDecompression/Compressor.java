package CompressionDecompression;
import java.util.*;

import Convertion.ByteToBinary;
import Convertion.ByteToHex;
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
    // TODO: to be removed
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

        // TODO: to be removed
        this.codeWords = new HashMap<>();
        this.outputBytes = 0;
        this.bitsInLastByte = 0;
    }

    public void compress () {

        byte[] bytesRead;
        while ((bytesRead = this.reader.readChunk()) != null) {
            String[] hexRepresentation = ByteToHex.byteArrayToHexStringArray(bytesRead, this.n);
            this.huffman.updateFrequency(hexRepresentation);
        }

//        Map<String, String> codeWords = this.huffman.applyHuffman();
        this.codeWords = this.huffman.applyHuffman();

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

        // If there is remaining bits to written in separate byte
        if (!bitCompressed.toString().equals("")) {
            byte lastByte = ByteToBinary.binaryStringToByteWithTrailingZeros(bitCompressed.toString());
            byte[] lastByteArray = {lastByte};
            this.writer.writeChunk(lastByteArray);
        }

        this.writer.close();

        //TODO: to be removed
        this.outputBytes = this.huffman.getNumberBytesOutput();
        this.bitsInLastByte = this.huffman.getNumberBitsInLastByte();

    }

    // TODO: to be modified
    public Map<String, String> getReversedCodeWords () {

        Map<String, String> reversedCodeWords = new HashMap<>();
        for (Map.Entry<String, String> entry : this.codeWords.entrySet()) {
            reversedCodeWords.put(entry.getValue(), entry.getKey());
        }

        return reversedCodeWords;

    }

    // TODO: to be removed


    public long getOutputBytes() {
        return outputBytes;
    }

    public byte getBitsInLastByte() {
        return bitsInLastByte;
    }
}
