package applicationfolder.utils;

public class TestContent {
    private String question;
    private String[] answers = new String[4];
    private int correctAnswer;

    public void setAnswers(String[] answer) {
        this.answers = answer;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
