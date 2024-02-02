package HandlingInputOutput;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class CheckEquality {

    public static boolean areFilesEqual(String filePath1, String filePath2) {
        try {
            MessageDigest md1 = MessageDigest.getInstance("SHA-256");
            MessageDigest md2 = MessageDigest.getInstance("SHA-256");

            try (DigestInputStream dis1 = new DigestInputStream(new FileInputStream(filePath1), md1);
                 DigestInputStream dis2 = new DigestInputStream(new FileInputStream(filePath2), md2)) {

                while (dis1.read() != -1) ;
                while (dis2.read() != -1) ;
            }

            byte[] hash1 = md1.digest();
            byte[] hash2 = md2.digest();

            // Compare the hash values
            return MessageDigest.isEqual(hash1, hash2);

        } catch (Exception e) {
            e.printStackTrace();
            return false; // An error occurred, consider the files as not equal
        }
    }

    public static void main(String[] args) {
        String filePath1 = "D:\\College\\Third Year\\First Term\\Algorithms\\Labs\\Assignment2\\files\\test256bytes.txt";
        String filePath2 = "D:\\College\\Third Year\\First Term\\Algorithms\\Labs\\Assignment2\\files\\extracted.20011889.1.test256bytes.txt";

        boolean areEqual = areFilesEqual(filePath1, filePath2);

        if (areEqual) {
            System.out.println("The files are equivalent.");
        } else {
            System.out.println("The files are not equivalent.");
        }
//        String s1 = "8805A72E8B2B15DBDB3AD95DD401A32CE675E1FF14C63CDD8538A96B996966F6";
//        String s2 = "8805A72E8B2B15DBDB3AD95DD401A32CE675E1FF14C63CDD8538A96B996966F6";
//        System.out.println(s1.equals(s2));
    }
}
