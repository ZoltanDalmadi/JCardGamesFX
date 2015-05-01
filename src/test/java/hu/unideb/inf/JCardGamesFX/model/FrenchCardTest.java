package hu.unideb.inf.JCardGamesFX.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    card = new FrenchCard(true);
    card.setRank(FrenchRank.Ace);
    card.setSuit(FrenchSuit.Clubs);
    assertEquals("The Ace of Clubs", card.toString());
    card = new FrenchCard(true);
    card.setRank(FrenchRank.Jack);
    card.setSuit(FrenchSuit.Spades);
    assertEquals("The Jack of Spades", card.toString());
    card = new FrenchCard(true);
    card.setRank(FrenchRank.Seven);
    card.setSuit(FrenchSuit.Diamonds);
    assertEquals("The Seven of Diamonds", card.toString());
  }

  /**
   * Tests <code>flip()</code> method, which is defined in the superclass.
   */
  @Test
  public void testFlip() {
    assertTrue(card.isFaceDown());
    card.flip();
    assertFalse(card.isFaceDown());
    card.flip();
    assertTrue(card.isFaceDown());
  }

  /**
   * Tests <code>faceDown()</code> method, which is defined in the superclass.
   */
  @Test
  public void testFaceDown() {
    card.faceDown();
    assertTrue(card.isFaceDown());
    card.flip();
    card.faceDown();
    assertTrue(card.isFaceDown());
  }

  /**
   * Tests <code>faceUp()</code> method, which is defined in the superclass.
   */
  @Test
  public void testFaceUp() {
    card.faceUp();
    assertFalse(card.isFaceDown());
    card.flip();
    card.faceUp();
    assertFalse(card.isFaceDown());
  }

  /**
   * Tests <code>getId()</code> method.
   */
  @Test
  public void testGetId() {
    assertEquals("QH", card.getId());
    card = new FrenchCard(true, FrenchSuit.Clubs, FrenchRank.Ace);
    assertEquals("AC", card.getId());
    card = new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Ten);
    assertEquals("10H", card.getId());
    card = new FrenchCard(true, FrenchSuit.Spades, FrenchRank.Two);
    assertEquals("2S", card.getId());
    card = new FrenchCard(true, FrenchSuit.Spades, FrenchRank.Three);
    assertEquals("3S", card.getId());
    card = new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Four);
    assertEquals("4H", card.getId());
    card = new FrenchCard(true, FrenchSuit.Clubs, FrenchRank.Five);
    assertEquals("5C", card.getId());
    card = new FrenchCard(true, FrenchSuit.Diamonds, FrenchRank.Six);
    assertEquals("6D", card.getId());
    card = new FrenchCard(true, FrenchSuit.Diamonds, FrenchRank.Seven);
    assertEquals("7D", card.getId());
    card = new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Eight);
    assertEquals("8H", card.getId());
    card = new FrenchCard(true, FrenchSuit.Spades, FrenchRank.Nine);
    assertEquals("9S", card.getId());
    card = new FrenchCard(true, FrenchSuit.Clubs, FrenchRank.Ten);
    assertEquals("10C", card.getId());
    card = new FrenchCard(true, FrenchSuit.Spades, FrenchRank.Jack);
    assertEquals("JS", card.getId());
    card = new FrenchCard(true, FrenchSuit.Diamonds, FrenchRank.King);
    assertEquals("KD", card.getId());
  }
}
