package applicationfolder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AppLogic {
    FileReading fileReading = new FileReading();
    private ArrayList<TestContent> testContent = new ArrayList<TestContent>(10);
    private ArrayList<String> text = new ArrayList<String>();
    private ArrayList<String> questions = new ArrayList<String>();
    private ArrayList<String> answers = new ArrayList<String>();

    private static int random_int(int to) {
        int n = 0 + (int) (Math.random() * to);
        return n;
    }

    private void SplitFileText(String fileName, boolean is_resource) {
        try {
            text = fileReading.filetext(is_resource ? getClass().getResourceAsStream(fileName) : new FileInputStream(fileName));

            for (int i = 1; i < text.size(); i++) {
                String s = text.get(i);
                if (s.charAt(s.length() - 1) == '?') {
                    questions.add(s);
                } else {
                    answers.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<TestContent> testing(String fileName, boolean is_resource) {
        int random;
        int [] IndexOfVariants = new int[10];
        SplitFileText(fileName, is_resource);
        for(int i = 0; i < 10; i++) {
            TestContent unit_of_content = new TestContent();
            random = random_int(questions.size());
            for (int j = 0; j < 10; j++) {
                while (IndexOfVariants[j] == random) random = random_int(questions.size());
            }
            IndexOfVariants[i] = random;

            for (int j = 0; j < 4; j++) {
                if (answers.get(IndexOfVariants[i] * 4 + j).endsWith("!true!")) unit_of_content.setCorrectAnswer(j + 1);
            }
            unit_of_content.setQuestion(questions.get(IndexOfVariants[i]));
            for (int j = 0; j < 4; j++) {
                String str = answers.get(IndexOfVariants[i] * 4 + j);
                str = str.replaceAll("!true!", "");
                unit_of_content.setAnswers(str, j);
            }
            testContent.add(unit_of_content);
        }
        return testContent;
    }
}