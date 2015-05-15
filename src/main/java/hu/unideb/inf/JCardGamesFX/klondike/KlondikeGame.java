package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.model.Card;
import hu.unideb.inf.JCardGamesFX.model.CardPile;
import hu.unideb.inf.JCardGamesFX.model.FrenchCardDeck;
import javafx.collections.FXCollections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * This class represents the game itself, with methods to manipulate the state
 * of the game.
 */
public class KlondikeGame {

  /**
   * Reference to the {@link FrenchCardDeck} object associated with this game.
   */
  private FrenchCardDeck deck;

  /**
   * Reference to the {@link CardPile} object representing the stock.
   */
  private CardPile stock;

  /**
   * Reference to the {@link CardPile} object representing the waste.
   */
  private CardPile waste;

  /**
   * The list of {@link CardPile} objects representing the foundation piles.
   */
  private List<CardPile> foundations;

  /**
   * The list of {@link CardPile} objects representing the standard piles.
   */
  private List<CardPile> standardPiles;

  /**
   * The rules for this game.
   */
  private KlondikeRules rules;

  /**
   * Constructs a new {@link KlondikeGame} object.
   */
  public KlondikeGame() {
    // Create deck
    this.deck = FrenchCardDeck.createFrenchCardDeck();

    // create stock
    this.stock = new CardPile(CardPile.Type.Stock, "S");

    // create waste
    this.waste = new CardPile(CardPile.Type.Waste, "W");

    // create foundations
    this.foundations = FXCollections.observableArrayList();

    for (int i = 0; i < 4; i++)
      foundations.add(new CardPile(CardPile.Type.Foundation, "F" + i));

    // create standard piles
    this.standardPiles = FXCollections.observableArrayList();

    for (int i = 0; i < 7; i++)
      standardPiles.add(new CardPile(CardPile.Type.Klondike, "K" + i));

    // load rules
    this.rules = new KlondikeRules(standardPiles, foundations, waste, stock);
  }

  /**
   * Returns the deck of cards that is used in the current game.
   *
   * @return The {@link FrenchCardDeck} object.
   */
  public FrenchCardDeck getDeck() {
    return deck;
  }

  /**
   * Returns the stock pile.
   *
   * @return The {@link CardPile} object representing the stock.
   */
  public CardPile getStock() {
    return stock;
  }

  /**
   * Returns the waste pile.
   *
   * @return The {@link CardPile} object representing the waste.
   */
  public CardPile getWaste() {
    return waste;
  }

  /**
   * Returns the list of foundation piles.
   *
   * @return The {@link List} of {@link CardPile} objects representing
   * the foundations.
   */
  public List<CardPile> getFoundations() {
    return foundations;
  }

  /**
   * Returns the list of standard piles.
   *
   * @return The {@link List} of {@link CardPile} objects representing
   * the standard piles.
   */
  public List<CardPile> getStandardPiles() {
    return standardPiles;
  }

  /**
   * Returns the {@link KlondikeRules} object associated with this
   * {@link KlondikeGame} instance.
   *
   * @return The {@link KlondikeRules} object.
   */
  public KlondikeRules getRules() {
    return rules;
  }

  /**
   * Starts a new game. Effectively shuffles the deck of cards, and deals them
   * to the standard piles, and puts the rest to the stock.
   */
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

  /**
   * Moves a list of {@link Card} object from a {@link CardPile} to another.
   *
   * @param cardsToMove The {@link List} of {@link Card}'s to move.
   * @param from        The source {@link CardPile} object.
   * @param to          The destination {@link CardPile} object.
   */
  public void moveCards(List<Card> cardsToMove, CardPile from, CardPile to) {
    if (cardsToMove == null)
      return;

    from.moveCardsToPile(cardsToMove, to);
  }

  /**
   * Draws a {@link Card} from the stock and put it on the waste, flipped.
   *
   * @param card The {@link Card} object to draw.
   */
  public void drawFromStock(Card card) {
    stock.moveCardToPile(card, waste);
    card.flip();
  }

  /**
   * Refills all cards from the waste to the stock, in reverse order.
   */
  public void refillStockFromWaste() {
    ListIterator<Card> revIt =
        waste.getCards().listIterator(waste.numOfCards());

    while (revIt.hasPrevious()) {
      Card currentCard = revIt.previous();
      currentCard.flip();
      stock.addCard(currentCard);
      revIt.remove();
    }
  }

  /**
   * Checks if the current game is won by the player.
   *
   * @return true if the game is won, false otherwise.
   */
  public boolean isGameWon() {
    return foundations.stream().allMatch(pile -> pile.numOfCards() == 13);
  }

  /**
   * Looks up a {@link CardPile} object by its short identifier.
   *
   * @param id The short identifier to look for.
   * @return The found {@link CardPile} object, or null if no matching pile found.
   */
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
