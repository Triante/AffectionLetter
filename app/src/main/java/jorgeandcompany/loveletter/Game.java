package jorgeandcompany.loveletter;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class Game extends ActionBarActivity {
    public static int deckcount = 16;
    private ImageButton discard, deck, firstPlayerRight, firstPlayerLeft, secondPlayerRight,
            secondPlayerLeft, thirdPlayerRight, thirdPlayerLeft, fourthPlayerRight, fourthPlayerLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        discard = (ImageButton) findViewById(R.id.discard);
        deck = (ImageButton) findViewById(R.id.deck);
        thirdPlayerLeft = (ImageButton) findViewById(R.id.player3left);
        thirdPlayerRight = (ImageButton) findViewById(R.id.player3right);
        discard.setVisibility(View.INVISIBLE);
        firstPlayerRight = (ImageButton) findViewById(R.id.player1right);
        secondPlayerRight = (ImageButton) findViewById(R.id.player2right);
        secondPlayerLeft = (ImageButton) findViewById(R.id.player2left);
        firstPlayerLeft = (ImageButton) findViewById(R.id.player1left);
        fourthPlayerLeft = (ImageButton) findViewById(R.id.player4left);
        fourthPlayerRight = (ImageButton) findViewById(R.id.player4right);
        final ImageButton deckDummy = (ImageButton) findViewById(R.id.deckDummy);

        discard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Game.this, Game.class));
                finish();
            }
        });

        firstPlayerRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                firstRightToDeck();
                new CountDownTimer(1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {

                        discard.setVisibility(View.VISIBLE);

                        discard.setClickable(true);
                    }
                }.start();
            }

        });

        firstPlayerLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                firstLeftToDeck();
            }
        });

        secondPlayerRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondRightToDeck();
            }
        });

        secondPlayerLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondLeftToDeck();
            }
        });

        thirdPlayerLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                thirdLeftToDeck();
            }
        });

        thirdPlayerRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                thirdRightToDeck();
            }
        });

        fourthPlayerLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fourthLeftToDeck();
            }
        });

        fourthPlayerRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fourthRightToDeck();
            }
        });



        new CountDownTimer(12000, 1000) {
            int a = 0;
            public void onTick(long millisUntilFinished) {
                if (a == 1) {
                    a++;
                    deckToFirstLeft();
                }
                else if (a == 2) {
                    a++;
                    deckToFirstRight();
                }
                else if (a == 3) {
                    a++;
                    deckToSecondLeft();
                }
                else if (a == 4) {
                    a++;
                    deckToSecondRight();
                }
                else if (a == 5) {
                    a++;
                    deckToThirdLeft();
                }
                else if (a == 6) {
                    a++;
                    deckToThirdRight();
                }
                else if (a == 7) {
                    a++;
                    deckToFourthLeft();
                }
                else if (a == 8) {
                    a++;
                    deckToFourthRight();
                }
                else if (a == 0) {
                    a = 1;
                }

            }

            public void onFinish() {}
        }.start();
    }


    private void deckToFirstRight() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        Animation translate;
        AnimationSet set;
        firstPlayerRight.getLocationOnScreen(cardcoordinates);
        deck.getLocationOnScreen(deckcoordinates);
        translate = new TranslateAnimation(0, cardcoordinates[0] - deckcoordinates[0], 0, cardcoordinates[1] - deckcoordinates[1]);
        translate.setDuration(1000);
        set = new AnimationSet (true);
        set.addAnimation(translate);
        deck.startAnimation(set);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                firstPlayerRight.setVisibility(firstPlayerRight.VISIBLE);
                firstPlayerRight.setClickable(true);
            }
        }.start();

    }
    private void deckToSecondRight() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        Animation translate;
        Animation rotate;
        AnimationSet set;
        secondPlayerRight.getLocationOnScreen(cardcoordinates);
        deck.getLocationOnScreen(deckcoordinates);
        translate = new TranslateAnimation(0, cardcoordinates[0] - deckcoordinates[0], 0, cardcoordinates[1] - deckcoordinates[1]);
        rotate = new RotateAnimation(0, -90, deck.getPivotX(), deck.getPivotY());
        translate.setDuration(1000);
        rotate.setDuration(1000);
        set = new AnimationSet (true);
        set.addAnimation(rotate);
        set.addAnimation(translate);
        deck.startAnimation(set);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                secondPlayerRight.setVisibility(secondPlayerRight.VISIBLE);
                secondPlayerRight.setClickable(true);
            }
        }.start();


    }
    private void deckToThirdRight() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        Animation translate;
        Animation rotate;
        AnimationSet set;
        thirdPlayerRight.getLocationOnScreen(cardcoordinates);
        deck.getLocationOnScreen(deckcoordinates);
        translate = new TranslateAnimation(0, cardcoordinates[0] - deckcoordinates[0], 0, cardcoordinates[1] - deckcoordinates[1]);
        rotate = new RotateAnimation(0, 180, deck.getPivotX(), deck.getPivotY());
        translate.setDuration(1000);
        rotate.setDuration(1000);
        set = new AnimationSet (true);
        set.addAnimation(rotate);
        set.addAnimation(translate);
        deck.startAnimation(set);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                thirdPlayerRight.setVisibility(thirdPlayerRight.VISIBLE);
                thirdPlayerRight.setClickable(true);
            }
        }.start();


    }
    private void deckToFourthRight() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        Animation translate;
        Animation rotate;
        AnimationSet set;
        fourthPlayerRight.getLocationOnScreen(cardcoordinates);
        deck.getLocationOnScreen(deckcoordinates);
        translate = new TranslateAnimation(0, cardcoordinates[0] - deckcoordinates[0], 0, cardcoordinates[1] - deckcoordinates[1]);
        rotate = new RotateAnimation(0, 90, deck.getPivotX(), deck.getPivotY());
        translate.setDuration(1000);
        rotate.setDuration(1000);
        set = new AnimationSet (true);
        set.addAnimation(rotate);
        set.addAnimation(translate);
        deck.startAnimation(set);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                fourthPlayerRight.setVisibility(fourthPlayerRight.VISIBLE);
                fourthPlayerRight.setClickable(true);
            }
        }.start();

    }

    private void deckToFirstLeft() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        Animation translate;
        AnimationSet set;
        firstPlayerLeft.getLocationOnScreen(cardcoordinates);
        deck.getLocationOnScreen(deckcoordinates);
        translate = new TranslateAnimation(0, cardcoordinates[0] - deckcoordinates[0], 0, cardcoordinates[1] - deckcoordinates[1]);
        translate.setDuration(1000);
        set = new AnimationSet (true);
        set.addAnimation(translate);
        deck.startAnimation(set);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                firstPlayerLeft.setVisibility(firstPlayerLeft.VISIBLE);
                firstPlayerLeft.setClickable(true);
            }
        }.start();

    }
    private void deckToSecondLeft() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        Animation translate;
        Animation rotate;
        AnimationSet set;
        secondPlayerLeft.getLocationOnScreen(cardcoordinates);
        deck.getLocationOnScreen(deckcoordinates);
        translate = new TranslateAnimation(0, cardcoordinates[0] - deckcoordinates[0], 0, cardcoordinates[1] - deckcoordinates[1]);
        rotate = new RotateAnimation(0, -90, deck.getPivotX(), deck.getPivotY());
        translate.setDuration(1000);
        rotate.setDuration(1000);
        set = new AnimationSet (true);
        set.addAnimation(rotate);
        set.addAnimation(translate);
        deck.startAnimation(set);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                secondPlayerLeft.setVisibility(secondPlayerLeft.VISIBLE);
                secondPlayerLeft.setClickable(true);
            }
        }.start();


    }
    private void deckToThirdLeft() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        Animation translate;
        Animation rotate;
        AnimationSet set;
        thirdPlayerLeft.getLocationOnScreen(cardcoordinates);
        deck.getLocationOnScreen(deckcoordinates);
        translate = new TranslateAnimation(0, cardcoordinates[0] - deckcoordinates[0], 0, cardcoordinates[1] - deckcoordinates[1]);
        rotate = new RotateAnimation(0, 180, deck.getPivotX(), deck.getPivotY());
        translate.setDuration(1000);
        rotate.setDuration(1000);
        set = new AnimationSet (true);
        set.addAnimation(rotate);
        set.addAnimation(translate);
        deck.startAnimation(set);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                thirdPlayerLeft.setVisibility(thirdPlayerLeft.VISIBLE);
                thirdPlayerLeft.setClickable(true);
            }
        }.start();


    }
    private void deckToFourthLeft() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        Animation translate;
        Animation rotate;
        AnimationSet set;
        fourthPlayerLeft.getLocationOnScreen(cardcoordinates);
        deck.getLocationOnScreen(deckcoordinates);
        translate = new TranslateAnimation(0, cardcoordinates[0] - deckcoordinates[0], 0, cardcoordinates[1] - deckcoordinates[1]);
        rotate = new RotateAnimation(0, 90, deck.getPivotX(), deck.getPivotY());
        translate.setDuration(1000);
        rotate.setDuration(1000);
        set = new AnimationSet (true);
        set.addAnimation(rotate);
        set.addAnimation(translate);
        deck.startAnimation(set);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                fourthPlayerLeft.setVisibility(fourthPlayerLeft.VISIBLE);
                firstPlayerRight.setClickable(true);
            }
        }.start();

    }

    private void firstRightToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        firstPlayerRight.getLocationOnScreen(cardcoordinates);
        Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translate.setDuration(1000);
        firstPlayerRight.startAnimation(translate);
        firstPlayerRight.setVisibility(firstPlayerRight.INVISIBLE);
        firstPlayerRight.setClickable(false);
    };
    private void secondRightToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        secondPlayerRight.getLocationOnScreen(cardcoordinates);
        Animation rotate = new RotateAnimation(0, -90, secondPlayerRight.getPivotX(), secondPlayerRight.getPivotY());
        rotate.setDuration(1000);
        Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        secondPlayerRight.startAnimation(rotateandmove);
        secondPlayerRight.setVisibility(secondPlayerRight.INVISIBLE);
        secondPlayerRight.setClickable(false);
    };
    private void thirdRightToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        thirdPlayerRight.getLocationOnScreen(cardcoordinates);
        Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translate.setDuration(1000);
        thirdPlayerRight.startAnimation(translate);
        thirdPlayerRight.setVisibility(thirdPlayerRight.INVISIBLE);
        thirdPlayerRight.setClickable(false);
    };
    private void fourthRightToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        fourthPlayerRight.getLocationOnScreen(cardcoordinates);
        Animation rotate = new RotateAnimation(0, 90, fourthPlayerRight.getPivotX(), fourthPlayerRight.getPivotY());
        rotate.setDuration(1000);
        Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        fourthPlayerRight.startAnimation(rotateandmove);
        fourthPlayerRight.setVisibility(fourthPlayerRight.INVISIBLE);
        fourthPlayerRight.setClickable(false);
    };

    private void firstLeftToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        firstPlayerLeft.getLocationOnScreen(cardcoordinates);
        Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translate.setDuration(1000);
        firstPlayerLeft.startAnimation(translate);
        firstPlayerLeft.setVisibility(firstPlayerLeft.INVISIBLE);
        firstPlayerLeft.setClickable(false);
    };
    private void secondLeftToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        secondPlayerLeft.getLocationOnScreen(cardcoordinates);
        Animation rotate = new RotateAnimation(0, -90, secondPlayerLeft.getPivotX(), secondPlayerLeft.getPivotY());
        rotate.setDuration(1000);
        Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        secondPlayerLeft.startAnimation(rotateandmove);
        secondPlayerLeft.setVisibility(secondPlayerLeft.INVISIBLE);
        secondPlayerLeft.setClickable(false);
    };
    private void thirdLeftToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        thirdPlayerLeft.getLocationOnScreen(cardcoordinates);
        Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translate.setDuration(1000);
        thirdPlayerLeft.startAnimation(translate);
        thirdPlayerLeft.setVisibility(thirdPlayerLeft.INVISIBLE);
        thirdPlayerLeft.setClickable(false);
    };
    private void fourthLeftToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        fourthPlayerLeft.getLocationOnScreen(cardcoordinates);
        Animation rotate = new RotateAnimation(0, 90, fourthPlayerLeft.getPivotX(), fourthPlayerLeft.getPivotY());
        rotate.setDuration(1000);
        Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        fourthPlayerLeft.startAnimation(rotateandmove);
        fourthPlayerLeft.setVisibility(fourthPlayerLeft.INVISIBLE);
        firstPlayerRight.setClickable(false);
    };
}
