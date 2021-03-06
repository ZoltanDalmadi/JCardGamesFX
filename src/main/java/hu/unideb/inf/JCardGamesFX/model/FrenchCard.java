package hu.unideb.inf.JCardGamesFX.model;

/**
 * Class representing a classic French playing card.
 */
public class FrenchCard extends Card {

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

  /**
   * Builds a short {@link String} identifier. This should be only called once,
   * at the creation of the card object.
   *
   * @return The short identifier as a {@link String}.
   */
  @Override
  protected String buildId() {

    String suitChar = null;
    String rankChar = null;

    FrenchRank rank = (FrenchRank) this.getRank();

    switch (rank) {
      case Ace:
        rankChar = "A";
        break;
      case Two:
        rankChar = "2";
        break;
      case Three:
        rankChar = "3";
        break;
      case Four:
        rankChar = "4";
        break;
      case Five:
        rankChar = "5";
        break;
      case Six:
        rankChar = "6";
        break;
      case Seven:
        rankChar = "7";
        break;
      case Eight:
        rankChar = "8";
        break;
      case Nine:
        rankChar = "9";
        break;
      case Ten:
        rankChar = "10";
        break;
      case Jack:
        rankChar = "J";
        break;
      case Queen:
        rankChar = "Q";
        break;
      case King:
        rankChar = "K";
        break;
    }

    FrenchSuit suit = (FrenchSuit) this.getSuit();

    switch (suit) {
      case Clubs:
        suitChar = "C";
        break;
      case Diamonds:
        suitChar = "D";
        break;
      case Hearts:
        suitChar = "H";
        break;
      case Spades:
        suitChar = "S";
        break;
    }

    return rankChar + suitChar;
  }

}
