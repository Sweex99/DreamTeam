package applicationfolder;

import java.io.*;
import java.util.ArrayList;

public class FileReading {
    public static ArrayList<String> filetext (String fileName) throws FileNotFoundException {
        ArrayList<String> text = new ArrayList<String>();

        exists(fileName);

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String s;
            while ((s = in.readLine()) != null) {
                text.add(s);
            }
            in.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return text;
    }

    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }
}
