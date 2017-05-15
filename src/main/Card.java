package main;

public class Card {

	private String suit; // Treff, Kőr, Ász, Pikk
	private String rank; // 23456789 10, J, D, K, Á
	private int value; // Value. 2-10, 1 or 11 (based on player's decision on ace)

	// Constructor to create a new card
	public Card(String suit, String rank) {
			this.suit = suit;
			this.rank = rank;
		}

	public String getSuit() {
		return suit;
	}

	public String getRank() {
		return rank;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int newValue) {
		this.value = newValue;
	}

	public String toString(){
		return suit+" "+rank;
	}
}
