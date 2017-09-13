package applicationfolder;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        AppLogic appLogic = new AppLogic();
        appLogic.testing();

        /*String question;
        ArrayList<String> questions = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new FileReader("src\\applicationfolder\\text.txt"));

        File file = new File("src\\applicationfolder\\text.txt");
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }

        try {
            try {
                while ((question = in.readLine()) != null) {
                    if (question.charAt(question.length() - 1) == '?') {
                        questions.add(question);
                    }
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(questions.get(0));
        System.out.println(questions.get(1));
        System.out.println(questions.get(2));*/
    }
}
