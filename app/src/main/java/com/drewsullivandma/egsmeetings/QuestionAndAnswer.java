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

    public int getQuestion() { return mQuestion; }

    public int getAnswer() {
        return mAnswer;
    }
}
