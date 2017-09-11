package DT;

import java.io.FileReader;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {

        FileReader inputStream = null;

        try {
            inputStream = new FileReader("d:\\first.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                //посимвольно записуємо у файл і виводимо на екран
                //outputStream.write(c);
                System.out.print(Character.toChars(c));
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}