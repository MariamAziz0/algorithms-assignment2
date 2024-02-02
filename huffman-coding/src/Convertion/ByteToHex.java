package Convertion;
import java.util.stream.*;

public class ByteToHex {

    private static final char[] hexDigits = "0123456789ABCDEF".toCharArray();

    public static String[] byteArrayToHexStringArray(byte[] byteArray, int n) {
        int length = byteArray.length;
        int numStrings = (length + n - 1) / n;

        String[] hexStringArray = new String[numStrings];
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < numStrings; i++) {
            int startIndex = i * n;
            int endIndex = Math.min(startIndex + n, length);

            for (int j = startIndex; j < endIndex; j++) {
                int value = byteArray[j] & 0xFF;
                hexString.append(hexDigits[value >>> 4]).append(hexDigits[value & 0x0F]);
            }

            hexStringArray[i] = hexString.toString();
            hexString.setLength(0);
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

//    public static String byteArrayToHexString (byte[] byteArray) {
//        return IntStream.range(0, byteArray.length)
//                .mapToObj(i -> String.format("%02X", byteArray[i]))
//                .collect(Collectors.joining());
//    }
    public static String byteArrayToHexString(byte[] byteArray) {
        int length = byteArray.length;
        StringBuilder hexString = new StringBuilder(length * 2);

        for (int i = 0; i < length; i++) {
            int value = byteArray[i] & 0xFF;
            hexString.append(hexDigits[value >>> 4]).append(hexDigits[value & 0x0F]);
        }

        return hexString.toString();
    }
}
