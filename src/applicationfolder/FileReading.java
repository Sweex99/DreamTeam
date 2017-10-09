package applicationfolder;

import java.io.*;
import java.util.ArrayList;

public class FileReading {
    public ArrayList<String> filetext(String fileName) throws FileNotFoundException {
        ArrayList<String> text = new ArrayList<String>();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName)))) {
            String s;
            while ((s = in.readLine()) != null) {
                text.add(s);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return text;
    }
}