package applicationfolder.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AppLogic {
    private final int NUMBEROFVARIANTS = 10;   //upper or lower case
    private FileReading fileReading = new FileReading();
    private ArrayList<TestContent> testContent = new ArrayList<>(NUMBEROFVARIANTS);
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();

    private int[] randomVariantsOfQuestions(int bound) {
        int[] randomVariants = new int[NUMBEROFVARIANTS];
        Random random = new Random();
        Set<Integer> generated = new HashSet<>();
        int tempInt;
        while (generated.size() < NUMBEROFVARIANTS){
            while (generated.contains(tempInt = random.nextInt(bound)));
            generated.add(tempInt);
        }
        int i = 0;
        for (int gen : generated) {
            randomVariants[i] = gen;
            i++;
        }
        return randomVariants;
    }

    private void splitFileText(String fileName, boolean isResource) {
        try {
            ArrayList<String> text = fileReading.fileText(isResource ? getClass().getResourceAsStream(fileName) : new FileInputStream(fileName));

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

    protected ArrayList<TestContent> testing(String fileName, boolean isResource) {
        splitFileText(fileName, isResource);
        int[] IndexesOfVariants = randomVariantsOfQuestions(questions.size());
        for (int i = 0; i < NUMBEROFVARIANTS; i++) {
            TestContent unitOfContent = new TestContent();

            for (int j = 0; j < 4; j++) {
                if (answers.get(IndexesOfVariants[i] * 4 + j).endsWith("!true!")) unitOfContent.setCorrectAnswer(j + 1);
            }
            unitOfContent.setQuestion(questions.get(IndexesOfVariants[i]));
            for (int j = 0; j < 4; j++) {
                String str = answers.get(IndexesOfVariants[i] * 4 + j);
                str = str.replaceAll("!true!", "");
                unitOfContent.setAnswers(str, j);
            }
            testContent.add(unitOfContent);
        }
        return testContent;
    }
}