package hu.unideb.inf.JCardGamesFX.model;

/**
 * Abstract class representing the concept of a playing card.
 */
public abstract class Card {

  /**
   * Whether the card is facing down.
   */
  private boolean faceDown;
  private Suit suit;
  private Rank rank;

  /**
   * Constructs a {@link Card} object, with the specified suit and rank.
   *
   * @param faceDown Whether the card is facing down.
   * @param suit     The suit of the card.
   * @param rank     The rank of the card.
   */
  public Card(boolean faceDown, Suit suit, Rank rank) {
    this.faceDown = faceDown;
    this.suit = suit;
    this.rank = rank;
  }

  /**
   * Constructs a {@link Card} object.
   *
   * @param faceDown Whether the card is facing down.
   */
  public Card(boolean faceDown) {
    this.faceDown = faceDown;
  }

  /**
   * Returns the suit of this card.
   *
   * @return The Suit of this card.
   */
  public Suit getSuit() {
    return suit;
  }

  /**
   * Sets the suit of this card.
   *
   * @param suit The suit to be set.
   */
  public void setSuit(Suit suit) {
    this.suit = suit;
  }

  /**
   * Returns the rank of this card.
   *
   * @return The rank of this card.
   */
  public Rank getRank() {
    return rank;
  }

  /**
   * Sets the rank of this card.
   *
   * @param rank The rank to be set.
   */
  public void setRank(Rank rank) {
    this.rank = rank;
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

  /**
   * Flips the card.
   */
  public void flip() {
    faceDown = !faceDown;
  }

  /**
   * Turns the card down.
   */
  public void faceDown() {
    faceDown = true;
  }

  /**
   * Turns the card up.
   */
  public void faceUp() {
    faceDown = false;
  }

  /**
   * Returns whether the card is facing down.
   *
   * @return true if the card is facing down, false otherwise.
   */
  public boolean isFaceDown() {
    return faceDown;
  }

  public abstract String getId();

}
