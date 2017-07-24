package com.example.matt.mathquiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity{


    enum Difficulty {EASY , MEDIUM, HARD}
    TextView textScore, textRound, textLives, textHighScore;
    EditText editAnswer;
    boolean questionAsked = false, gameOver = false;
    int answer, lives = 3;


    int qvalue = 50, round = 1 , points = 0, answered = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button buttonRed = (Button) findViewById(R.id.btnRed);
        final Button buttonBlue = (Button) findViewById(R.id.btnBlue);
        final Button buttonPink = (Button) findViewById(R.id.btnPink);
        final Button buttonGreen = (Button) findViewById(R.id.btnGreen);
        final Button buttonYellow = (Button) findViewById(R.id.btnYellow);
        final Button buttonPurple = (Button) findViewById(R.id.btnPurple);
        final Button buttonAnswer = (Button) findViewById(R.id.btnAnswer);

        textScore = (TextView) findViewById(R.id.textCurrentScore);
        textHighScore = (TextView) findViewById(R.id.textCurrentHighScore);
        textRound = (TextView) findViewById(R.id.textCurrentRound);
        textLives = (TextView) findViewById(R.id.textCurrentLives);
        final Difficulty difficulty = (Difficulty) getIntent().getSerializableExtra("Difficulty");
        highScore(difficulty);

        buttonRed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!questionAsked && !gameOver) {
                    answer = ask(difficulty);
                    buttonRed.setVisibility(View.INVISIBLE);
                    questionAsked = true;
                }
            }
        });

        buttonBlue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!questionAsked && !gameOver) {
                    answer = ask(difficulty);
                    buttonBlue.setVisibility(View.INVISIBLE);
                    questionAsked = true;
                }
            }
        });

        buttonPink.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!questionAsked && !gameOver) {
                    answer = ask(difficulty);
                    buttonPink.setVisibility(View.INVISIBLE);
                    questionAsked = true;
                }
            }
        });

        buttonGreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!questionAsked && !gameOver) {
                    answer = ask(difficulty);
                    buttonGreen.setVisibility(View.INVISIBLE);
                    questionAsked = true;
                }
            }
        });

        buttonYellow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!questionAsked && !gameOver) {
                    answer = ask(difficulty);
                    buttonYellow.setVisibility(View.INVISIBLE);
                    questionAsked = true;
                }
            }
        });

        buttonPurple.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!questionAsked && !gameOver) {
                    answer = ask(difficulty);
                    buttonPurple.setVisibility(View.INVISIBLE);
                    questionAsked = true;
                }
            }
        });

        buttonAnswer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                editAnswer = (EditText) findViewById(R.id.editAnswer);
                String Answer = editAnswer.getText().toString().trim();
                if (questionAsked && Answer.length() != 0 && !gameOver) {
                    if (checkAnswer())
                    {
                        points += qvalue;
                        textScore.setText(String.valueOf(points));
                    }
                    else
                    {
                        lives --;
                        textLives.setText(String.valueOf(lives));
                        if(lives == 0)
                        {
                            gameOver();
                        }
                    }
                    answered ++;
                    questionAsked = false;
                    clearQuestion();
                    if (answered == 6)
                    {
                        newRound();
                    }
                }
                else if (gameOver)
                {
                    restart(difficulty);
                }
            }
        });

    }
    void restart(Difficulty difficulty)
    {
        highScore(difficulty);
        gameOver = false;
        lives = 3;
        round = 1;
        points = 0;
        qvalue = 50;
        answered = 0;
        Button buttonAnswer = (Button) findViewById(R.id.btnAnswer);
        buttonAnswer.setText(R.string.submit);
        textScore.setText(String.valueOf(points));
        textLives.setText(String.valueOf(lives));
        textRound.setText(String.valueOf(round));
        setVisible();

    }

    void gameOver()
    {
        gameOver = true;
        Button buttonAnswer = (Button) findViewById(R.id.btnAnswer);
        buttonAnswer.setText(R.string.retry);
    }

    void highScore(Difficulty difficulty)
    {
        //store high score value for each difficulty within a shared preference.
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int storedHighScore = sharedPref.getInt("storedHighScore" + difficulty, points );

        if (points >= storedHighScore)
        {
            editor.putInt("storedHighScore" + difficulty, points);
            editor.apply();
            storedHighScore = points;
        }

        textHighScore.setText(String.valueOf(storedHighScore));
    }


    void clearQuestion()
    {
        TextView text1,text2,textO;
        editAnswer.setText("");
        text1 = (TextView) findViewById(R.id.textValue1);
        text1.setText("");
        text2 = (TextView) findViewById(R.id.textValue2);
        text2.setText("");
        textO = (TextView) findViewById(R.id.textOperator);
        textO.setText("");
    }

    void setVisible()
    {

        TextView textround;
        textround = (TextView) findViewById(R.id.textCurrentRound);
        textround.setText(String.valueOf(round));
        Button buttonRed = (Button) findViewById(R.id.btnRed);
        buttonRed.setVisibility(View.VISIBLE);
        Button buttonBlue = (Button) findViewById(R.id.btnBlue);
        buttonBlue.setVisibility(View.VISIBLE);
        Button buttonPink = (Button) findViewById(R.id.btnPink);
        buttonPink.setVisibility(View.VISIBLE);
        Button buttonGreen = (Button) findViewById(R.id.btnGreen);
        buttonGreen.setVisibility(View.VISIBLE);
        Button buttonYellow = (Button) findViewById(R.id.btnYellow);
        buttonYellow.setVisibility(View.VISIBLE);
        Button buttonPurple = (Button) findViewById(R.id.btnPurple);
        buttonPurple.setVisibility(View.VISIBLE);
    }

    void newRound()
    {
        answered = 0;
        qvalue = qvalue * 2;
        round ++;
        setVisible();
    }

    boolean checkAnswer()
    {
    editAnswer = (EditText) findViewById(R.id.editAnswer);
    int input = Integer.valueOf(editAnswer.getText().toString());
    return input == answer;
    }

    int ask(Difficulty difficulty)
    {
        Question question = new Question(difficulty);
        TextView text1,text2,textO;
        text1 = (TextView) findViewById(R.id.textValue1);
        text1.setText(String.format("%d",question.getValue1()));
        text2 = (TextView) findViewById(R.id.textValue2);
        text2.setText(String.format("%d",question.getValue2()));
        textO = (TextView) findViewById(R.id.textOperator);
        if (question.getOperator() == 0)
        {
            textO.setText("+");
        }
        else if (question.getOperator() == 1)
        {
            textO.setText("-");
        }
        else if (question.getOperator() == 2)
        {
            textO.setText("x");
        }
        else
        {
            textO.setText("/");
        }
        return question.getAnswer();
    }

}

 class Question {
    private int value1, value2, operator, answer;

     private static Random rand = new Random();

     Question(MainActivity.Difficulty dif) {
         this.operator = rand.nextInt(4);
    if (operator == 0) // for addition questions
    {
        if (dif == MainActivity.Difficulty.EASY) {
            value1 = rand.nextInt(100) + 1;
            value2 = rand.nextInt(100) + 1;
        } else if (dif == MainActivity.Difficulty.MEDIUM) {
            value1 = rand.nextInt(1000) + 1;
            value2 = rand.nextInt(1000) + 1;
        } else {
            value1 = rand.nextInt(10000) + 1;
            value2 = rand.nextInt(10000) + 1;
        }
        answer = value1 + value2;
    }
    if (operator == 1) // for subtraction questions
    {
        if (dif == MainActivity.Difficulty.EASY) {
            value1 = rand.nextInt(100) + 1;
            do {
                value2 = rand.nextInt(100) + 1;
            }
            while (value1 < value2);
        } else if (dif == MainActivity.Difficulty.MEDIUM) {
            value1 = rand.nextInt(1000) + 1;
            do {
                value2 = rand.nextInt(1000) + 1;
            }
            while (value1 < value2);
        } else {
            value1 = rand.nextInt(10000) + 1;
            do {
                value2 = rand.nextInt(10000) + 1;
            }
            while (value1 < value2);
        }
        answer = value1 - value2;
    }
    if (operator == 2) // for multiplication questions
    {
        if (dif == MainActivity.Difficulty.EASY) {
            value1 = rand.nextInt(10) + 1;
            value2 = rand.nextInt(10) + 1;
        } else if (dif == MainActivity.Difficulty.MEDIUM) {
            value1 = rand.nextInt(20) + 1;
            value2 = rand.nextInt(20) + 1;
        } else {
            value1 = rand.nextInt(50) + 1;
            value2 = rand.nextInt(50) + 1;
        }
        answer = value1 * value2;
    }
    if (operator == 3) // for division questions
    {
        do {
            if (dif == MainActivity.Difficulty.EASY) {
                value1 = rand.nextInt(10) + 1;
                value2 = rand.nextInt(10) + 1;
            } else if (dif == MainActivity.Difficulty.MEDIUM) {
                value1 = rand.nextInt(20) + 1;
                value2 = rand.nextInt(10) + 1;
            } else {
                value1 = rand.nextInt(50) + 1;
                value2 = rand.nextInt(20) + 1;
            }
        } while (value1/value2 < 0 || !isEvenlyDivisible(value1, value2));
        answer = value1 / value2;
    }
}
     int getValue1()
     {
         return value1;
     }
     int getValue2()
     {
         return value2;
     }
     int getOperator()
     {
         return operator;
     }
     int getAnswer()
     {
         return answer;
     }
    private boolean isEvenlyDivisible(int a, int b) {

         return a % b == 0;
     }
}
