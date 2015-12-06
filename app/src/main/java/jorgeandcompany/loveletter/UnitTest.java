package jorgeandcompany.loveletter;

//import static org.junit.Assert.*;
import android.content.Intent;
import android.widget.ImageButton;
import junit.framework.TestCase;

import java.util.Arrays;

import jorgeandcompany.loveletter.Card;
import jorgeandcompany.loveletter.Deck;
import jorgeandcompany.loveletter.DiscardPile;
import jorgeandcompany.loveletter.Game;
import jorgeandcompany.loveletter.GameAnimation;
import jorgeandcompany.loveletter.GameData;
import jorgeandcompany.loveletter.MainMenu;

/**
 * Created by yoho on 12/4/2015.
 */
public class UnitTest extends TestCase{

        @Override
        protected void setUp() throws Exception{
            super.setUp();
        }
        public void testDeckPreset() throws Exception {
            final int numberOfOne = 5;
            final int numberOfTwo = 2;
            final int numberOfThree = 2;
            final int numberOfFour = 2;
            final int numberOfFive = 2;
            final int numberOfSix = 1;
            final int numberOfSeven = 1;
            final int numberOfEight = 1;
            final int expectedTotal = 16;
            int realityOne = 0, realityTwo = 0, realityThree = 0, realityFour = 0,
                    realityFive = 0, realitySix = 0, realitySeven = 0, realityEight = 0;
            Deck deck = new Deck();
            for (int i = 0; i < expectedTotal; i++) {
                Card drawCard = deck.draw();
                switch (drawCard.getValue()){
                    case 1:realityOne++;  break;
                    case 2:realityTwo++;  break;
                    case 3:realityThree++;  break;
                    case 4:realityFour++;  break;
                    case 5:realityFive++;  break;
                    case 6:realitySix++;  break;
                    case 7:realitySeven++;  break;
                    case 8:realityEight++;  break;
                }
            }
            assertEquals(numberOfOne, realityOne);
            assertEquals(numberOfTwo, realityTwo);
            assertEquals(numberOfThree, realityThree);
            assertEquals(numberOfFour, realityFour);
            assertEquals(numberOfFive, realityFive);
            assertEquals(numberOfSix, realitySix);
            assertEquals(numberOfSeven, realitySeven);
            assertEquals(numberOfEight, realityEight);
        }

   /*public void testAnimation() throws Exception {
        Game game = new Game();
        GameAnimation ga = game.provideAnimations();
        ImageButton deck = GameData.game.getButton("deck");
        ImageButton discard = GameData.game.getButton("discard");
        ImageButton outCard = GameData.game.getButton("outcard");
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        outCard.getLocationOnScreen(cardcoordinates);
        deck.getLocationOnScreen(deckcoordinates);
        Integer [] expected = new Integer[2];
        expected[0] = cardcoordinates[0];
        expected[1] = cardcoordinates[1];
        Integer [] reality = new Integer[2];
        reality[0] = cardcoordinates[0] - deckcoordinates[0];
        reality[1] = cardcoordinates[1] - deckcoordinates[1];
        int[] a = {1,1};
        int[] b = {1,1};
        assertTrue(Arrays.equals(a, b));
        //assertArrayEquals(a, b);
    }*/


    @Override
        protected void tearDown()throws Exception{
            super.tearDown();
        }
    }

