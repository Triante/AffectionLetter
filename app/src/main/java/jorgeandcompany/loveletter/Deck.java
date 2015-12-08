package jorgeandcompany.loveletter;


import java.util.Random;
import java.util.Stack;

public class Deck {

	private Stack deck;
	private int deckCount;

	public Deck() {
		deckCount = 16;
		createDeck();
	}

	/**
	 * Gets the top card of the deck
	 * @return a card drawn from the top of the deck.
	 */
	public Card draw() {
		deckCount--;
		return (Card) deck.pop();
	}

	/**
	 * Creates a new deck and shuffles it using Fisher Yates algorithm.
	 */
	private void createDeck() {
		CardFactory aCardFactory = new ConcreteCardFactory();
		deck = new Stack();
		int[] preDeck = {1,1,1,1,1,2,2,3,3,4,4,5,5,6,7,8};
		//int[] preDeck = {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6};
		//int[] preDeck = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
		shuffleArray(preDeck);
		shuffleArray(preDeck);
		shuffleArray(preDeck);
		for (int loc = 0; loc < preDeck.length; loc++) {
			Card toAdd =  aCardFactory.createCard(preDeck[loc]);
			deck.push(toAdd);
		}

	}

	/**
	 * Shuffles an array using Fisher Yates algorithm.
	 * @param ar array to be shuffled.
	 */
	private void shuffleArray(int[] ar) {
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

	/**
	 * Returns the amount of cards still left in the Deck
	 * @return the amount of cards left in the Deck
	 */
	public int getDeckCount() {
		return deckCount;
	}

}
