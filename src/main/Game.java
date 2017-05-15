package main;
/*
Requirement: For the adventurous gambler — 
* use a stack and a Random object in a program to simulate a game of Blackjack 
* for one player using two decks of cards.
* 
* Rules:
* Player Decide if Ace is 11 or 1
* Face cards (J,D,Q) value is 10 
* No betting
* Rules: http://www.bicyclecards.com/how-to-play/blackjack/ 
* Deal 1 card to the player, one card to the bank, both face up
* Deal 1 card to the player face up, one card to the bank face down
*	If player or dealer have blackjack, the game ends
*Player decides to Stand (not take another card) or Hit (take another card)
*If Hit, player gets another card. 
*If total is over 21, player loses
*If total is under 21, player decides to stand or hit
*
*if Stand, the dealer reveals his second card
*If it is over 17, dealer must stand. if its under 17, dealer must hit
*If the dealer has an ace, and counting it as 11 would bring his total to 17 or more
* (but not over 21), he must count the ace as 11 and stand.
*/

import java.util.Iterator;
import java.util.Stack;

public class Game {

	Player player;
	Player bank;
	Deck deck;
	int deckSize; //temporary variable for junit testing

	// Constructs a game object
	public Game() {
		this.deck = new Deck(2); //Note: this will be the point for dependency injection
		deck.shuffleDeck();
		this.bank = new Player("Bank");
		this.player = new Player("Player");
		// Deal 2 cards for Player
		dealCard(player, 2);
		// Deal 2 cards for Bank
		dealCard(bank, 2); 
		//Temporary solution: store the deck size at this point for validation
		deckSize=deck.getDeck().size();
		// Calculate hand value (method implemented in Player's class)
		// Display players cards and value
		// Display first card of Bank value
		// Get user input
		// If 'hit'
		// deal another card to user
		// and display it
		// calculate Players hand value
		// if burst finish game
	}

	public void dealCard(Player playerToDeal, int numberToDeal) {
		for (int i = 0; i < numberToDeal; i++) {
			// pop the card from the top of the deck and push it to the player's hand
//			playerToDeal.getPlayerHand().push(this.deck.getDeck().pop());
			playerToDeal.dealToPlayer(this.deck.getDeck().pop());
		}
	}

	
	
	
	public static boolean getKeyboardInput() {
		return false;
	}

	public int getValue() {
		return 0;
	}

	public static void main(String[] args) {
		Game aGameInstance = new Game();

		System.out.println("End.");
	}

}
