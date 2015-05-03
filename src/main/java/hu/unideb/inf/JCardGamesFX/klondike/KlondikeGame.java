package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.model.Card;
import hu.unideb.inf.JCardGamesFX.model.CardPile;
import hu.unideb.inf.JCardGamesFX.model.FrenchCardDeck;
import javafx.collections.FXCollections;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class KlondikeGame {

  private FrenchCardDeck deck;
  private CardPile stock;
  private CardPile waste;
  private List<CardPile> foundations;
  private List<CardPile> standardPiles;

  private KlondikeRules rules;

  public KlondikeGame() {
    // Create deck
    this.deck = FrenchCardDeck.createFrenchCardDeck();

    // create stock
    this.stock = new CardPile(CardPile.Type.Stock, "S");

    // create waste
    this.waste = new CardPile(CardPile.Type.Waste, "W");

    // create foundations
    this.foundations = FXCollections.observableArrayList();
    IntStream.range(0, 4)
        .forEach(i -> foundations.add(new CardPile(CardPile.Type.Foundation, "F" + i)));

    // create standard piles
    this.standardPiles = FXCollections.observableArrayList();
    IntStream.range(0, 7)
        .forEach(i -> standardPiles.add(new CardPile(CardPile.Type.Klondike, "K" + i)));

    // load rules
    this.rules = new KlondikeRules(standardPiles, foundations, waste, stock);
  }

  public FrenchCardDeck getDeck() {
    return deck;
  }

  public CardPile getStock() {
    return stock;
  }

  public CardPile getWaste() {
    return waste;
  }

  public List<CardPile> getFoundations() {
    return foundations;
  }

  public List<CardPile> getStandardPiles() {
    return standardPiles;
  }

  public KlondikeRules getRules() {
    return rules;
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

  public void moveCards(List<Card> cardsToMove, CardPile from, CardPile to) {
    if (cardsToMove == null)
      return;

    if (rules.isMoveValid(cardsToMove.get(0), to))
      from.moveCardsToPile(cardsToMove, to);
  }

  public boolean isGameWon() {
    return foundations.stream().allMatch(pile -> pile.numOfCards() == 13);
  }

  public CardPile getPileById(String id) {
    CardPile result;

    result = standardPiles.stream()
        .filter(pile -> pile.getId().equals(id)).findFirst().orElse(null);

    if (result != null)
      return result;

    result = foundations.stream()
        .filter(pile -> pile.getId().equals(id)).findFirst().orElse(null);

    if (result != null)
      return result;

    if (waste.getId().equals(id))
      return waste;

    if (stock.getId().equals(id))
      return stock;

    return null;
  }

}
