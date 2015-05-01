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

  @Override
  public Iterator<Card> iterator() {
    return cards.iterator();
  }

  @Override
  public void forEach(Consumer<? super Card> action) {
    cards.forEach(action);
  }

  @Override
  public Spliterator<Card> spliterator() {
    return cards.spliterator();
  }

}
