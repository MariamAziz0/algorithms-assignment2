package Convertion;
import java.util.stream.*;

public class ByteToBinary {

    public static String byteArrayToBinaryString(byte[] byteArray) {
        int length = byteArray.length;
        int numBits = length * 8;

        StringBuilder binaryString = new StringBuilder(numBits);

        for (int i = 0; i < numBits; i++) {
            int byteIndex = i / 8;
            int bitIndex = 7 - (i % 8);
            int bitValue = (byteArray[byteIndex] >> bitIndex) & 1;
            binaryString.append(bitValue);
        }

        return binaryString.toString();
    }

    public static byte[] bitStringToByteArray(String bitString) {
        int length = bitString.length();
        int byteLength = (int) Math.ceil((double) length / 8);

        byte[] byteArray = new byte[byteLength];

        for (int i = 0; i < byteLength; i++) {
            int start = i * 8;
            int end = Math.min(start + 8, length);

            String byteSubstring = bitString.substring(start, end);

            byte byteValue = (byte) Integer.parseInt(byteSubstring, 2);
            byteArray[i] = byteValue;
        }

        return byteArray;
    }

    public static byte binaryStringToByteWithTrailingZeros (String bitString) {
        bitString = String.format("%-8s", bitString).replace(' ', '0');
        return (byte) Integer.parseInt(bitString, 2);
    }

}
