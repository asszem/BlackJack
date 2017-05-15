package main;

import java.util.Collections;
import java.util.Stack;

public class Deck {
	public static final int ACEINDEX=12; //The index of the "Ace" nam ein the rankNames array
	enum Suit {
		Treff, Kőr, Káró, Pikk
	}

	private static String[] rankNames = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jumbó", "Dáma", "Király",
			"Ász" };

	private Stack<Card> deckStack = new Stack<>();

	// Construct @param deckNumber unshuffled deck(s)
	public Deck(int deckNumber) {
		for (int i = 0; i < deckNumber; i++) {
			for (Suit suit : Suit.values()) {
				for (String rank : rankNames) {
					deckStack.push(new Card(suit.toString(), rank));
				}
			}
		}
	}

	public void shuffleDeck() {
		Collections.shuffle(deckStack);
	}

	public Stack<Card> getDeck() {
		return deckStack;
	}
	public static String[] getRankNames(){
		return rankNames;
	}
}
