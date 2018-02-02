package com.drewsullivandma.egsmeetings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mYesButton;
    private Button mNoButton;
    private Button mPreviousButton;
    private Button mStartOverButton;
    private TextView mQuestionAndAnswerTextView;
    private QuestionAndAnswer[] mQsAndAs = new QuestionAndAnswer[] {
        new QuestionAndAnswer(R.string.q_1, R.string.q_1_toast),
        new QuestionAndAnswer(R.string.q_2, R.string.q_2_toast),
        new QuestionAndAnswer(R.string.q_3, R.string.q_3_toast),
        new QuestionAndAnswer(R.string.q_4, R.string.q_4_toast),
    };
    private int mCurrentIndex = 0;

    private void updateText() {
        if (mCurrentIndex == 0) {
            if (mPreviousButton != null) {
                mPreviousButton.setVisibility(View.INVISIBLE);
            }
            mQuestionAndAnswerTextView.setBackgroundResource(R.drawable.first_question_color);
        } else {
            mPreviousButton.setVisibility(View.VISIBLE);
            mQuestionAndAnswerTextView.setBackgroundResource(R.drawable.non_first_question_color);
        }
        int question = mQsAndAs[mCurrentIndex].getQuestion();
        mQuestionAndAnswerTextView.setText(question);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionAndAnswerTextView = findViewById(R.id.question_text_view);
        updateText();

        mYesButton = findViewById(R.id.yes_button);
        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex += 1;
                if (mCurrentIndex == mQsAndAs.length) {
                    toggleButtons();
                    mQuestionAndAnswerTextView.setText(R.string.schedule_meeting);
                } else {
                    updateText();
                }
            }
        });
        mNoButton = findViewById(R.id.no_button);
        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButtons();
                if (mCurrentIndex == 0) {
                    mQuestionAndAnswerTextView.setBackgroundResource(R.drawable.non_first_question_color);
                }
                int answer = mQsAndAs[mCurrentIndex].getAnswer();
                mQuestionAndAnswerTextView.setText(answer);
            }
        });
        mPreviousButton = findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex > 0) {
                    mCurrentIndex -= 1;
                }
                updateText();
            }
        });
        mStartOverButton = findViewById(R.id.start_over_button);
        mStartOverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = 0;
                toggleButtons();
                updateText();
            }
        });
    }

    private void toggleButtons() {
        if (mYesButton.getVisibility() == View.VISIBLE) {
            mYesButton.setVisibility(View.GONE);
            mNoButton.setVisibility(View.GONE);
            mPreviousButton.setVisibility(View.GONE);
            mStartOverButton.setVisibility(View.VISIBLE);
        } else {
            mYesButton.setVisibility(View.VISIBLE);
            mNoButton.setVisibility(View.VISIBLE);
            mPreviousButton.setVisibility(View.VISIBLE);
            mStartOverButton.setVisibility(View.INVISIBLE);
        }
    }
}
