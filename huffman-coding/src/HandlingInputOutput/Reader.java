package HandlingInputOutput;

import java.io.*;

/**
 * I acknowledge that I am aware of the academic integrity guidelines of this course, and that I worked on this assignment independently without any unauthorized help
 */
public class Reader {

    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;
    private byte[] buffer;
    private int bufferSize;
    private int currentIndex;

    public Reader (String path, int bufferSize) {

        try {
            this.fileInputStream = new FileInputStream(path);
            this.bufferedInputStream = new BufferedInputStream(this.fileInputStream);
            this.bufferSize = bufferSize;
            this.buffer = new byte[bufferSize];
            this.currentIndex = -1;
        }
        catch (Exception e) {
            System.out.println("Error in the Reader: " + e.getMessage());
        }

    }

    public byte[] readChunk () {

        try {
            int bytesRead = bufferedInputStream.read(buffer);
            this.currentIndex = 0;

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

            if (bytesRead < this.bufferSize) {
                byte[] chunk = new byte[bytesRead];
                System.arraycopy(buffer, 0, chunk, 0, bytesRead);
                return chunk;
            }

            return buffer;
        }
        catch (Exception e) {
            System.out.println("Error in getting chunk of data: " + e.getMessage());
            return null;
        }

    }

    public byte[] getBytes (int bytes) {

        byte[] result = new byte[bytes];

        if (this.currentIndex == -1 || this.currentIndex >= this.buffer.length) {
            this.readChunk();
            currentIndex = 0;
        }

        if (bytes <= this.buffer.length - this.currentIndex) {
            System.arraycopy(this.buffer, this.currentIndex, result, 0, bytes);
            this.currentIndex += bytes;
        }
        else {
            int remainingInBuffer = this.buffer.length - this.currentIndex;
            System.arraycopy(this.buffer, this.currentIndex, result, 0, remainingInBuffer);
            this.readChunk();
            this.currentIndex = 0;

            int remainingToRead = bytes - remainingInBuffer;
            System.arraycopy(this.buffer, this.currentIndex, result, remainingInBuffer, remainingToRead);
            this.currentIndex += remainingToRead;
        }

        return result;
    }

}
