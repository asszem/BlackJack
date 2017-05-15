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

}
