package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.model.Card;
import hu.unideb.inf.JCardGamesFX.model.CardPile;
import hu.unideb.inf.JCardGamesFX.model.FrenchCardDeck;
import javafx.collections.FXCollections;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class KlondikeGame {

  public static FrenchCardDeck deck;
  public static CardPile stock;
  public static CardPile waste;
  public static List<CardPile> foundations;
  public static List<CardPile> standardPiles;

  private static KlondikeRules rules;

  public KlondikeGame() {
    // Create deck
    deck = FrenchCardDeck.createFrenchCardDeck();

    // create stock
    stock = new CardPile();

    // create waste
    waste = new CardPile();

    // create foundations
    foundations = FXCollections.observableArrayList();
    IntStream.range(0, 4)
        .forEach(i -> foundations.add(new CardPile()));

    // create standard piles
    standardPiles = FXCollections.observableArrayList();
    IntStream.range(0, 7)
        .forEach(i -> standardPiles.add(new CardPile()));

    // load rules
    rules = new KlondikeRules(standardPiles, foundations, waste, stock);
  }

  public void startNewGame() {
    // shuffle cards
    deck.shuffle();

    // deal to piles
    Iterator<Card> deckIterator = deck.iterator();

    int cardsToPut = 1;

    for (CardPile standardPile : standardPiles) {
      for (int i = 0; i < cardsToPut; i++)
        standardPile.addCard(deckIterator.next());

      standardPile.getTopCard().flip();
      cardsToPut++;
    }

    // put rest to stock
    deckIterator.forEachRemaining(stock::addCard);

  }
}
