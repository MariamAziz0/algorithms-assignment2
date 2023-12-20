package Convertion;
import java.util.stream.*;

public class ByteToHex {

//    public static String[] byteArrayToHexStringArray(byte[] byteArray, int n) {
//        int dataLength = n * 2;
//        return IntStream.range(0, byteArray.length)
//                .mapToObj(i -> String.format("%02X", byteArray[i]))
//                .collect(Collectors.joining())
//                .replaceAll("(.{" + dataLength + "})", "$1 ")
//                .split(" ");
//    }

    public static String[] byteArrayToHexStringArray(byte[] byteArray, int n) {
        int length = byteArray.length;
        int numStrings = (length + n - 1) / n; // Calculate the number of strings needed

        String[] hexStringArray = new String[numStrings];

        for (int i = 0; i < numStrings; i++) {
            int startIndex = i * n;
            int endIndex = Math.min(startIndex + n, length);

            StringBuilder hexString = new StringBuilder();
            for (int j = startIndex; j < endIndex; j++) {
                hexString.append(String.format("%02X", byteArray[j]));
            }

            hexStringArray[i] = hexString.toString();
        }

        return hexStringArray;
    }

    public static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        int byteLength = (int) Math.ceil((double) length / 2);

        byte[] byteArray = new byte[byteLength];

        for (int i = 0; i < byteLength; i++) {
            int start = i * 2;
            int end = Math.min(start + 2, length);

            String byteSubstring = hexString.substring(start, end);

            byte byteValue = (byte) Integer.parseInt(byteSubstring, 16);
            byteArray[i] = byteValue;
        }

        return byteArray;
    }

    public static String byteToHexString (byte b) {
        return String.format("%02X", b);
    }

    public static byte hexStringToByte (String hexString) {
        hexString = String.format("%-2s", hexString).replace(' ', '0');
        return (byte) Integer.parseInt(hexString, 16);
    }

}
