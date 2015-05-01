package hu.unideb.inf.JCardGamesFX.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Class representing a pile of cards.
 */
public class CardPile implements Iterable<Card> {

  /**
   * List holding the cards on this pile.
   */
  private List<Card> cards = new ArrayList<>();

  /**
   * Returns the list of cards.
   *
   * @return The list of cards.
   */
  public List<Card> getCards() {
    return cards;
  }

  /**
   * Returns the number of cards.
   *
   * @return The number of cards.
   */
  public int numOfCards() {
    return cards.size();
  }

  /**
   * Adds a card to the pile.
   *
   * @param card The card to be added to the pile.
   */
  public void addCard(Card card) {
    cards.add(card);
  }

  /**
   * Returns whether the pile is empty.
   *
   * @return <code>true</code> if the pile is empty, <code>false</code> otherwise.
   */
  public boolean isEmpty() {
    return cards.isEmpty();
  }

  /**
   * Gets the card which lies on top of the pile.
   *
   * @return The card on top of the pile.
   */
  public Card getTopCard() {
    return cards.get(cards.size() - 1);
  }

  /**
   * Returns the card passed in the parameter and all the cards above in a
   * <code>List</code>.
   *
   * @param card The starting card of the <code>List</code>.
   * @return The <code>List</code> containing the card passed as parameter
   * and all cards above it in the pile.
   */
  public List<Card> cardsAbove(Card card) {
    return cards.subList(cards.indexOf(card), cards.size());
  }

  /**
   * Move cards to another pile. Intended to be used in conjunction with
   * <code>cardsAbove()</code>.
   *
   * @param cardsToMove The list of cards to move to the destination pile.
   * @param destPile    The destination pile.
   */
  public void moveCardsToPile(List<Card> cardsToMove, CardPile destPile) {
    cardsToMove.forEach(destPile::addCard);
    cardsToMove.clear();
  }

  /**
   * Returns an iterator for iterating through the cards.
   *
   * @return The iterator.
   */
  @Override
  public Iterator<Card> iterator() {
    return cards.iterator();
  }

  /**
   * Performs the given action for each of the cards in this pile.
   *
   * @param action The action to perform.
   */
  @Override
  public void forEach(Consumer<? super Card> action) {
    cards.forEach(action);
  }

}
