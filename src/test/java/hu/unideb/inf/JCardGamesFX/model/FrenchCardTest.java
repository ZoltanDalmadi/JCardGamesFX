package hu.unideb.inf.JCardGamesFX.model;

import hu.unideb.inf.JCardGamesFX.view.FrenchCardView;
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
   * Tests <code>addView()</code> method, which is defined in the superclass.
   */
  @Test
  public void testAddView() {
    FrenchCardView view = new FrenchCardView(card);
    card.addView(view);
    assertSame(view, card.getViews().get(0));
  }

  /**
   * Tests <code>removeView()</code> method, which is defined in the superclass.
   */
  @Test
  public void testRemoveView() {
    FrenchCardView view = new FrenchCardView(card);
    card.addView(view);
    card.removeView(view);
    assertTrue(card.getViews().isEmpty());
  }

}
