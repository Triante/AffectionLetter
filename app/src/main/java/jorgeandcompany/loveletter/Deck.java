package jorgeandcompany.loveletter;


import java.util.Random;
import java.util.Stack;

public class Deck {

	private Stack deck;
	private int deckCount;

	public Deck() {
		deckCount = 16;
		shuffle();
	}

	public Card draw() {
		deckCount--;
		return (Card) deck.pop();
	}

	private void shuffle() {
		CardFactory aCardFactory = new ConcreteCardFactory();
		deck = new Stack();
		int[] preDeck = {1,1,1,1,1,2,2,3,3,4,4,5,5,6,7,8};
		//int[] preDeck = {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6};
		//int[] preDeck = {5,5,5,5,5,5,5,5,5,5,5,5,5,8,8,8};
		shuffleArray(preDeck);
		shuffleArray(preDeck);
		shuffleArray(preDeck);
		for (int loc = 0; loc < preDeck.length; loc++) {
			Card toAdd =  aCardFactory.createCard(preDeck[loc]);
			deck.push(toAdd);
		}

	}

	private void shuffleArray(int[] ar)
	{
		long tsLong = (System.currentTimeMillis()/1000) + (System.currentTimeMillis()/777);
		tsLong = tsLong/2;
		Random rnd = new Random(tsLong);
		for (int i = ar.length - 1; i > 0; i--)
		{
			int index = rnd.nextInt(i + 1);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	public int getDeckCount() {
		return deckCount;
	}

}
