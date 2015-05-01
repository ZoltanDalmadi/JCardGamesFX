package hu.unideb.inf.JCardGamesFX.model;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Class representing a deck of French playing cards.
 */
public class FrenchCardDeck extends CardDeck {

  /**
   * Creates a standard 52-card deck of French playing cards.
   *
   * @return The FrenchCardDeck object containing the 52 cards.
   */
  public static FrenchCardDeck createFrenchCardDeck() {

    FrenchCardDeck result = new FrenchCardDeck();

    for (FrenchSuit suit : FrenchSuit.values()) {
      for (FrenchRank rank : FrenchRank.values()) {
        result.addCard(new FrenchCard(true, suit, rank));
      }
    }

    return result;
  }

  /**
   * Returns an iterator to iterate through the cards.
   *
   * @return The iterator.
   */
  @Override
  public Iterator<Card> iterator() {
    return cards.iterator();
  }

  /**
   * Performs the given action on all the cards.
   *
   * @param action the specified action.
   */
  @Override
  public void forEach(Consumer<? super Card> action) {
    cards.forEach(action);
  }

  /**
   * Returns a spliterator for the cards.
   *
   * @return The spliterator.
   */
  @Override
  public Spliterator<Card> spliterator() {
    return cards.spliterator();
  }

}
