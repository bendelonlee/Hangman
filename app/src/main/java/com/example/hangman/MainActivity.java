package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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

    void initializeGame(){
        //1.Word
        //Shuffle array list and get first element, then remove it
        Collections.shuffle(myListOfWords);
        wordToBeGuessed = myListOfWords.get(0);
        myListOfWords.remove(0);
        //initialize char array
        wordDisplayedCharArray = wordToBeGuessed.toCharArray();
        //add underscores
        for(int i = 1; i < wordDisplayedCharArray.length - 1; i++){
            wordDisplayedCharArray[i] = '_';

        }

        //reveal all occurences of first character
        revealLetterInWord(wordDisplayedCharArray[0]);

        //reveal all occurrences of last character
        revealLetterInWord(wordDisplayedCharArray[wordDisplayedCharArray.length - 1]);

        // initialize a string from this char array
        wordDisplayedCharString = String.valueOf(wordDisplayedCharArray);

        //display word
        displayWordOnScreen();

        //2. INPUT
        //clear input field
        edtInput.setText("");

        //3. Letters tried
        //initialize string for letters tried with a space
        lettersTried = "";

        //display on screen
        txtLettersTried.setText(MESSAGE_WITH_LETTERS_TRIED);

        //4.Tries Left
        //initialize the string with tries left
        triesLeft = " X X X X X";
        txtTriesLeft.setText(triesLeft);

    }

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

        //traverse databse file and populate array list;

        InputStream myInputStream = null;
        Scanner in = null;
        String aWord = "";
        try {
            myInputStream = getAssets().open("database_file.txt");
            in = new Scanner(myInputStream);
            while(in.hasNext()){
                aWord = in.next();
                myListOfWords.add(aWord);
            }
        } catch(IOException e){
            Toast.makeText(MainActivity.this,
                    e.getClass().getSimpleName() + ": " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
        finally {
            //close scanner
            if (in != null) {
                in.close();
            }
            try {
                if (myInputStream != null) {
                    myInputStream.close();
                }
            } catch (IOException e) {
                Toast.makeText(MainActivity.this,
                        e.getClass().getSimpleName() + ": " + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }

        initializeGame();
    }
}