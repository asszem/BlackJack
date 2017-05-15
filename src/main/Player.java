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

	/**
	 * Increments the player's hand value with the value of the card dealt and pushes the card to the player's hand 
	 * */
	public void dealToPlayer(Card cardToDeal) {
		//Get the card's value
		int[] cardValue=Game.calculateCardValue(cardToDeal);
		//Increment the player's total hand accordingly
		totalHandValueAceIsEleven+=cardValue[0];
		totalHandValueAceIsOne+=cardValue[1];
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
