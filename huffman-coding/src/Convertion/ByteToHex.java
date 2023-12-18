package Convertion;
import java.util.stream.*;

public class ByteToHex {

    public static String[] byteArrayToHexStringArray(byte[] byteArray, int n) {
        int dataLength = n * 2;
        return IntStream.range(0, byteArray.length)
                .mapToObj(i -> String.format("%02X", byteArray[i]))
                .collect(Collectors.joining())
                .replaceAll("(.{" + dataLength + "})", "$1 ")
                .split(" ");
    }

    public static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        int byteLength = (int) Math.ceil((double) length / 2);

        byte[] byteArray = new byte[byteLength];

        for (int i = 0; i < byteLength; i++) {
            int start = i * 2;
            int end = Math.min(start + 2, length);

            String byteSubstring = hexString.substring(start, end);
//            byteSubstring = String.format("%-2s", byteSubstring).replace(' ', '0');

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

    public static void main(String[] args) {
//        String path = "D:\\College\\Third Year\\First Term\\Networks\\Sheets\\Sheet 1.pdf";
//
//        Reader reader = new Reader(path, 15);
//        byte[] firstChunk = reader.readChunk();
//        for (byte b: firstChunk)
//            System.out.println(b);
//        System.out.println("First byte: " + firstChunk[11] + ", Second one: " + firstChunk[12]);
//        String[] binary = byteArrayToBinaryStringArray(firstChunk, 2*8);
////        for (String s: binary)
////            System.out.println(s);
//        System.out.println("Binary Representation: " + binary[6]);

//        String test = "te  A\n";
//        byte[] bytes = test.getBytes();
//        byte[] bytes = {-11, 15, 102, 32, 10};
//        for (byte b: bytes)
//            System.out.println(b);
//        String[] binary = byteArrayToBinaryStringArray(bytes, 8);
//        StringBuilder result = new StringBuilder();
//        for (String s: binary)
//            result.append(s);
//        byte[] after = bitStringToByteArray(String.valueOf(result));
//        for (byte a: after)
//            System.out.println(a);
//        System.out.println("string:" + byteToBinaryString((byte) 32));
        byte[] bytes = {10, 11, -11, -100, 125};
        String[] hexaRepresentation = byteArrayToHexStringArray(bytes, 2);
        for (String s: hexaRepresentation)
            System.out.println("s:" + s);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s:hexaRepresentation)
            stringBuilder.append(s);
        byte[] converted = hexStringToByteArray(String.valueOf(stringBuilder));
        for (byte b: converted)
            System.out.println("byte:" + b);
        System.out.println("New hex:" + byteToHexString(bytes[0]));
        System.out.println("New byte:" + hexStringToByte(byteToHexString(bytes[0])));
    }

}
