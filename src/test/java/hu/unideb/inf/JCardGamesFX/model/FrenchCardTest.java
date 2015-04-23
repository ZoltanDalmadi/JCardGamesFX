package hu.unideb.inf.JCardGamesFX.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FrenchCardTest {

  /**
   * Reference to the <code>FrenchCard</code> object under test.
   */
  FrenchCard card;

  /**
   * Runs before each test method.
   *
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    card = new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Queen);
  }

  /**
   * Tests getter for field <code>suit</code>.
   *
   * @throws Exception
   */
  @Test
  public void testGetSuit() throws Exception {
    assertEquals(FrenchSuit.Hearts, card.getSuit());
  }

  /**
   * Tests getter for field <code>rank</code>.
   *
   * @throws Exception
   */
  @Test
  public void testGetRank() throws Exception {
    assertEquals(FrenchRank.Queen, card.getRank());
  }

  /**
   * Tests <code>toString()</code> method.
   *
   * @throws Exception
   */
  @Test
  public void testToString() throws Exception {
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
   *
   * @throws Exception
   */
  @Test
  public void testFlip() throws Exception {
    assertEquals(true, card.isFaceDown());
    card.flip();
    assertEquals(false, card.isFaceDown());
    card.flip();
    assertEquals(true, card.isFaceDown());
  }

  /**
   * Tests <code>faceDown()</code> method, which is defined in the superclass.
   *
   * @throws Exception
   */
  @Test
  public void testFaceDown() throws Exception {
    card.faceDown();
    assertEquals(true, card.isFaceDown());
    card.flip();
    card.faceDown();
    assertEquals(true, card.isFaceDown());
  }

  /**
   * Tests <code>faceUp()</code> method, which is defined in the superclass.
   *
   * @throws Exception
   */
  @Test
  public void testFaceUp() throws Exception {
    card.faceUp();
    assertEquals(false, card.isFaceDown());
    card.flip();
    card.faceUp();
    assertEquals(false, card.isFaceDown());
  }

}
