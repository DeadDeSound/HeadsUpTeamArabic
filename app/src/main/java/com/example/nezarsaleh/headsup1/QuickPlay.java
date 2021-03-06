package com.example.nezarsaleh.headsup1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class QuickPlay extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    Button correct, pass, Back;
    TextView result, introText;

    int CatID;

    int correctScore = 0;
    int passScore = 0;

    String lastText;

    ArrayList<String> HELLOS = new ArrayList<>();

    RelativeLayout main_relative, intro_relative;
    Boolean result_shown = false;
    TextView timerTextView;

    private class counterDown extends CountDownTimer {

        public counterDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String hms = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) );
            timerTextView.setText(hms);
        }

        @Override
        public void onFinish() {
            intro_relative.setVisibility(View.VISIBLE);
            introText.setText("Time Out !! \n Correct :"+correctScore+"\n Pass :"+passScore+"\n Tap To Play Again");
//            SharedPreferences myPrefs = getApplicationContext().getSharedPreferences("myPrefs", 0);
//            SharedPreferences.Editor editor = myPrefs.edit();
//            int Coins = myPrefs.getInt("Coins",-1);
//            if (Coins != -1) {
//                editor.putInt("Coins", Coins + 10);
//                editor.apply();
//            }
            intro_relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);

        Intent in = getIntent();
        CatID = in.getIntExtra("CatID", 0);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        if (CatID != 0) {
            Cursor res = databaseHelper.getItemsByCat(CatID);
            if (res.getCount() != -1) {
                while (res.moveToNext()) {
                    HELLOS.add(res.getString(3));
                }
            }
        }
        correct = (Button) findViewById(R.id.correct_btn);
        pass = (Button) findViewById(R.id.pass_btn);
        Back = (Button) findViewById(R.id.btnBack);
        result = (TextView) findViewById(R.id.play_text);
        main_relative = (RelativeLayout) findViewById(R.id.qplay_relative);
        intro_relative = (RelativeLayout) findViewById(R.id.intro);
        timerTextView = (TextView) findViewById(R.id.timer_tv);
        introText = (TextView) findViewById(R.id.intro_text);
        main_relative.setBackgroundResource(android.R.color.holo_blue_light);

        result.setVisibility(View.INVISIBLE);
        introText.setText("Tap When You Ready To Play !!");

//        timerTextView.setText("00:03:00");

        final SharedPreferences pref = getSharedPreferences("myPrefs", 0);


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        intro_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HELLOS.size() != 0) {
                    int Time = pref.getInt("Time",60000);
                    intro_relative.setVisibility(View.INVISIBLE);
                    result.setVisibility(View.VISIBLE);
                    result.setText(HELLOS.get(RANDOM.nextInt(HELLOS.size())));
                    final counterDown timer = new counterDown(Time, 1000);
                    timer.start();
                }else {
                    introText.setText("NO ENTITIES AVAILABLE !!");
                }
            }
        });
//        startTime = System.currentTimeMillis();
//        timerHandler.postDelayed(timerRunnable, 0);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(5000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (HELLOS.size() != 0) {
                                    result.setVisibility(View.VISIBLE);
                                    result.setText(sayHello());
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setVisibility(View.VISIBLE);
                result.setText("Correct");
                correctScore = correctScore + 1;
                main_relative.setBackgroundResource(android.R.color.holo_green_light);
                if (!result_shown) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            result_shown = true;
                            try {
                                synchronized (this) {
                                    wait(1000);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.setText(sayHello());
//                                            startTime = System.currentTimeMillis();
//                                            timerHandler.postDelayed(timerRunnable, 0);
                                        }
                                    });
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                }
            }
        });

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setVisibility(View.VISIBLE);
                result.setText("Pass");
                passScore = passScore + 1;
                main_relative.setBackgroundResource(android.R.color.holo_red_light);
                if (!result_shown) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            result_shown = true;
                            try {
                                synchronized (this) {
                                    wait(1000);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.setText(sayHello());
                                        }
                                    });
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                }
            }
        });
    }


    private String sayHello() {
        // Select a random hello.
        int helloLength = HELLOS.size();
        main_relative.setBackgroundResource(android.R.color.holo_blue_light);
        result_shown = false;
        String nextText = HELLOS.get(RANDOM.nextInt(helloLength));
        if (HELLOS.size() != 1) {
            while (nextText.equals(lastText)) {
                nextText = HELLOS.get(RANDOM.nextInt(helloLength));
            }
            lastText = nextText;
        }
        if (lastText == null){
            lastText = nextText;
        }
        return nextText;
    }

}

