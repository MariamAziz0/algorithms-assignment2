package HandlingInputOutput;

import java.io.*;

public class Reader {

    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;
    private byte[] buffer;
    private int bufferSize;

    public Reader (String path, int bufferSize) {

        try {
            this.fileInputStream = new FileInputStream(path);
            this.bufferedInputStream = new BufferedInputStream(this.fileInputStream);
            this.bufferSize = bufferSize;
            this.buffer = new byte[bufferSize];
        }
        catch (Exception e) {
            System.out.println("Error in the Reader: " + e.getMessage());
        }

    }

    public byte[] readChunk () {

        try {
            int bytesRead = bufferedInputStream.read(buffer);

            if (bytesRead == -1) {

                try {
                    this.bufferedInputStream.close();
                    this.fileInputStream.close();
                }
                catch (Exception e) {
                    System.out.println("Error in closing the stream of Input: " + e.getMessage());
                }

                return null;
            }

            byte[] chunk = new byte[bytesRead];
            System.arraycopy(buffer, 0, chunk, 0, bytesRead);
            return chunk;
        }
        catch (Exception e) {
            System.out.println("Error in getting chunk of data: " + e.getMessage());
            return null;
        }

    }

}
