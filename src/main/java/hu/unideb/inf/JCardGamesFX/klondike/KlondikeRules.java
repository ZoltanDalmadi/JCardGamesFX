package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.model.Card;
import hu.unideb.inf.JCardGamesFX.model.CardPile;
import hu.unideb.inf.JCardGamesFX.model.FrenchRank;
import hu.unideb.inf.JCardGamesFX.model.FrenchSuit;

import java.util.List;

/**
 * Class representing the rules of the Klondike game.
 */
public class KlondikeRules {

  /**
   * Reference to the list of the standard card piles.
   */
  private List<CardPile> standardPiles;

  /**
   * Reference to the list of the foundation piles.
   */
  private List<CardPile> foundations;

  /**
   * Reference to the stock pile.
   */
  private CardPile stock;

  /**
   * Reference to the waste pile.
   */
  private CardPile waste;

  /**
   * Creates an empty {@link KlondikeRules} object.
   */
  public KlondikeRules() {
  }

  /**
   * Creates a {@link KlondikeRules} object with the given pile references.
   *
   * @param standardPiles Reference to the list of standard card piles.
   * @param foundations   Reference to the list of foundation piles.
   * @param waste         Reference to the waste pile.
   * @param stock         Reference to the stock pile.
   */
  public KlondikeRules(List<CardPile> standardPiles,
                       List<CardPile> foundations,
                       CardPile waste, CardPile stock) {
    this.standardPiles = standardPiles;
    this.foundations = foundations;
    this.waste = waste;
    this.stock = stock;
  }

  /**
   * Returns the list of standard piles.
   *
   * @return The list of standard piles.
   */
  public List<CardPile> getStandardPiles() {
    return standardPiles;
  }

  /**
   * Sets the reference to the list of standard piles.
   *
   * @param standardPiles The new reference to the list of standard piles.
   */
  public void setStandardPiles(List<CardPile> standardPiles) {
    this.standardPiles = standardPiles;
  }

  /**
   * Returns the list of foundation piles.
   *
   * @return The list of foundation piles.
   */
  public List<CardPile> getFoundations() {
    return foundations;
  }

  /**
   * Sets the reference to the list of foundation piles.
   *
   * @param foundations The new reference to the list of foundation piles.
   */
  public void setFoundations(List<CardPile> foundations) {
    this.foundations = foundations;
  }

  /**
   * Returns the stock pile.
   *
   * @return The stock pile.
   */
  public CardPile getStock() {
    return stock;
  }

  /**
   * Sets reference to the stock pile.
   *
   * @param stock The new reference to the stock pile.
   */
  public void setStock(CardPile stock) {
    this.stock = stock;
  }

  /**
   * Returns the waste pile.
   *
   * @return The waste pile.
   */
  public CardPile getWaste() {
    return waste;
  }

  /**
   * Sets reference to the waste pile.
   *
   * @param waste The new reference to the waste pile.
   */
  public void setWaste(CardPile waste) {
    this.waste = waste;
  }

  /**
   * Returns the pile which currently contains the given {@link Card} object.
   *
   * @param card The {@link Card} object to check.
   * @return The {@link CardPile} which contains the card.
   */
  public CardPile lookForPile(Card card) {
    for (CardPile standardPile : standardPiles) {
      if (standardPile.getCards().contains(card)) {
        return standardPile;
      }
    }

    for (CardPile foundation : foundations) {
      if (foundation.getCards().contains(card)) {
        return foundation;
      }
    }

    if (stock.getCards().contains(card)) {
      return stock;
    }

    if (waste.getCards().contains(card)) {
      return waste;
    }

    return null;
  }

  /**
   * Checks if two cards are of opposite color.
   *
   * @param card1 The first card.
   * @param card2 The second card.
   * @return <code>true</code> if the colors of the two cards are opposite,
   * <code>false</code> otherwise.
   */
  public boolean isOppositeColor(Card card1, Card card2) {
    FrenchSuit thisSuit = (FrenchSuit) card1.getSuit();
    FrenchSuit otherSuit = (FrenchSuit) card2.getSuit();

    switch (thisSuit) {
      case Spades:
        if (otherSuit == FrenchSuit.Hearts || otherSuit == FrenchSuit.Diamonds)
          return true;
        break;

      case Clubs:
        if (otherSuit == FrenchSuit.Hearts || otherSuit == FrenchSuit.Diamonds)
          return true;
        break;

      case Hearts:
        if (otherSuit == FrenchSuit.Clubs || otherSuit == FrenchSuit.Spades)
          return true;
        break;

      case Diamonds:
        if (otherSuit == FrenchSuit.Clubs || otherSuit == FrenchSuit.Spades)
          return true;
        break;
    }

    return false;
  }

  /**
   * Checks if two cards are in the same suit.
   *
   * @param card1 The first card.
   * @param card2 The second card.
   * @return <code>true</code> if the two cards are in the same suit,
   * <code>false</code> otherwise.
   */
  public boolean isSameSuit(Card card1, Card card2) {
    return card1.getSuit() == card2.getSuit();
  }

  /**
   * Checks if the card passed as the first parameter is smaller by one rank
   * than the card passed as the second parameter.
   *
   * @param card1 The first card.
   * @param card2 The second card.
   * @return <code>true</code> if the first card is smaller by one rank than
   * the second card, <code>false</code> otherwise.
   */
  public boolean isSmallerByOne(Card card1, Card card2) {
    return ((FrenchRank) card1.getRank())
        .compareTo((FrenchRank) card2.getRank()) == -1;
  }

  /**
   * Checks if the card passed as the first parameter is larger by one rank
   * than the card passed as the second parameter.
   *
   * @param card1 The first card.
   * @param card2 The second card.
   * @return <code>true</code> if the first card is larger by one rank than
   * the second card, <code>false</code> otherwise.
   */
  public boolean isLargerByOne(Card card1, Card card2) {
    return ((FrenchRank) card1.getRank())
        .compareTo((FrenchRank) card2.getRank()) == 1;
  }

  /**
   * Checks if the card passed as the first parameter is smaller by one rank
   * than the card passed as the second parameter and is opposite color.
   *
   * @param card1 The first card.
   * @param card2 The second card.
   * @return <code>true</code> if the first card is smaller by one rank than
   * the second card and is opposite color, <code>false</code> otherwise.
   */
  public boolean isSmallerByOneAndOppositeColor(Card card1, Card card2) {
    return isSmallerByOne(card1, card2) && isOppositeColor(card1, card2);
  }

  /**
   * Checks if the card passed as the first parameter is smaller by one rank
   * than the card passed as the second parameter and is in the same suit.
   *
   * @param card1 The first card.
   * @param card2 The second card.
   * @return <code>true</code> if the first card is smaller by one rank than
   * the second card and is in the same suit, <code>false</code> otherwise.
   */
  public boolean isLargerByOneAndSameSuit(Card card1, Card card2) {
    return isLargerByOne(card1, card2) && isSameSuit(card1, card2);
  }

  /**
   * Checks if moving the current card to the destination pile is valid.
   *
   * @param card     The card to check.
   * @param destPile The destination pile.
   * @return <code>true</code> if the move is valid,
   * <code>false</code> otherwise.
   */
  public boolean isMoveValid(Card card, CardPile destPile) {
    if (destPile.getType() == CardPile.Type.Klondike) {
      if (destPile.isEmpty())
        return card.getRank() == FrenchRank.King;
      else
        return isSmallerByOneAndOppositeColor(card, destPile.getTopCard()) &&
            !destPile.getTopCard().isFaceDown() &&
            destPile.getTopCard().getRank() != FrenchRank.Two;
    }

    if (destPile.getType() == CardPile.Type.Foundation) {
      if (destPile.isEmpty())
        return card.getRank() == FrenchRank.Ace;
      else
        return isLargerByOneAndSameSuit(card, destPile.getTopCard());
    }

    return false;
  }

}
