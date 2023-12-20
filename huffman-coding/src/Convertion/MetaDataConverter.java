package Convertion;

public class MetaDataConverter {

    // return the start index for next operation
    public static int convertMetaDataHeader (long totalBytes, byte bitsInLastByte, int entries, byte[] bytesArray, int start) {

        for (int i = start + 7 ; i >= start ; i--) {
            bytesArray[i] = (byte) (totalBytes & 0xFF);
            totalBytes >>= 8;
        }

        bytesArray[start + 8] = bitsInLastByte;

        for (int i = start + 12 ; i >= start + 9 ; i--) {
            bytesArray[i] = (byte) (entries & 0xFF);
            entries >>= 8;
        }

        return start + 13;

    }

    // convert each entry in hash map and related meta-data to bytes to be put in the array
    public static int convertEntry (byte bitsPadded, int bytesBits, int bytesHex, String bits, String hex, byte[] byteArray, int start) {
        // number of padded bits
        byteArray[start] = bitsPadded;
        start++;

        // number of bytes of bits string
        for (int i = start + 3 ; i >= start ; i--) {
            byteArray[i] = (byte) (bytesBits & 0xFF);
            bytesBits >>= 8;
        }
        start += 4;

        // number of bytes of hex string
        for (int i = start + 3 ; i >= start ; i--) {
            byteArray[i] = (byte) (bytesHex & 0xFF);
            bytesHex >>= 8;
        }
        start += 4;

        // string of bits
        String newBits = "0".repeat(bitsPadded) + bits;
        byte[] byteArrayFromBits = ByteToBinary.bitStringToByteArray(newBits);
        System.arraycopy(byteArrayFromBits, 0, byteArray, start, byteArrayFromBits.length);
        start += byteArrayFromBits.length;

        // string of hex
        byte[] byteArrayFromHex = ByteToHex.hexStringToByteArray(hex);
        System.arraycopy(byteArrayFromHex, 0, byteArray, start, byteArrayFromHex.length);
        start += byteArrayFromHex.length;


        return start;
    }

    public static long convertBytesToLong(byte[] byteArray, int startIndex, int lastIndex) {

        long result = 0;
        for (int i = startIndex ; i <= lastIndex ; i++) {
            result = (result << 8) | (byteArray[i] & 0xFF);
        }

        return result;
    }

    public static int convertBytesToInt(byte[] byteArray, int startIndex, int lastIndex) {

        int result = 0;
        for (int i = startIndex ; i <= lastIndex ; i++) {
            result = (result << 8) | (byteArray[i] & 0xFF);
        }

        return result;
    }

    public static byte[] convertLongToBytes(long value) {
        byte[] byteArray = new byte[8];

        for (int i = 7 ; i >= 0 ; i--) {
            byteArray[i] = (byte) (value & 0xFF);
            value >>= 8;
        }

        return byteArray;
    }

    public static byte[] convertIntToBytes(int value) {
        byte[] byteArray = new byte[4];

        for (int i = 3 ; i >= 0 ; i--) {
            byteArray[i] = (byte) (value & 0xFF);
            value >>= 8;
        }

        return byteArray;
    }

}
