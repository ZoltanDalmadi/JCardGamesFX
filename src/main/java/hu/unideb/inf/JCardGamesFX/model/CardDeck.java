package hu.unideb.inf.JCardGamesFX.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class representing the concept of a deck of cards.
 */
public abstract class CardDeck implements Iterable<Card> {

  /**
   * A List holding the individual cards of this deck.
   */
  protected List<Card> cards;

  /**
   * Creates an empty {@link CardDeck} object.
   */
  public CardDeck() {
    this.cards = new ArrayList<>();
  }

  /**
   * Returns the list of cards of this deck.
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

  /**
   * Finds the card in the deck by its short identifier.
   *
   * @param id The short identifier.
   * @return The card object if found, null otherwise.
   */
  public Card getById(String id) {
    return cards.stream()
        .filter(card -> card.getId().equals(id)).findFirst().orElseGet(() -> null);
  }

}
