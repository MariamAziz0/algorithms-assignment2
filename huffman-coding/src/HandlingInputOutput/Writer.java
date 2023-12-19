package HandlingInputOutput;

import java.io.*;

/**
 * I acknowledge that I am aware of the academic integrity guidelines of this course, and that I worked on this assignment independently without any unauthorized help
 */
public class Writer {

    private FileOutputStream fileOutputStream;
    private BufferedOutputStream bufferedOutputStream;

    public Writer (String path) {

        try {
            this.fileOutputStream = new FileOutputStream(path);
            this.bufferedOutputStream = new BufferedOutputStream(this.fileOutputStream);
        }
        catch (Exception e) {
            System.out.println("Error in the Writer: " + e.getMessage());
        }

    }

    public boolean writeChunk (byte[] chunk) {

        try {
            this.bufferedOutputStream.write(chunk);
            return true;
        }
        catch (Exception e) {
            System.out.println("Error in writing chunk of data: " + e.getMessage());
            return close();
        }
    }

    public boolean close () {
        try {
            this.bufferedOutputStream.close();
            this.fileOutputStream.close();
            return true;
        }
        catch (Exception e) {
            System.out.println("Error in closing the stream of Output: " + e.getMessage());
            return false;
        }
    }

}
