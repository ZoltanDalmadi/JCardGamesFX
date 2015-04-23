package hu.unideb.inf.JCardGamesFX.model;

public class FrenchCardDeck extends CardDeck {

  /**
   * Creates a standard 52-card deck of Frenck playing cards.
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
}
