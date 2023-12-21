package Convertion;

import java.util.Arrays;

public class test {

    public static void main(String[] args) {
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
//        byte[] bytes = {10, 11, -11, -100, 125};
//        String[] hexaRepresentation = byteArrayToHexStringArray(bytes, 2);
//        for (String s: hexaRepresentation)
//            System.out.println("s:" + s);
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String s:hexaRepresentation)
//            stringBuilder.append(s);
//        byte[] converted = hexStringToByteArray(String.valueOf(stringBuilder));
//        for (byte b: converted)
//            System.out.println("byte:" + b);
//        System.out.println("New hex:" + byteToHexString(bytes[0]));
//        System.out.println("New byte:" + hexStringToByte(byteToHexString(bytes[0])));

//        String binary = "1100000001";
//        int bits = (int) Math.ceil(binary.length() / 8.0) * 8 - binary.length();
//        String newBinary = "0".repeat(bits) + binary;
//        byte[] bytes = ByteToBinary.bitStringToByteArray(newBinary);
//        System.out.println("b:" + Arrays.toString(bytes));
//        String hex = "0A";
//        byte[] b = ByteToHex.hexStringToByteArray(hex);
//        for (byte by: b) {
//            System.out.println("b:" + by);
//        }
//        String s = "01234567890123456789";
//        System.out.println(s.substring(0, (s.length() - 4) )+ ", its length:" + (s.length() - 4));
//        System.out.println(s.substring((s.length() / 8) * 8));

//        String binary = "01";
//        System.out.println("Leading: " + ByteToBinary.binaryStringToByteWithLeadingZeros(binary) + ", Trailing: " + ByteToBinary.binaryStringToByteWithTrailingZeros(binary));

//        byte[] bytes = {10, 15, -15};
//
//        System.out.println("Binary string:" + ByteToBinary.byteArrayToBinaryString(bytes) + ".");
//        byte[] bytes = {-1, 0, 2, 0, 10, 0, 5, 0};
//        long result = ByteToInteger.convertBytesToLong(bytes, 0, 7);
//        System.out.println("Long: " + result);
//        byte[] newBytes = ByteToInteger.convertLongToBytes(result);
//        System.out.println("New bytes: " + Arrays.toString(newBytes));
//        byte[] bytes = new byte[50];
//        long totalBytes = 4125836;
//        byte bits = 3;
//        int entries = 15823;
//        int result = ByteToInteger.convertMetaDataHeader(totalBytes, bits, entries, bytes, 0);
//        System.out.println("bytes: " + Arrays.toString(bytes));
//        System.out.println("totalBytes: " + ByteToInteger.convertBytesToLong(bytes, 0, 7));
//        System.out.println("bits: " + bytes[8]);
//        System.out.println("entries: " + ByteToInteger.convertBytesToInt(bytes, 9, 12));
//
//        int[] array1 = {1, 2};
//        int[] array2 = new int[15];
//        System.arraycopy(array1, 0, array2, 5, array1.length);
//        System.out.println(Arrays.toString(array2));

//        String binary = "1100000001";
//        int bits = (int) Math.ceil(binary.length() / 8.0) * 8 - binary.length();
//        String newBinary = "0".repeat(bits) + binary;

//        byte bits = 5;
//        int bytesBit = 2;
//        int bytesHex = 3;
//        String binary = "00100000010";
//        String hex = ByteToHex.byteToHexString((byte)10) + ByteToHex.byteToHexString((byte)11) + ByteToHex.byteToHexString((byte)-15);
//        byte[] array = new byte[20];
//        int starting = MetaDataConverter.convertEntry(bits, bytesBit, bytesHex, binary, hex, array, 6);
//        System.out.println("New starting position: " + starting);
//        System.out.println(Arrays.toString(array));

        byte[] bytes = {10, -10, 15};

        String hex = ByteToHex.byteArrayToHexString(bytes);
        System.out.println(hex);
    }

}
