package applicationfolder.utils;

public class TestContent {
    private String question;
    private String[] answers = new String[4];
    private int correctAnswer;

    public void setAnswers(String answer, int i) {
        this.answers[i] = answer;
    }

    public String getAnswers(int i) {
        return answers[i];
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correct_answer) {
        this.correctAnswer = correct_answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
