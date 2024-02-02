package HandlingInputOutput;

import Convertion.ByteToBinary;
import Convertion.ByteToHex;
import Convertion.MetaDataConverter;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        Reader reader = new Reader("D:\\College\\Third Year\\First Term\\Algorithms\\Labs\\Assignment2\\files\\20011889.1.huffman_test1.txt.hc", 10*1024);

        byte[] bytes = reader.getBytes(8);

        for(byte b : bytes) {
            System.out.print(b + " ");
        }
        System.out.println();

        byte bits = reader.getBytes(1)[0];
        System.out.println("bits: " + bits);

        int integer = MetaDataConverter.convertBytesToInt(reader.getBytes(4), 0 , 3);
        System.out.println("Entries: " + integer);

        while (integer > 0) {
            byte bitsPadded = reader.getBytes(1)[0];
            int byteBits = MetaDataConverter.convertBytesToInt(reader.getBytes(4), 0, 3);
            int byteHex = MetaDataConverter.convertBytesToInt(reader.getBytes(4), 0, 3);

            byte[] byteArrayBits = reader.getBytes(byteBits);
            byte[] byteArrayHex = reader.getBytes(byteHex);

            String bitString = ByteToBinary.byteArrayToBinaryString(byteArrayBits);
            bitString = bitString.substring(bitsPadded);

            String hexString = ByteToHex.byteArrayToHexString(byteArrayHex);
//            System.out.println("bit string:" + bitString + ", hex string: " + hexString);

            integer--;
        }

        System.out.println(Arrays.toString(reader.readChunk()));

    }


}
