package applicationfolder.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static org.apache.commons.io.IOUtils.readLines;

public class AppLogic {
    private final int NUMBER_OF_VARIANTS = 10;   //upper or lower case
    private List<TestContent> testContent = new ArrayList<>(NUMBER_OF_VARIANTS);
    private List<String> questions = new ArrayList<>();
    private List<String> answers = new ArrayList<>();

    private int[] randomVariantsOfQuestions(int bound) {
        int[] randomVariants = new int[NUMBER_OF_VARIANTS];
        Random random = new Random();
        Set<Integer> generated = new HashSet<>();
        int tempInt;
        while (generated.size() < NUMBER_OF_VARIANTS){
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
            List<String> text = readLines(isResource ? getClass().getResourceAsStream(fileName) : new FileInputStream(fileName), "UTF-8");

            for (int i = 1; i < text.size(); i++) {
                String s = text.get(i);
                if (s.charAt(s.length() - 1) == '?') {
                    questions.add(s);
                } else {
                    answers.add(s);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<TestContent> testing(String fileName, boolean isResource) {
        splitFileText(fileName, isResource);
        int[] IndexesOfVariants = randomVariantsOfQuestions(questions.size());
        for (int i = 0; i < NUMBER_OF_VARIANTS; i++) {
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