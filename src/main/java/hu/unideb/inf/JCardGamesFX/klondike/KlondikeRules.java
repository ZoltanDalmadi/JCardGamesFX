package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.model.Card;
import hu.unideb.inf.JCardGamesFX.model.CardPile;
import hu.unideb.inf.JCardGamesFX.model.FrenchCard;
import hu.unideb.inf.JCardGamesFX.model.FrenchRank;
import hu.unideb.inf.JCardGamesFX.model.FrenchSuit;

import java.util.List;

public class KlondikeRules {

  private List<CardPile> standardPiles;
  private List<CardPile> foundations;
  private CardPile stock;
  private CardPile waste;

  public KlondikeRules() {
  }

  public KlondikeRules(List<CardPile> standardPiles,
                       List<CardPile> foundations,
                       CardPile waste, CardPile stock) {
    this.standardPiles = standardPiles;
    this.foundations = foundations;
    this.waste = waste;
    this.stock = stock;
  }

  public List<CardPile> getStandardPiles() {
    return standardPiles;
  }

  public void setStandardPiles(List<CardPile> standardPiles) {
    this.standardPiles = standardPiles;
  }

  public List<CardPile> getFoundations() {
    return foundations;
  }

  public void setFoundations(List<CardPile> foundations) {
    this.foundations = foundations;
  }

  public CardPile getStock() {
    return stock;
  }

  public void setStock(CardPile stock) {
    this.stock = stock;
  }

  public CardPile getWaste() {
    return waste;
  }

  public void setWaste(CardPile waste) {
    this.waste = waste;
  }

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

  public boolean isOppositeColor(FrenchCard card1, FrenchCard card2) {
    FrenchSuit otherSuit = (FrenchSuit) card2.getSuit();

    switch ((FrenchSuit) card1.getSuit()) {
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

  public boolean isSameSuit(FrenchCard card1, FrenchCard card2) {
    return card1.getSuit() == card2.getSuit();
  }

  public boolean isSmallerByOne(FrenchCard card1, FrenchCard card2) {
    return ((FrenchRank) card1.getRank())
        .compareTo((FrenchRank) card2.getRank()) == -1;
  }

  public boolean isSmallerByOneAndOppositeColor(FrenchCard card1, FrenchCard card2) {
    return isSmallerByOne(card1, card2) && isOppositeColor(card1, card2);
  }

  public boolean isSmallerByOneAndSameSuit(FrenchCard card1, FrenchCard card2) {
    return isSmallerByOne(card1, card2) && isSameSuit(card1, card2);
  }
}
