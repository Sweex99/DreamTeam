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

    private static int randomInt(int to) {
        int n = 0 + (int) (Math.random() * to);
        return n;
    }

    private void splitFileText(String fileName, boolean isResource) {
        try {
            text = fileReading.filetext(isResource ? getClass().getResourceAsStream(fileName) : new FileInputStream(fileName));

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

    public ArrayList<TestContent> testing(String fileName, boolean isResource) {
        int random;
        int [] IndexOfVariants = new int[10];
        splitFileText(fileName, isResource);
        for(int i = 0; i < 10; i++) {
            TestContent unitOfContent = new TestContent();
            random = randomInt(questions.size());
            for (int j = 0; j < 10; j++) {
                while (IndexOfVariants[j] == random) random = randomInt(questions.size());
            }
            IndexOfVariants[i] = random;

            for (int j = 0; j < 4; j++) {
                if (answers.get(IndexOfVariants[i] * 4 + j).endsWith("!true!")) unitOfContent.setCorrectAnswer(j + 1);
            }
            unitOfContent.setQuestion(questions.get(IndexOfVariants[i]));
            for (int j = 0; j < 4; j++) {
                String str = answers.get(IndexOfVariants[i] * 4 + j);
                str = str.replaceAll("!true!", "");
                unitOfContent.setAnswers(str, j);
            }
            testContent.add(unitOfContent);
        }
        return testContent;
    }
}