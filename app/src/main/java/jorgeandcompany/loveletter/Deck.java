package jorgeandcompany.loveletter;


import java.util.Random;

public class Deck {

	private Stack deck;

	public Deck() {
		shuffle();
	}

	public Card draw() {
		return deck.pop();
	}

	private void shuffle() {
		deck = new Stack();
		int[] preDeck = {1,1,1,1,1,2,2,3,3,4,4,5,5,6,7,8};
		shuffleArray(preDeck);
		shuffleArray(preDeck);
		shuffleArray(preDeck);
		for (int loc = 0; loc < preDeck.length; loc++) {
			Card toAdd =  CardFactory.createCard(preDeck[loc]);
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


	class Stack {
		Node top;
		public void push(Card c) {
			if (top.equals(null)) {
				Node n = new Node();
				n.card = c;
				top = n;
			}
			else {
				Node n = new Node();
				n.card = c;
				n.next = top;
				top = n;
			}
		}
		public Card pop(){
			Card c = top.card;
			top = top.next;
			return c;
		}
	}

	class Node {
		Card card;
		Node next;
	}
}
