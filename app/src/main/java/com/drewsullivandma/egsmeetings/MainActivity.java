package com.drewsullivandma.egsmeetings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mYesButton;
    private Button mNoButton;
    private Button mPreviousButton;
    private Button mStartOverButton;
    private TextView mQuestionTextView;
    private TextView mAnswerTextView;
    private QuestionAndAnswer[] mQsAndAs = new QuestionAndAnswer[] {
        new QuestionAndAnswer(R.string.q_1, R.string.q_1_toast),
        new QuestionAndAnswer(R.string.q_2, R.string.q_2_toast),
        new QuestionAndAnswer(R.string.q_3, R.string.q_3_toast),
        new QuestionAndAnswer(R.string.q_4, R.string.q_4_toast),
    };
    private int mCurrentIndex = 0;

    private void updateQuestion() {
        if (mCurrentIndex != 0) {
            mQuestionTextView.setBackgroundResource(R.drawable.non_first_color_question);
        } else {
            mQuestionTextView.setBackgroundResource(R.drawable.first_question_color);
        }
        int question = mQsAndAs[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = findViewById(R.id.question_text_view);
        mAnswerTextView = findViewById(R.id.answer_text_view);
        updateQuestion();

        mYesButton = findViewById(R.id.yes_button);
        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex += 1;
                if (mCurrentIndex == mQsAndAs.length) {
                    mAnswerTextView.setText(R.string.schedule_meeting);
                    hideYesNoPrevButtons();
                    hideQuestionTextView();
                    showAnswerAndStartOverButton();
                    mCurrentIndex -= 1;
                } else {
                    updateQuestion();
                }
            }
        });
        mNoButton = findViewById(R.id.no_button);
        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideYesNoPrevButtons();
                int answerId = mQsAndAs[mCurrentIndex].getAnswer();
                mAnswerTextView.setText(answerId);
                mStartOverButton.setText(R.string.start_over_button);
                hideQuestionTextView();
                showAnswerAndStartOverButton();
            }
        });
        mPreviousButton = findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex > 0) {
                    mNoButton.setEnabled(true);
                    mCurrentIndex -= 1;
                    updateQuestion();
                } else {
                    Toast.makeText(MainActivity.this, R.string.toast_prev_beginning, Toast.LENGTH_SHORT).show();
                }
            }
        });
        mStartOverButton = findViewById(R.id.start_over_button);
        mStartOverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = 0;
                showYesNoPrevButtons();
                hideAnswerAndStartOverButton();
                updateQuestion();
                showQuestionTextView();
            }
        });
    }

    private void hideYesNoPrevButtons() {
        mYesButton.setVisibility(View.INVISIBLE);
        mNoButton.setVisibility(View.INVISIBLE);
        mPreviousButton.setVisibility(View.INVISIBLE);
    }

    private void showYesNoPrevButtons() {
        mYesButton.setVisibility(View.VISIBLE);
        mNoButton.setVisibility(View.VISIBLE);
        mPreviousButton.setVisibility(View.VISIBLE);
    }

    private void hideAnswerAndStartOverButton() {
        mAnswerTextView.setVisibility(View.INVISIBLE);
        mStartOverButton.setVisibility(View.INVISIBLE);
    }

    private void showAnswerAndStartOverButton() {
        mAnswerTextView.setVisibility(View.VISIBLE);
        mStartOverButton.setVisibility(View.VISIBLE);
    }

    private void hideQuestionTextView() {
        mQuestionTextView.setVisibility(View.INVISIBLE);
    }

    private void showQuestionTextView() {
        mQuestionTextView.setVisibility(View.VISIBLE);
    }
}
