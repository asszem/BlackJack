package main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class GameTest {
	public static Game testInstance = new Game();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//After dealing 4 cards, the deck size must be 100, and player's hand sizes 2-2
	@Test
	public void testDealCard() {
		assertEquals(100,testInstance.deckSize);
	}
	
	@Test
	public void testCardValueEvaulation(){
	Card testCard=new Card(Deck.Suit.Káró.toString(), "Ász");
	int actValue[]=Game.calculateCardValue(testCard);
	int expValue[] = {11,1};
	assertArrayEquals(expValue,actValue);

	 testCard=new Card(Deck.Suit.Káró.toString(), "2");
	actValue=Game.calculateCardValue(testCard);
	expValue[0] = 2;
	expValue[1] = 2;
	assertArrayEquals(expValue,actValue);

	 testCard=new Card(Deck.Suit.Káró.toString(), "10");
	actValue=Game.calculateCardValue(testCard);
	expValue[0] = 10;
	expValue[1] = 10;
	assertArrayEquals(expValue,actValue);

	 testCard=new Card(Deck.Suit.Káró.toString(), "Jumbó");
	actValue=Game.calculateCardValue(testCard);
	expValue[0] = 10;
	expValue[1] = 10;
	assertArrayEquals(expValue,actValue);
	}

}
