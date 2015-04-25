package hu.unideb.inf.JCardGamesFX.model;

import hu.unideb.inf.JCardGamesFX.view.CardObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing the concept of a playing card.
 */
public abstract class Card {

  /**
   * The list of views attached to this card.
   */
  List<CardObserver> views = new ArrayList<>();
  /**
   * Whether the card is facing down.
   */
  private boolean faceDown;
  private Suit suit;
  private Rank rank;

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
    updateViews();
  }

  /**
   * Turns the card down.
   */
  public void faceDown() {
    faceDown = true;
    updateViews();
  }

  /**
   * Turns the card up.
   */
  public void faceUp() {
    faceDown = false;
    updateViews();
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
   * Adds a {@link CardObserver} to the list of views.
   *
   * @param view The {@link CardObserver} to be added.
   */
  public void addView(CardObserver view) {
    views.add(view);
    updateViews();
  }

  /**
   * Removes a {@link CardObserver} from the list of views.
   *
   * @param view The {@link CardObserver} to be removed.
   * @return true if successful, false otherwise.
   */
  public boolean removeView(CardObserver view) {
    return views.remove(view);
  }

  /**
   * Returns the list of views attached to this card.
   *
   * @return The list of views.
   */
  public List<CardObserver> getViews() {
    return views;
  }

  /**
   * Updates all views attached to this card.
   */
  private void updateViews() {
    views.forEach(CardObserver::update);
  }

}
