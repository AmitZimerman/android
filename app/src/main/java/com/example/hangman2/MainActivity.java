package com.example.hangman2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    ImageView iv; LinearLayout main;int n=0,lengh=0;
    EditText ans;TextView words,error;
    String txt="",input,showtext="",firsttxt,wordtoguess,showWrong="",empty="",help="";
    ArrayList<String> wordbank=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        wordbank.add("sodot");
        wordbank.add("sodi");
        wordbank.add("raz");
        wordbank.add("secret");
        wordbank.add("sod");
        wordbank.add("hasod");
        wordbank.add("shel");
        wordbank.add("amit");
        System.out.println("Original List : \n" + wordbank);


        Collections.shuffle(wordbank);


        System.out.println("\nShuffled List : \n" + wordbank);
        wordtoguess=wordbank.get(0);
        for (int i=0;i<wordbank.get(0).length();i++)
        {
            empty=empty+"-";
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=new ImageView(this);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(100,100);
        iv.setLayoutParams(layoutParams);
        main= findViewById(R.id.l1);
        main.addView(iv);
        Loadpics1(n);
        ans=findViewById(R.id.ans);
        words=findViewById(R.id.words);
        error=findViewById(R.id.wrong);
        ans.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                input=ans.getText().toString();
                firsttxt=txt;
                txt=txt+input;
                if(n<6&&!CorrectLetter(txt)){

                    n++;
                    int imageKey=getResources().getIdentifier("hangman"+n,"drawable",getPackageName());
                    iv.setImageResource(imageKey);
                    txt=firsttxt;
                    showWrong+=input+", ";
                    error.setText(showWrong);
                    ans.getText().clear();

                }
                if(CorrectLetter(txt)){
                    showtext+=input;
                    words.setText(Print(wordtoguess,showtext.charAt(showtext.length()-1),empty));
                    empty=Print(wordtoguess,showtext.charAt(showtext.length()-1),empty);
                    if(empty.equals(wordtoguess)){
                        words.setText(empty+"  |Good Job!");
                        n=7;
                    }
                    ans.getText().clear();
                }


            }
        });

    }
    public boolean CorrectLetter(String Letters){
        String answer=wordtoguess;
        if(Letters.length()>0) {
            for (int i = 0; i < wordtoguess.length(); i++) {
                if (answer.charAt(i) == Letters.charAt(Letters.length()-1))
                    return true;

            }
            return false;
        }
        return false;
    }


    public String Print(String a,char b,String c){
        for (int i = 0; i < a.length(); i++) {
            if(b==a.charAt(i)){
                c=c.substring(0,i)+a.charAt(i)+c.substring(i+1);
            }
        }
        return c;
    }

    public void Loadpics1(int n){

        iv=new ImageView(this);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(1000,1000);
        layoutParams.setMargins(20,20,20,20);
        iv.setLayoutParams(layoutParams);
        int imageKey=getResources().getIdentifier("hangman"+n,"drawable",getPackageName());
        iv.setImageResource(imageKey);
        main.addView(iv);

    }

}