package hu.unideb.inf.JCardGamesFX.model;

public abstract class Card {

  /**
   * Whether the card is facing down.
   */
  protected boolean faceDown;

  /**
   * Constructs a Card object.
   *
   * @param faceDown Whether the card is facing down.
   */
  public Card(boolean faceDown) {
    this.faceDown = faceDown;
  }

  /**
   * Flips the card.
   */
  protected void flip() {
    faceDown = !faceDown;
  }

  /**
   * Turns the card down.
   */
  protected void faceDown() {
    faceDown = true;
  }

  /**
   * Turns the card up.
   */
  protected void faceUp() {
    faceDown = false;
  }

  /**
   * Returns whether the card is facing down.
   *
   * @return true if the card is facing down, false otherwise.
   */
  protected boolean isFaceDown() {
    return faceDown;
  }
}
