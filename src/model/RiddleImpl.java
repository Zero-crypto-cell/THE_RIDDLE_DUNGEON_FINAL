package model;

public class RiddleImpl implements IRiddle {
    private final String question;
    private final String answer;
    private final String hint;

    public RiddleImpl(String question, String answer, String hint) {
        this.question = question;
        this.answer = answer.toLowerCase().trim();
        this.hint = hint;
    }

    @Override
    public String getQuestion() { return question; }

    @Override
    public String getAnswer() { return answer; }

    @Override
    public String getHint() { return hint; }
}
