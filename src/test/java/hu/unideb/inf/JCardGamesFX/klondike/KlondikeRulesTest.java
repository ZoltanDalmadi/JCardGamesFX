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

public class KlondikeRulesTest {

  KlondikeRules klondikeRules;

  @Before
  public void setUp() throws Exception {
    klondikeRules = new KlondikeRules();
  }

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

  @Test
  public void testLookForPile() {
    List<CardPile> testStandardPiles = new ArrayList<>();
    IntStream.range(0, 7).parallel()
        .forEach(i -> testStandardPiles.add(new CardPile()));

    List<CardPile> testFoundations = new ArrayList<>();
    IntStream.range(0, 4).parallel()
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
  }

  @Test
  public void testIsOppositeColorAndIsSameColor() {
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

    assertFalse(klondikeRules.isOppositeColor(card2, card3));
    assertTrue(klondikeRules.isOppositeColor(card2, card4));

    assertTrue(klondikeRules.isOppositeColor(card3, card4));

    assertTrue(klondikeRules.isSameSuit(card1, card5));
    assertTrue(klondikeRules.isSameSuit(card2, card6));
    assertTrue(klondikeRules.isSameSuit(card3, card7));
    assertTrue(klondikeRules.isSameSuit(card4, card8));

    assertFalse(klondikeRules.isSameSuit(card1, card2));
    assertFalse(klondikeRules.isSameSuit(card1, card3));
    assertFalse(klondikeRules.isSameSuit(card1, card4));
  }

  @Test
  public void testIsSmallerByOne() {
    FrenchCard card1 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Ten);
    FrenchCard card2 = new FrenchCard(false, FrenchSuit.Diamonds, FrenchRank.Jack);
    FrenchCard card3 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Six);

    assertTrue(klondikeRules.isSmallerByOne(card1, card2));
    assertFalse(klondikeRules.isSmallerByOne(card1, card3));
  }

  @Test
  public void testIsSmallerByOneAndOppositeColor() {
    FrenchCard card1 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Ten);
    FrenchCard card2 = new FrenchCard(false, FrenchSuit.Diamonds, FrenchRank.Jack);
    FrenchCard card3 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Six);

    assertTrue(klondikeRules.isSmallerByOneAndOppositeColor(card1, card2));
    assertFalse(klondikeRules.isSmallerByOneAndOppositeColor(card1, card3));
  }

  @Test
  public void testIsSmallerByOneAndSameSuit() {
    FrenchCard card1 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Ten);
    FrenchCard card2 = new FrenchCard(false, FrenchSuit.Spades, FrenchRank.Jack);
    FrenchCard card3 = new FrenchCard(false, FrenchSuit.Hearts, FrenchRank.Six);

    assertTrue(klondikeRules.isSmallerByOneAndSameSuit(card1, card2));
    assertFalse(klondikeRules.isSmallerByOneAndSameSuit(card1, card3));
  }
}
