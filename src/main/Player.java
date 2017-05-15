package main;

import java.util.Stack;

public class Player {

	private String playerName;
	private Stack<Card> playerHand;
	private int totalHandValueAceIsEleven;
	private int totalHandValueAceIsOne;

	public Player() {
		this.playerHand = new Stack<Card>(); // create an empty hand
		this.totalHandValueAceIsEleven = 0;
		this.totalHandValueAceIsOne = 0;
	}

	public Player(String name) {
		this(); // call no-arg constructor
		this.playerName = name;
	}

	public void dealToPlayer(Card cardToDeal) {
		// Get the rank of the card
		String rank = cardToDeal.getRank();
		// Match the rank string with the ranknames string to get it's index
		// Sort the rankNames for Binary Search
		java.util.Arrays.sort(Deck.getRankNames());
		// Search for the index corresponding the current rank
		int rankIndex = java.util.Arrays.binarySearch(Deck.getRankNames(), rank);

		// Index 0-7 -> numeric values of 2,3,4,5,6,7,8,9
		if (rankIndex < 8) {
			totalHandValueAceIsEleven += rankIndex + 2; // Because this is the value of the card
			totalHandValueAceIsOne += rankIndex + 2; // Because this is the value of the card
		}

		// Index 8 - 9-10-11 -> numeric values of 10, jumbo, dáma, király
		else if (rankIndex < 11) {
			totalHandValueAceIsEleven += 10; // Because this is the value of the card
			totalHandValueAceIsOne += 10; // Because this is the value of the card
		}
		// Index 12 -> numberic value of Ace, which is either 1 or 11
		else {
			totalHandValueAceIsEleven += 11; // Because this is the value of the card
			totalHandValueAceIsOne += 1; // Because this is the value of the card
		}
		// Push the card to the player's hand
		playerHand.push(cardToDeal);
	}

	public Stack<Card> getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(Stack<Card> playerHand) {
		this.playerHand = playerHand;
	}

	public int[] getTotalHandValues() {
		int[] returnInt = {totalHandValueAceIsEleven, totalHandValueAceIsOne};
		return returnInt;
	}

	/**
	 * totalHandValue[0] = Ace is Eleven
	 * totalHandValue[1] = Ace is One 
	 * */
	public void setTotalHandValues(int[] totalHandValue) {
		this.totalHandValueAceIsEleven = totalHandValue[0];
		this.totalHandValueAceIsOne = totalHandValue[1];
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}
