package Convertion;
import java.util.stream.*;

public class ByteToBinary {

    public static String[] byteArrayToBinaryStringArray (byte[] byteArray, int n) {
        int dataLength = n * 8;
        return IntStream.range(0, byteArray.length)
                .mapToObj(i -> String.format("%8s", Integer.toBinaryString(byteArray[i] & 0xFF)).replace(' ', '0'))
                .collect(Collectors.joining())
                .replaceAll("(.{" + dataLength + "})", "$1 ")
                .split(" ");
    }

    public static byte[] bitStringToByteArray(String bitString) {
        int length = bitString.length();
        int byteLength = (int) Math.ceil((double) length / 8);

        byte[] byteArray = new byte[byteLength];

        for (int i = 0; i < byteLength; i++) {
            int start = i * 8;
            int end = Math.min(start + 8, length);

            String byteSubstring = bitString.substring(start, end);
//            byteSubstring = String.format("%-8s", byteSubstring).replace(' ', '0');

            byte byteValue = (byte) Integer.parseInt(byteSubstring, 2);
            byteArray[i] = byteValue;
        }

        return byteArray;
    }

    public static String byteToBinaryString (byte b) {
        return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    }

    public static byte binaryStringToByte (String bitString) {
        bitString = String.format("%-8s", bitString).replace(' ', '0');
        return (byte) Integer.parseInt(bitString, 2);
    }

}
