package hu.unideb.inf.JCardGamesFX.model;

/**
 * Class representing a classic French playing card.
 */
public class FrenchCard extends Card {

  /**
   * Constructs a {@link FrenchCard} object.
   *
   * @param faceDown Whether the card is facing down.
   */
  public FrenchCard(boolean faceDown) {
    super(faceDown);
  }

  /**
   * Constructs a {@link FrenchCard} object, with the specified suit and rank.
   *
   * @param faceDown Whether the card is facing down.
   * @param suit     Suit of the card.
   * @param rank     Rank of the card.
   */
  public FrenchCard(boolean faceDown, FrenchSuit suit, FrenchRank rank) {
    super(faceDown, suit, rank);
  }

}
