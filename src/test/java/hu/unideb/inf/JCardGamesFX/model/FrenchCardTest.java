package hu.unideb.inf.JCardGamesFX.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for class <code>FrenchCard</code>.
 */
public class FrenchCardTest {

  /**
   * Reference to the <code>FrenchCard</code> object under test.
   */
  FrenchCard card;

  /**
   * Runs before each test method.
   */
  @Before
  public void setUp() {
    card = new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Queen);
  }

  /**
   * Tests getter for field <code>suit</code>.
   */
  @Test
  public void testGetSuit() {
    assertEquals(FrenchSuit.Hearts, card.getSuit());
  }

  /**
   * Tests getter for field <code>rank</code>.
   */
  @Test
  public void testGetRank() {
    assertEquals(FrenchRank.Queen, card.getRank());
  }

  /**
   * Tests <code>toString()</code> method.
   */
  @Test
  public void testToString() {
    assertEquals("The Queen of Hearts", card.toString());
    card = new FrenchCard(true, FrenchSuit.Clubs, FrenchRank.Ace);
    assertEquals("The Ace of Clubs", card.toString());
    card = new FrenchCard(true, FrenchSuit.Spades, FrenchRank.Jack);
    assertEquals("The Jack of Spades", card.toString());
    card = new FrenchCard(true, FrenchSuit.Diamonds, FrenchRank.Seven);
    assertEquals("The Seven of Diamonds", card.toString());
  }

  /**
   * Tests <code>flip()</code> method, which is defined in the superclass.
   */
  @Test
  public void testFlip() {
    assertEquals(true, card.isFaceDown());
    card.flip();
    assertEquals(false, card.isFaceDown());
    card.flip();
    assertEquals(true, card.isFaceDown());
  }

  /**
   * Tests <code>faceDown()</code> method, which is defined in the superclass.
   */
  @Test
  public void testFaceDown() {
    card.faceDown();
    assertEquals(true, card.isFaceDown());
    card.flip();
    card.faceDown();
    assertEquals(true, card.isFaceDown());
  }

  /**
   * Tests <code>faceUp()</code> method, which is defined in the superclass.
   */
  @Test
  public void testFaceUp() {
    card.faceUp();
    assertEquals(false, card.isFaceDown());
    card.flip();
    card.faceUp();
    assertEquals(false, card.isFaceDown());
  }

}
