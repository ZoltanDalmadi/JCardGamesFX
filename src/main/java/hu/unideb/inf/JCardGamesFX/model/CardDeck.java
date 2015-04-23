package hu.unideb.inf.JCardGamesFX.model;

import java.util.Collections;
import java.util.List;

public abstract class CardDeck {
  /**
   * A List holding the individual cards of this deck.
   */
  protected List<Card> cards;

  /**
   * Creates an empty <code>CardDeck</code> object.
   */
  public CardDeck() {
  }

  /**
   * Returns the list of cards od this deck.
   *
   * @return The list of cards
   */
  public List<Card> getCards() {
    return cards;
  }

  /**
   * Adds a Card object to the deck.
   *
   * @param card The card to be added to the deck.
   */
  public void addCard(Card card) {
    cards.add(card);
  }

  /**
   * Removes a card from the deck.
   *
   * @param card The card to be removed.
   * @return true if the card is removed from the deck, false otherwise.
   */
  public boolean removeCard(Card card) {
    return cards.remove(card);
  }

  /**
   * Shuffles the deck.
   */
  public void shuffle() {

    // Researches proved that if we want a deck of cards to be well shuffled,
    // we have to do it about seven times.
    for (int i = 0; i < 7; i++) {
      Collections.shuffle(cards);
    }
  }
}
