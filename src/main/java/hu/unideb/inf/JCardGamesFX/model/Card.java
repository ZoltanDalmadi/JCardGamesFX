package hu.unideb.inf.JCardGamesFX.model;

/**
 * Abstract class representing the concept of a playing card.
 */
public abstract class Card {

  /**
   * Whether the card is facing down.
   */
  private boolean faceDown;

  /**
   * Suit of the card.
   */
  private Suit suit;

  /**
   * Rank of the card.
   */
  private Rank rank;

  /**
   * Short identifier.
   */
  protected String id;

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
    this.id = buildId();
  }

  /**
   * Returns a short identifier.
   *
   * @return A short identifier as a String.
   */
  public String getId() {
    return id;
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
   * Returns the rank of this card.
   *
   * @return The rank of this card.
   */
  public Rank getRank() {
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

  /**
   * Abstract method whose purpose is to return a short string identifier.
   * Subclasses of this class must implement this method.
   *
   * @return The short identifier.
   */
  protected abstract String buildId();

}
