package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.model.CardPile;
import hu.unideb.inf.JCardGamesFX.model.FrenchCard;
import hu.unideb.inf.JCardGamesFX.model.FrenchRank;
import hu.unideb.inf.JCardGamesFX.model.FrenchSuit;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Unit test for class {@link KlondikeRules}.
 */
public class KlondikeRulesTest {

  /**
   * Reference to an object of the class {@link KlondikeRules}.
   */
  KlondikeRules klondikeRules;

  /**
   * Instantiates a new {@link KlondikeRules} object before each test.
   */
  @Before
  public void setUp() {
    klondikeRules = new KlondikeRules();
  }

  /**
   * Tests the parameter constructor.
   */
  @Test
  public void testParameterConstructor() {
    List<CardPile> testStandardPiles = new ArrayList<>();
    List<CardPile> testFoundations = new ArrayList<>();
    CardPile testWaste = new CardPile();
    CardPile testStock = new CardPile();

    KlondikeRules klondikeRules1 = new KlondikeRules(
        testStandardPiles, testFoundations, testWaste, testStock);

    assertEquals(testStandardPiles, klondikeRules1.getStandardPiles());
    assertEquals(testFoundations, klondikeRules1.getFoundations());
    assertEquals(testWaste, klondikeRules1.getWaste());
    assertEquals(testStock, klondikeRules1.getStock());
  }

  /**
   * Tests the <code>lookForPile()</code> method.
   */
  @Test
  public void testLookForPile() {
    List<CardPile> testStandardPiles = new ArrayList<>();
    IntStream.range(0, 7)
        .forEach(i -> testStandardPiles.add(new CardPile()));

    List<CardPile> testFoundations = new ArrayList<>();
    IntStream.range(0, 4)
        .forEach(i -> testFoundations.add(new CardPile()));

    CardPile testWaste = new CardPile();
    CardPile testStock = new CardPile();

    klondikeRules.setStandardPiles(testStandardPiles);
    klondikeRules.setFoundations(testFoundations);
    klondikeRules.setWaste(testWaste);
    klondikeRules.setStock(testStock);

    FrenchCard card1 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Four);
    testStandardPiles.get(3).addCard(card1);
    assertEquals(testStandardPiles.get(3), klondikeRules.lookForPile(card1));

    FrenchCard card2 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Ace);
    testFoundations.get(1).addCard(card2);
    assertEquals(testFoundations.get(1), klondikeRules.lookForPile(card2));

    FrenchCard card3 = new FrenchCard(false, FrenchSuit.Diamonds, FrenchRank.Ten);
    testWaste.addCard(card3);
    assertEquals(testWaste, klondikeRules.lookForPile(card3));

    FrenchCard card4 = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.King);
    testStock.addCard(card4);
    assertEquals(testStock, klondikeRules.lookForPile(card4));

    FrenchCard card5 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Queen);
    assertNull(klondikeRules.lookForPile(card5));
  }

  /**
   * Tests the <code>isOppositeColor()</code> and <code>isSameSuit()</code>
   * methods.
   */
  @Test
  public void testIsOppositeColorAndIsSameSuit() {
    FrenchCard card1 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Jack);
    FrenchCard card2 = new FrenchCard(false, FrenchSuit.Diamonds, FrenchRank.Ten);
    FrenchCard card3 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Six);
    FrenchCard card4 = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.Ace);

    FrenchCard card5 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Three);
    FrenchCard card6 = new FrenchCard(false, FrenchSuit.Diamonds, FrenchRank.Nine);
    FrenchCard card7 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Queen);
    FrenchCard card8 = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.Two);

    assertTrue(klondikeRules.isOppositeColor(card1, card2));
    assertTrue(klondikeRules.isOppositeColor(card1, card3));
    assertFalse(klondikeRules.isOppositeColor(card1, card4));

    assertTrue(klondikeRules.isOppositeColor(card2, card1));
    assertFalse(klondikeRules.isOppositeColor(card2, card3));
    assertTrue(klondikeRules.isOppositeColor(card2, card4));

    assertTrue(klondikeRules.isOppositeColor(card3, card1));
    assertFalse(klondikeRules.isOppositeColor(card3, card2));
    assertTrue(klondikeRules.isOppositeColor(card3, card4));

    assertFalse(klondikeRules.isOppositeColor(card4, card1));
    assertTrue(klondikeRules.isOppositeColor(card4, card2));
    assertTrue(klondikeRules.isOppositeColor(card4, card3));

    assertTrue(klondikeRules.isSameSuit(card1, card5));
    assertTrue(klondikeRules.isSameSuit(card2, card6));
    assertTrue(klondikeRules.isSameSuit(card3, card7));
    assertTrue(klondikeRules.isSameSuit(card4, card8));

    assertFalse(klondikeRules.isSameSuit(card1, card2));
    assertFalse(klondikeRules.isSameSuit(card2, card3));
    assertFalse(klondikeRules.isSameSuit(card3, card4));
  }

  /**
   * Tests the <code>isSmallerByOne()</code> method.
   */
  @Test
  public void testIsSmallerByOne() {
    FrenchCard card1 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Ten);
    FrenchCard card2 = new FrenchCard(false, FrenchSuit.Diamonds, FrenchRank.Jack);
    FrenchCard card3 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Six);

    assertTrue(klondikeRules.isSmallerByOne(card1, card2));
    assertFalse(klondikeRules.isSmallerByOne(card1, card3));
  }

  /**
   * Tests the <code>isLargerByOne()</code> method.
   */
  @Test
  public void testIsLargerByOne() {
    FrenchCard card1 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Five);
    FrenchCard card2 = new FrenchCard(false, FrenchSuit.Diamonds, FrenchRank.Four);
    FrenchCard card3 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Ten);

    assertTrue(klondikeRules.isLargerByOne(card1, card2));
    assertFalse(klondikeRules.isLargerByOne(card1, card3));
  }

  /**
   * Tests the <code>isSmallerByOneAndOppositeColor()</code> method.
   */
  @Test
  public void testIsSmallerByOneAndOppositeColor() {
    FrenchCard card1 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Ten);
    FrenchCard card2 = new FrenchCard(false, FrenchSuit.Diamonds, FrenchRank.Jack);
    FrenchCard card3 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Ten);

    assertTrue(klondikeRules.isSmallerByOneAndOppositeColor(card1, card2));
    assertFalse(klondikeRules.isSmallerByOneAndOppositeColor(card2, card1));
    assertFalse(klondikeRules.isSmallerByOneAndOppositeColor(card3, card1));
    assertFalse(klondikeRules.isSmallerByOneAndOppositeColor(card3, card2));
  }

  /**
   * Tests the <code>isLargerByOneAndSameSuit()</code> method.
   */
  @Test
  public void testIsLargerByOneAndSameSuit() {
    FrenchCard card1 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Jack);
    FrenchCard card2 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Ten);
    FrenchCard card3 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Jack);

    assertTrue(klondikeRules.isLargerByOneAndSameSuit(card1, card2));
    assertFalse(klondikeRules.isLargerByOneAndSameSuit(card2, card1));
    assertFalse(klondikeRules.isLargerByOneAndSameSuit(card3, card1));
    assertFalse(klondikeRules.isLargerByOneAndSameSuit(card3, card2));
  }

  /**
   * Tests the <code>isMoveValid()</code> method.
   */
  @Test
  public void testIsMoveValid() {
    List<CardPile> testStandardPiles = new ArrayList<>();
    IntStream.range(0, 7)
        .forEach(i -> testStandardPiles.add(new CardPile(CardPile.Type.Klondike)));

    List<CardPile> testFoundations = new ArrayList<>();
    IntStream.range(0, 4)
        .forEach(i -> testFoundations.add(new CardPile(CardPile.Type.Foundation)));

    FrenchCard jackSpades = new FrenchCard(true, FrenchSuit.Spades, FrenchRank.Jack);
    FrenchCard queenHearts = new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Queen);
    FrenchCard twoClubs = new FrenchCard(true, FrenchSuit.Clubs, FrenchRank.Two);
    FrenchCard threeDiamonds = new FrenchCard(true, FrenchSuit.Diamonds, FrenchRank.Three);
    FrenchCard aceClubs = new FrenchCard(true, FrenchSuit.Clubs, FrenchRank.Ace);
    FrenchCard kingDiamonds = new FrenchCard(true, FrenchSuit.Diamonds, FrenchRank.King);
    FrenchCard aceHearts = new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Ace);

    testStandardPiles.get(0).addCard(jackSpades);
    jackSpades.flip();
    testStandardPiles.get(2).addCard(queenHearts);
    queenHearts.flip();
    testStandardPiles.get(3).addCard(threeDiamonds);
    threeDiamonds.flip();

    assertTrue(klondikeRules.isMoveValid(jackSpades, testStandardPiles.get(2)));
    queenHearts.flip();
    assertFalse(klondikeRules.isMoveValid(jackSpades, testStandardPiles.get(2)));
    queenHearts.flip();
    assertFalse(klondikeRules.isMoveValid(jackSpades, testStandardPiles.get(3)));
    assertFalse(klondikeRules.isMoveValid(queenHearts, testStandardPiles.get(1)));
    assertTrue(klondikeRules.isMoveValid(kingDiamonds, testStandardPiles.get(1)));
    assertFalse(klondikeRules.isMoveValid(kingDiamonds, testStandardPiles.get(0)));

    testStandardPiles.get(5).addCard(twoClubs);
    twoClubs.flip();
    testStandardPiles.get(6).addCard(aceClubs);
    aceClubs.flip();
    testStandardPiles.get(1).addCard(aceHearts);
    aceHearts.flip();

    assertTrue(klondikeRules.isMoveValid(aceClubs, testFoundations.get(0)));
    assertTrue(klondikeRules.isMoveValid(aceClubs, testFoundations.get(1)));
    assertTrue(klondikeRules.isMoveValid(aceClubs, testFoundations.get(2)));
    assertTrue(klondikeRules.isMoveValid(aceClubs, testFoundations.get(3)));

    assertFalse(klondikeRules.isMoveValid(twoClubs, testFoundations.get(0)));
    assertFalse(klondikeRules.isMoveValid(twoClubs, testFoundations.get(1)));
    assertFalse(klondikeRules.isMoveValid(twoClubs, testFoundations.get(2)));
    assertFalse(klondikeRules.isMoveValid(twoClubs, testFoundations.get(3)));

    assertFalse(klondikeRules.isMoveValid(aceHearts, testStandardPiles.get(5)));

    testFoundations.get(0).addCard(aceClubs);
    aceClubs.flip();

    assertTrue(klondikeRules.isMoveValid(twoClubs, testFoundations.get(0)));
    assertFalse(klondikeRules.isMoveValid(threeDiamonds, testFoundations.get(0)));

    CardPile testWaste = new CardPile(CardPile.Type.Waste);
    assertFalse(klondikeRules.isMoveValid(kingDiamonds, testWaste));
  }
}
