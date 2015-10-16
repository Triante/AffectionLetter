package jorgeandcompany.loveletter;


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
		deck.push(new CardOne());
		deck.push(new CardOne());
		deck.push(new CardOne());
		deck.push(new CardOne());
		deck.push(new CardOne());

		deck.push(new CardTwo());
		deck.push(new CardTwo());

		deck.push(new CardThree());
		deck.push(new CardThree());

		deck.push(new CardFour());
		deck.push(new CardFour());

		deck.push(new CardFive());
		deck.push(new CardFive());

		deck.push(new CardSix());

		deck.push(new CardSeven());

		deck.push(new CardEight());
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
