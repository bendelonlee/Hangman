package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //declare variables

    TextView txtWordToBeGuessed;
    String wordToBeGuessed;
    String wordDisplayedString;
    char[] wordDisplayedCharArray;
    ArrayList<String> myListOfWords;
    EditText edtInput;
    TextView txtLettersTried;
    String lettersTried;
    final String MESSAGE_WITH_LETTERS_TRIED = "Letters tried: ";
    TextView txtTriesLeft;
    String triesLeft;
    final String WINNING_MESSAGE = "You won";
    final String LOSING_MESSAGE = "You lost";
    Animation rotateAnimation;
    Animation scaleAnimation;
    Animation scaleAndRotateAnimation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize variables

        myListOfWords = new ArrayList<String>();
        txtWordToBeGuessed = (TextView) findViewById(R.id.txtToBeGuessed);
        edtInput = (EditText) findViewById(R.id.edtInput);
        txtLettersTried = (TextView) findViewById(R.id.lettersTried);
        txtTriesLeft= (TextView) findViewById(R.id.txtTriesLeft);
    }
}