package hu.unideb.inf.JCardGamesFX.model;

/**
 * Class representing a classic French playing card.
 */
public class FrenchCard extends Card {

  /**
   * Suit of this card object.
   */
  private FrenchSuit suit;

  /**
   * Rank of this card object.
   */
  private FrenchRank rank;

  /**
   * Constructs a {@link FrenchCard} object.
   *
   * @param faceDown Whether the card is facing down.
   * @param suit     Suit of the card.
   * @param rank     Rank of the card.
   */
  public FrenchCard(boolean faceDown, FrenchSuit suit, FrenchRank rank) {
    super(faceDown);
    this.suit = suit;
    this.rank = rank;
  }

  /**
   * Returns the suit of this card.
   *
   * @return The Suit of this card.
   */
  public FrenchSuit getSuit() {
    return suit;
  }

  /**
   * Returns the rank of this card.
   *
   * @return The rank of this card.
   */
  public FrenchRank getRank() {
    return rank;
  }

  /**
   * Returns a {@link String} representation of this card.
   *
   * @return The {@link String} representation of this card.
   */
  @Override
  public String toString() {
    return "The " + rank + " of " + suit;
  }
}
