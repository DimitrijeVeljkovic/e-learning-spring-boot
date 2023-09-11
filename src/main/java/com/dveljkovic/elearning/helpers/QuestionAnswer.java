package com.dveljkovic.elearning.helpers;

public class QuestionAnswer {

    private int questionId;
    private String answer;

    public QuestionAnswer() {

    }

    public QuestionAnswer(int questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
