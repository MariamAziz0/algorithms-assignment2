package HandlingInputOutput;

import java.io.*;

public class Writer {

    private FileOutputStream fileOutputStream;
    private BufferedOutputStream bufferedOutputStream;
    private int bufferSize;
    private byte[] buffer;

    public Writer (String path, int bufferSize) {

        try {
            this.fileOutputStream = new FileOutputStream(path);
            this.bufferedOutputStream = new BufferedOutputStream(this.fileOutputStream);
            this.bufferSize = bufferSize;
            this.buffer = new byte[bufferSize];
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

            try {
                this.bufferedOutputStream.close();
                this.fileOutputStream.close();
            }
            catch (Exception e2) {
                System.out.println("Error in closing the stream of Output: " + e2.getMessage());
            }

            System.out.println("Error in writing chunk of data: " + e.getMessage());
        }
        return false;
    }

}
