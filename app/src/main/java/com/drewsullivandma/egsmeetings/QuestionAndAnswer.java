package com.drewsullivandma.egsmeetings;

/**
 * Created by dsullivan on 2/1/2018.
 */

public class QuestionAndAnswer {
    private int mQuestion;
    private int mAnswer;

    public QuestionAndAnswer(int question, int answer) {
        mQuestion = question;
        mAnswer = answer;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public int getAnswer() {
        return mAnswer;
    }

    public void setAnswer(int answer) {
        mAnswer = answer;
    }
}
