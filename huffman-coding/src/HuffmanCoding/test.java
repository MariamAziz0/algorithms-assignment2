package HuffmanCoding;

import java.util.*;

public class test {
    public static void main(String[] args) {
        HuffmanCodingAlgorithm huffmanCodingAlgorithm = new HuffmanCodingAlgorithm();
//        String[] sequences = {"a", "a", "b", "a", "b", "c"};
        String[] sequences = getSequence();
//        String[] sequences = {"a"};
        huffmanCodingAlgorithm.updateFrequency(sequences);
        Map<String, String> codes = huffmanCodingAlgorithm.applyHuffman();
        Map<String, Long> frequency = huffmanCodingAlgorithm.getFrequencyMap();
        String[] added = {"f"};
        huffmanCodingAlgorithm.updateFrequency(added);
        for (Map.Entry<String, String> entry : codes.entrySet()) {
            System.out.println("Node sequence: " + entry.getKey() + ", code word: " + entry.getValue() + ", its frequency: " + frequency.get(entry.getKey()));
        }
        System.out.println("Total Bytes: " + huffmanCodingAlgorithm.getNumberBytesOutput() + ", bits in last byte: " + huffmanCodingAlgorithm.getNumberBitsInLastByte());
    }

    public static String[] getSequence () {
        int countA = 45;
        int countB = 13;
        int countC = 12;
        int countD = 16;
        int countE = 9;
        int countF = 6;

        String[] stringArray = new String[countA + countB + countC + countD + countE + countF];

        fillArray(stringArray, 0, countA, "a");
        fillArray(stringArray, countA, countA + countB, "b");
        fillArray(stringArray, countA + countB, countA + countB + countC, "c");
        fillArray(stringArray, countA + countB + countC, countA + countB + countC + countD, "d");
        fillArray(stringArray, countA + countB + countC + countD, countA + countB + countC + countD + countE, "e");
        fillArray(stringArray, countA + countB + countC + countD + countE, countA + countB + countC + countD + countE + countF, "f");

        // Print the initialized array
        for (String str : stringArray) {
            System.out.print(str + " ");
        }
        System.out.println();

        return stringArray;
    }

    private static void fillArray(String[] array, int start, int end, String value) {
        for (int i = start; i < end; i++) {
            array[i] = value;
        }
    }

}
