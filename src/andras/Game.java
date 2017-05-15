package andras;
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
import java.util.Scanner;
import java.util.Stack;

public class Game {

	Player player;
	Player bank;
	Deck deck;
	int deckSize; // temporary variable for junit testing
	Scanner keyboard = new Scanner(System.in);

	// Constructs a game object
	public Game() {
		this.deck = new Deck(2); // Note: this will be the point for dependency injection
		deck.shuffleDeck();
		this.bank = new Player("Bank");
		this.player = new Player("Player");
		// Deal 2 cards for Player && calculate hand value
		dealCard(player, 2);
		// Deal 2 cards for Bank
		dealCard(bank, 2);
		// Temporary solution: store the deck size at this point for validation
		deckSize = deck.getDeck().size();
		// Display players cards and value
		displayPlayerHand(this.player, 2);
		// Display first card of Bank value
		displayPlayerHand(this.bank, 1);
		// Calculate player status
		displayIsBusted(player);
		displayIsBusted(bank);
		// Run the Game of Player if not busted already
		if (!isBusted(player)) {
			gameOfPlayer();
		}
		// Run the Game of Bank only if player & bank is not busted
		System.out.println("Revealing Bank's hand");
		displayPlayerHand(bank, 2);
		if (!isBusted(bank) && !isBusted(player)) {
			gameOfBank();
		}
		// At this point, evaulate result
		evaulateResult();
	}

	public void evaulateResult() {

		if (isBusted(bank) & !isBusted(player)) {
			announceWinner(player);
		} else if (isBusted(player)) {
			announceWinner(bank);
		} else if (!isBusted(player) & !isBusted(bank)) {
			int[] playerHand = player.getTotalHandValues();
			// If player is not busted, any of its hand must be under 21
			int playerBestHand = playerHand[0] <= 21 ? playerHand[0] : playerHand[1];
			int[] bankHand = bank.getTotalHandValues();
			// If Bank is not busted, any of its hand must be under 21
			int bankBestHand = bankHand[0] <= 21 ? bankHand[0] : bankHand[1];
			System.out.println(player.getPlayerName() + " hand: " + playerBestHand);
			System.out.println(bank.getPlayerName() + " hand: " + bankBestHand);
			if (playerBestHand > bankBestHand) {
				announceWinner(player);
			} else if (playerBestHand == bankBestHand) {
				announceDraw();
			} else if (playerBestHand < bankBestHand) {
				announceWinner(bank);
			}
		}
	}

	public void announceWinner(Player player) {
		System.out.println("The winner is: " + player.getPlayerName());
	}

	public void announceDraw() {
		System.out.println("It's a draw!");
	}

	public void gameOfPlayer() {

		while (!isBusted(player)) {
			System.out.print("<H>it or <S>tand?");
			boolean doesUserHit = doesUserHit();
			if (doesUserHit) {
				dealCard(player, 1);
				displayPlayerHand(player, player.getPlayerHand().size()); // display all
				displayIsBusted(player);
			} else {
				break;
			}
		}
	}

	public void gameOfBank() {
		while (!isBusted(bank)) {
			System.out.print("Bank decides to..");
			int[] bankCurrentHandValue = bank.getTotalHandValues();

			// If it is over 17, dealer must stand. if its under 17, dealer must hit
			// If the dealer has an ace, and counting it as 11 would bring his total to 17 or more
			// (but not over 21), he must count the ace as 11 and stand.
			boolean doesBankHit;
			// If the two arrays are the same, there is no ace in hand
			if (bankCurrentHandValue[0] == bankCurrentHandValue[1]) {
				if (bankCurrentHandValue[0] < 17) {
					doesBankHit = true;
				} else {
					doesBankHit = false;
				}
			}
			// There is an Ace in Bank's hand
			else {
				// First, set the boolean based on whether cards without ace is greater than 17
				if (bankCurrentHandValue[1] < 17) {
					doesBankHit = true;
				} else {
					doesBankHit = false;
				}
				// But if has Ace, and counting it as 11, bank has to stand
				if (bankCurrentHandValue[0] > 17 && bankCurrentHandValue[0] < 21) {
					doesBankHit = false; // must stand as Hand with 11 is between 17-21
				}
			}
			if (doesBankHit) {
				System.out.println("HIT!");
				dealCard(bank, 1);

				displayPlayerHand(bank, bank.getPlayerHand().size()); // display all
				displayIsBusted(bank);
			} else {
				System.out.println("Stand.");
				// displayPlayerHand(bank, bank.getPlayerHand().size()); // display all
				break;
			}
		}
	}

	public void dealCard(Player playerToDeal, int numberToDeal) {
		for (int i = 0; i < numberToDeal; i++) {
			// pop the card from the top of the deck and push it to the player's hand
			// playerToDeal.getPlayerHand().push(this.deck.getDeck().pop());
			// the DealToPlayer method also increments the player's hand value
			playerToDeal.dealToPlayer(this.deck.getDeck().pop());
		}
	}

	public void displayPlayerHand(Player playerToDisplay, int cardsToDisplay) { // Note - in a later version DI can go here --i guess
		Stack<Card> currentHand = playerToDisplay.getPlayerHand();
		int[] handValues = playerToDisplay.getTotalHandValues();
		System.out.print(playerToDisplay.getPlayerName() + "'s hand value: ");
		if (cardsToDisplay == 1) {
			System.out.println("XXXX / XXXX");
		} else {
			System.out.println(handValues[0] + " / " + handValues[1]);
		}
		System.out.println("Cards in hand:");
		int displayCounter = 0;
		for (Card currentCard : currentHand) {
			if (cardsToDisplay >= 2) {
				System.out.println("\t" + currentCard);
			} else if (++displayCounter == 1) {
				System.out.println("\t" + currentCard);
			} else {
				System.out.println("\tXXXXXXXX");
			}

		}
		System.out.println("--------------");
	}

	public static int[] calculateCardValue(Card cardToEvaulateForValue) {
		int returnValue[] = new int[2];
		// Get the rank of the card
		String rank = cardToEvaulateForValue.getRank();
		String[] rankNames = Deck.getRankNames();
		int rankIndex = 0;
		for (int i = 0; i < rankNames.length; i++) {
			if (rankNames[i].equalsIgnoreCase(rank)) {
				rankIndex = i;
				break;
			}
		}
		// Index 0-7 -> numeric values of 2,3,4,5,6,7,8,9
		if (rankIndex < 8) {
			returnValue[0] = rankIndex + 2;
			returnValue[1] = rankIndex + 2;
		}

		// Index 8 - 9-10-11 -> numeric values of 10, jumbo, dáma, király
		else if (rankIndex < 12) {
			returnValue[0] = 10;
			returnValue[1] = 10;
		}
		// Index 12 -> numeric value of Ace, which is either 1 or 11
		else {
			returnValue[0] = 11;
			returnValue[1] = 1;
		}
		return returnValue;
	}

	public boolean isBusted(Player playerToCheck) {
		return (playerToCheck.getTotalHandValues()[1] > 21);
	}

	public void displayIsBusted(Player playerToDisplay) {
		if (isBusted(playerToDisplay)) {
			System.out.println(playerToDisplay.getPlayerName() + " is BUSTED!");
		}
	}

	public boolean doesUserHit() {
		while (true) {
			String input = keyboard.next();
			if (input.equalsIgnoreCase("h")) {
				return true;
			} else if (input.equalsIgnoreCase("s")) {
				return false;
			}
			System.out.println("Wrong input. Enter H or S!");
		}
	}

	public static void main(String[] args) {
		while (true) {
			Game aGameInstance = new Game();
			System.out.println("Play another game? Y/N");
			if (aGameInstance.keyboard.next().equalsIgnoreCase("n")){
				break;
			};
			
		}

		System.out.println("End.");
	}

}
