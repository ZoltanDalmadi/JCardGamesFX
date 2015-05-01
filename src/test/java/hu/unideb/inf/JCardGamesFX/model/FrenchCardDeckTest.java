package hu.unideb.inf.JCardGamesFX.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.StringJoiner;

import static org.junit.Assert.*;

/**
 * Unit test for class <code>FrenchCardDeck</code>.
 */
public class FrenchCardDeckTest {

  /**
   * Reference to the <code>FrenchCardDeck</code> object under test.
   */
  FrenchCardDeck deck;

  /**
   * Runs before each test method.
   */
  @Before
  public void setUp() {
    deck = new FrenchCardDeck();
  }

  /**
   * Tests the static standard 52 card creating method.
   */
  @Test
  public void testCreateFrenchCardDeck() {
    FrenchCardDeck standardDeck = FrenchCardDeck.createFrenchCardDeck();
    assertEquals(52, standardDeck.cards.size());
    assertEquals("The Ace of Clubs", standardDeck.cards.get(0).toString());
    assertEquals("The Jack of Clubs", standardDeck.cards.get(10).toString());
    assertEquals("The Ace of Spades", standardDeck.cards.get(13).toString());
    assertEquals("The Queen of Hearts", standardDeck.cards.get(37).toString());
  }

  /**
   * Test for <code>addCard()</code> method, which is defined in the superclass.
   */
  @Test
  public void testAddCard() {
    FrenchCard card1 = new FrenchCard(true, FrenchSuit.Clubs, FrenchRank.Jack);
    List<Card> testList = new ArrayList<>();

    testList.add(card1);
    deck.addCard(card1);
    assertEquals(testList, deck.getCards());

    FrenchCard card2 = new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.King);

    testList.add(card2);
    deck.addCard(card2);
    assertEquals(testList, deck.getCards());
  }

  /**
   * Test for <code>removeCard()</code> method, which is defined in the superclass.
   */
  @Test
  public void testRemoveCard() {
    FrenchCard card = new FrenchCard(true, FrenchSuit.Clubs, FrenchRank.Jack);
    deck.addCard(card);
    deck.removeCard(card);
    assertTrue(deck.getCards().isEmpty());
  }

  /**
   * Test for <code>shuffle()</code> method, which is defined in the superclass.
   */
  @Test
  public void testShuffle() {
    FrenchCardDeck standardDeck = FrenchCardDeck.createFrenchCardDeck();
    StringJoiner originalString = new StringJoiner("#");
    StringJoiner shuffledString = new StringJoiner("#");

    for (Card card : standardDeck.getCards())
      originalString.add(card.toString());

    standardDeck.shuffle();

    for (Card card : standardDeck.getCards())
      shuffledString.add(card.toString());

    assertNotEquals(originalString, shuffledString);
  }

  @Test
  public void testIteratorAndForEachAndSpliterator() {
    FrenchCardDeck standardDeck = FrenchCardDeck.createFrenchCardDeck();
    Iterator<Card> testIterator = standardDeck.iterator();
    standardDeck.forEach(card -> assertSame(card, testIterator.next()));

    Spliterator<Card> testSpliterator = standardDeck.spliterator();
    assertEquals(testSpliterator.characteristics(),
        standardDeck.getCards().spliterator().characteristics());
  }

  @Test
  public void testGetById() {
    FrenchCardDeck standardDeck = FrenchCardDeck.createFrenchCardDeck();
    assertSame(standardDeck.getCards().get(0), standardDeck.getById("AC"));
    assertSame(standardDeck.getCards().get(34), standardDeck.getById("9H"));
    assertNull(standardDeck.getById("N/A"));
  }
}
