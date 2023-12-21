import HandlingInputOutput.Reader;
import HandlingInputOutput.Writer;

/**
 * I acknowledge that I am aware of the academic integrity guidelines of this course, and that I worked on this assignment independently without any unauthorized help
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        String path = "C:\\Users\\DELL\\Videos\\Captures\\IMDb_ Lucidchart - Google Chrome 2022-11-01 21-16-19.mp4";
        String path2 = "C:\\Users\\DELL\\Videos\\Captures\\IMDb_ Lucidchart - Google Chrome 2022-11-01 21-16-19output.mp4";;

        int bufferSize = 1048576;
        int i = 1;
        Reader reader = new Reader(path, bufferSize);
        Writer writer = new Writer(path2);
        byte[] chunk;
        while ((chunk = reader.readChunk()) != null) {
            System.out.println("Reading chunk: " + i);
            String content = new String(chunk);
//            System.out.println(content);
            writer.writeChunk(chunk);
            System.out.println("Chunk length: " + chunk.length);
            i++;
        }
    }
}