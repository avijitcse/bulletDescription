package com.example.l8770068.myapplication;
import android.app.Activity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View.OnKeyListener;
import android.view.View;
import android.view.KeyEvent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View.OnKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText detailDescTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        detailDescTxt = (EditText) findViewById(R.id.detailDescTxt);
        Log.v(getPackageName(), "View Loaded");

        detailDescTxt.setImeOptions(EditorInfo.IME_ACTION_DONE);
        detailDescTxt.setRawInputType(InputType.TYPE_CLASS_TEXT);

        detailDescTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    //do what you want on the press of 'done'
                    Toast.makeText(MainActivity.this, "Enter Pressed", Toast.LENGTH_LONG);
                    Log.v(getPackageName(), "Enter Pressed");
                    int cursorPosition = detailDescTxt.getSelectionStart();

                    detailDescTxt.setText(detailDescTxt.getText().insert(cursorPosition, "\n• "));
                    detailDescTxt.setSelection(cursorPosition+3);

                }
                return true;
            }
        });

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        Log.v("bulletDescription---", formattedDate);

//        detailDescTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE) {
//                    //some method
//                    Toast.makeText(getApplicationContext(), "Enter pressed", Toast.LENGTH_SHORT);
//                    return true;
//                }
//                return false;
//            }
//        });

        /* Set Text Watcher listener */
        //detailDescTxt.addTextChangedListener(passwordWatcher);
    }

    public void checkCharLimit(View view) {
        detailDescTxt = (EditText) findViewById(R.id.detailDescTxt);
        int actLength = detailDescTxt.getText().length();
        int lengthAfter = detailDescTxt.getText().toString().replaceAll("[•]+ ","").length();
        Log.v("Actual length",""+ actLength);
        Log.v("Length After",""+ lengthAfter);
    }

    private final TextWatcher passwordWatcher = new TextWatcher() {
        private String lastValue = "";
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //detailDescTxt.setVisibility(View.VISIBLE);

            Log.v("You have entered onTextChanged:", s.toString());

        }

        public void afterTextChanged(Editable s) {
            String newValue = s.toString();
            try{
                s.toString().replaceAll("\n", "\n*");
                detailDescTxt.setSelection(detailDescTxt.length());
                //When user enters "*" will be added
                //if (s.toString().endsWith("\n") && lastValue.length()< newValue.length()) {
                    //s.append("*");
                    //detailDescTxt.setText(s.toString().replaceAll("\n","\n*"));
                    //
                    // detailDescTxt.setText(s.toString()+"*");
               // }
//                else if(s.toString().endsWith("*") && lastValue.length()> newValue.length()){
//                    detailDescTxt.setText(detailDescTxt.getText().toString().substring(0, s.length()-1));
//                }

            } catch (Exception e){
                Log.v("Exception---", "failed---");

            } finally {
                if (!newValue.equals(lastValue)) {
                    lastValue = newValue;
                }
            }
//            if (!s.toString().endsWith("\n") && s.length()== 1) {
//                s.insert(0,"*");
//            }
        }
    };
}
