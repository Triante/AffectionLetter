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

        firstPlayerRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] cardcoordinates = new int[2];
                int[] deckcoordinates = new int[2];
                deck.getLocationOnScreen(deckcoordinates);
                v.getLocationOnScreen(cardcoordinates);
                Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
                translate.setDuration(1000);
                v.startAnimation(translate);

                new CountDownTimer(1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        firstPlayerRight.setVisibility(firstPlayerRight.INVISIBLE);
                        discard.setVisibility(View.VISIBLE);
                        firstPlayerRight.setClickable(false);
                        discard.setClickable(true);
                    }
                }.start();
            }

        });

        discard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Game.this, Game.class));
                finish();
            }
        });

        thirdPlayerLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] cardcoordinates = new int[2];
                int[] deckcoordinates = new int[2];
                deck.getLocationOnScreen(deckcoordinates);
                v.getLocationOnScreen(cardcoordinates);
                Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
                translate.setDuration(1000);
                v.startAnimation(translate);
                v.setVisibility(v.INVISIBLE);
            }
        });

        thirdPlayerRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] cardcoordinates = new int[2];
                int[] deckcoordinates = new int[2];
                deck.getLocationOnScreen(deckcoordinates);
                v.getLocationOnScreen(cardcoordinates);
                Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
                translate.setDuration(1000);
                v.startAnimation(translate);
                v.setVisibility(v.INVISIBLE);
            }
        });
        firstPlayerLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] cardcoordinates = new int[2];
                int[] deckcoordinates = new int[2];
                deck.getLocationOnScreen(deckcoordinates);
                v.getLocationOnScreen(cardcoordinates);
                Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
                translate.setDuration(1000);
                v.startAnimation(translate);
                v.setVisibility(v.INVISIBLE);
            }
        });

        fourthPlayerLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] cardcoordinates = new int[2];
                int[] deckcoordinates = new int[2];
                deck.getLocationOnScreen(deckcoordinates);
                v.getLocationOnScreen(cardcoordinates);
                Animation rotate = new RotateAnimation(0, 90, v.getPivotX(), v.getPivotY());
                rotate.setDuration(1000);
                Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
                translateleftrigt.setDuration(1000);
                AnimationSet rotateandmove = new AnimationSet(false);
                rotateandmove.addAnimation(rotate);
                rotateandmove.addAnimation(translateleftrigt);
                v.startAnimation(rotateandmove);
            }
        });

        fourthPlayerRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] cardcoordinates = new int[2];
                int[] deckcoordinates = new int[2];
                deck.getLocationOnScreen(deckcoordinates);
                v.getLocationOnScreen(cardcoordinates);
                Animation rotate = new RotateAnimation(0, 90, v.getPivotX(), v.getPivotY());
                rotate.setDuration(1000);
                Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
                translateleftrigt.setDuration(1000);
                AnimationSet rotateandmove = new AnimationSet(false);
                rotateandmove.addAnimation(rotate);
                rotateandmove.addAnimation(translateleftrigt);
                v.startAnimation(rotateandmove);
            }
        });

        secondPlayerRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] cardcoordinates = new int[2];
                int[] deckcoordinates = new int[2];
                deck.getLocationOnScreen(deckcoordinates);
                v.getLocationOnScreen(cardcoordinates);
                Animation rotate = new RotateAnimation(0, -90, v.getPivotX(), v.getPivotY());
                rotate.setDuration(1000);
                Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
                translateleftrigt.setDuration(1000);
                AnimationSet rotateandmove = new AnimationSet(false);
                rotateandmove.addAnimation(rotate);
                rotateandmove.addAnimation(translateleftrigt);
                v.startAnimation(rotateandmove);
            }
        });

        secondPlayerLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] cardcoordinates = new int[2];
                int[] deckcoordinates = new int[2];
                deck.getLocationOnScreen(deckcoordinates);
                v.getLocationOnScreen(cardcoordinates);
                Animation rotate = new RotateAnimation(0, -90, v.getPivotX(), v.getPivotY());
                rotate.setDuration(1000);
                Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
                translateleftrigt.setDuration(1000);
                AnimationSet rotateandmove = new AnimationSet(false);
                rotateandmove.addAnimation(rotate);
                rotateandmove.addAnimation(translateleftrigt);
                v.startAnimation(rotateandmove);
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
                else {
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
    }

}
