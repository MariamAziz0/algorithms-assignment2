package HandlingInputOutput;

import java.nio.file.*;

public class HelpingFunctions {

    private final static String ID = "20011889";

    public static String getCompressedPath (String inputPathString, int n) {

        Path inputPath = Paths.get(inputPathString);
        Path fileName = inputPath.getFileName();
        String newFileName = ID + "." + n + "." + fileName.toString() + ".hc";
        return inputPath.getParent().resolve(newFileName).toString();

    }

    public static String getDecompressionPath (String inputPathString) {

        Path inputPath = Paths.get(inputPathString);
        Path fileName = inputPath.getFileName();
        String newFileName = "extracted." + fileName.toString().replace(".hc", "");
        return inputPath.getParent().resolve(newFileName).toString();

    }

}
