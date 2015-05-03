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

public class KlondikeGameTest {

  KlondikeGame klondikeGame;

  @Before
  public void setUp() {
    klondikeGame = new KlondikeGame();
  }

  @Test
  public void testStartNewGame() {
    klondikeGame.startNewGame();

    int cards = 1;

    for (CardPile standardPile : klondikeGame.getStandardPiles()) {
      assertEquals(cards, standardPile.numOfCards());

      for (int i = 0; i < cards - 1; i++)
        assertTrue(standardPile.getCards().get(i).isFaceDown());

      assertFalse(standardPile.getTopCard().isFaceDown());
      cards++;
    }

    assertEquals(24, klondikeGame.getStock().numOfCards());
    assertEquals(0, klondikeGame.getWaste().numOfCards());
    assertEquals(52, klondikeGame.getDeck().getCards().size());
    assertSame(klondikeGame.getWaste(), klondikeGame.getRules().getWaste());
    assertSame(klondikeGame.getStock(), klondikeGame.getRules().getStock());
    assertSame(klondikeGame.getStandardPiles(), klondikeGame.getRules().getStandardPiles());
    assertSame(klondikeGame.getFoundations(), klondikeGame.getRules().getFoundations());
  }

  @Test
  public void testMoveCard() {
    List<CardPile> testStandardPiles = new ArrayList<>();
    IntStream.range(0, 7)
        .forEach(i -> testStandardPiles.add(new CardPile(CardPile.Type.Klondike)));

    List<CardPile> testFoundations = new ArrayList<>();
    IntStream.range(0, 4)
        .forEach(i -> testFoundations.add(new CardPile(CardPile.Type.Foundation)));

    FrenchCard jackSpades = new FrenchCard(true, FrenchSuit.Spades, FrenchRank.Jack);
    FrenchCard queenHearts = new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Queen);
    FrenchCard threeDiamonds = new FrenchCard(true, FrenchSuit.Diamonds, FrenchRank.Three);

    testStandardPiles.get(0).addCard(jackSpades);
    jackSpades.flip();
    testStandardPiles.get(2).addCard(queenHearts);
    queenHearts.flip();
    testStandardPiles.get(3).addCard(threeDiamonds);
    threeDiamonds.flip();

    klondikeGame.moveCards(testStandardPiles.get(0).cardsAbove(jackSpades),
        testStandardPiles.get(0), testStandardPiles.get(2));

    assertSame(testStandardPiles.get(2).getTopCard(), jackSpades);

    klondikeGame.moveCards(testStandardPiles.get(2).cardsAbove(jackSpades),
        testStandardPiles.get(0), testStandardPiles.get(3));

    klondikeGame.moveCards(null,
        testStandardPiles.get(0), testStandardPiles.get(3));

    assertSame(testStandardPiles.get(2).getTopCard(), jackSpades);
    assertSame(testStandardPiles.get(3).getTopCard(), threeDiamonds);
  }

  @Test
  public void testIsGameWon() {
    klondikeGame.getFoundations().forEach(pile ->
        IntStream.range(0, 12)
            .forEach(i -> pile.addCard(
                new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Queen))));

    assertFalse(klondikeGame.isGameWon());

    klondikeGame.getFoundations().get(0).addCard(
        new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Queen));

    assertFalse(klondikeGame.isGameWon());

    klondikeGame.getFoundations().get(1).addCard(
        new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Queen));

    assertFalse(klondikeGame.isGameWon());

    klondikeGame.getFoundations().get(2).addCard(
        new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Queen));

    assertFalse(klondikeGame.isGameWon());

    klondikeGame.getFoundations().get(3).addCard(
        new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Queen));

    assertTrue(klondikeGame.isGameWon());
  }

  @Test
  public void testGetPileById() {
    assertSame(klondikeGame.getStock(), klondikeGame.getPileById("S"));
    assertSame(klondikeGame.getWaste(), klondikeGame.getPileById("W"));

    IntStream.range(0, klondikeGame.getFoundations().size())
        .forEach(i -> assertSame(klondikeGame.getFoundations().get(i),
            klondikeGame.getPileById("F" + i)));

    IntStream.range(0, klondikeGame.getStandardPiles().size())
        .forEach(i -> assertSame(klondikeGame.getStandardPiles().get(i),
            klondikeGame.getPileById("K" + i)));

    assertNull(klondikeGame.getPileById("N/A"));
  }

  @Test
  public void testDrawFromStock() {
    klondikeGame.startNewGame();
    int stockCards = klondikeGame.getStock().numOfCards();
    klondikeGame.drawFromStock(klondikeGame.getStock().getTopCard());

    assertEquals(stockCards - 1, klondikeGame.getStock().numOfCards());
    assertEquals(1, klondikeGame.getWaste().numOfCards());
  }
}
