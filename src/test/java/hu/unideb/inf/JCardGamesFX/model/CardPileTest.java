package hu.unideb.inf.JCardGamesFX.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for class {@link CardPile}.
 */
public class CardPileTest {

  /**
   * Reference to the {@link CardPile} object under test.
   */
  CardPile cardPile;

  /**
   * Instantiates a new object before each test.
   */
  @Before
  public void setUp() {
    cardPile = new CardPile();
  }

  @Test
  public void testParameterConstructorAndGetterSetterForType() {
    CardPile cardPile1 = new CardPile(CardPile.Type.Klondike);
    assertEquals(CardPile.Type.Klondike, cardPile1.getType());
    cardPile1.setType(CardPile.Type.Waste);
    assertEquals(CardPile.Type.Waste, cardPile1.getType());
  }

  /**
   * Tests <code>addCard()</code> and <code>numOfCards()</code> methods.
   */
  @Test
  public void testAddCardAndNumOfCards() {
    Card card = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Four);
    cardPile.addCard(card);
    assertEquals(1, cardPile.numOfCards());
  }

  /**
   * Tests <code>isEmpty()</code> method.
   */
  @Test
  public void testIsEmpty() {
    assertTrue(cardPile.isEmpty());
  }

  /**
   * Tests <code>getTopCard()</code> method.
   */
  @Test
  public void testGetTopCard() {
    Card card1 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Four);
    Card card2 = new FrenchCard(false, FrenchSuit.Diamonds, FrenchRank.Three);
    cardPile.addCard(card1);
    cardPile.addCard(card2);
    assertSame(card2, cardPile.getTopCard());
  }

  /**
   * Tests <code>cardsAbove()</code> and <code>moveCardsToPile()</code> methods.
   */
  @Test
  public void testCardsAboveAndMoveCardsToPile() {
    Card card1 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Five);
    Card card2 = new FrenchCard(false, FrenchSuit.Diamonds, FrenchRank.Four);
    Card card3 = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.Three);
    Card card4 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Two);

    cardPile.addCard(card1);
    cardPile.addCard(card2);
    cardPile.addCard(card3);
    cardPile.addCard(card4);

    List<Card> cardsAbove = cardPile.cardsAbove(card2);

    assertSame(card2, cardsAbove.get(0));
    assertSame(card3, cardsAbove.get(1));
    assertSame(card4, cardsAbove.get(2));

    CardPile cardPile1 = new CardPile();

    cardPile.moveCardsToPile(cardsAbove, cardPile1);

    assertEquals(1, cardPile.numOfCards());
    assertEquals(3, cardPile1.numOfCards());
    assertTrue(cardsAbove.isEmpty());
  }

  /**
   * Tests <code>iterator()</code> and <code>forEach()</code> methods.
   */
  @Test
  public void testIteratorAndForEach() {
    Card card1 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Five);
    Card card2 = new FrenchCard(false, FrenchSuit.Diamonds, FrenchRank.Four);
    Card card3 = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.Three);
    Card card4 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Two);

    cardPile.addCard(card1);
    cardPile.addCard(card2);
    cardPile.addCard(card3);
    cardPile.addCard(card4);

    Iterator<Card> testIterator1 = cardPile.iterator();
    assertSame(testIterator1.next(), cardPile.getCards().get(0));
    assertSame(testIterator1.next(), cardPile.getCards().get(1));
    assertSame(testIterator1.next(), cardPile.getCards().get(2));

    Iterator<Card> testIterator2 = cardPile.iterator();
    cardPile.forEach(card -> assertSame(card, testIterator2.next()));
  }

}
