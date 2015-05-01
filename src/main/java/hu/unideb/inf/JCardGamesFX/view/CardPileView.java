package hu.unideb.inf.JCardGamesFX.view;

import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class CardPileView extends Pane implements Iterable<CardView> {

  private List<CardView> cards = FXCollections.observableArrayList();
  private double cardGap;
  private int numOfCards = 0;

  public CardPileView(double cardGap) {
    this.cardGap = cardGap;
  }

  public double getCardGap() {
    return cardGap;
  }

  public void setCardGap(double cardGap) {
    this.cardGap = cardGap;
  }

  public List<CardView> getCards() {

    return cards;
  }

  public void addCard(CardView card) {
    cards.add(card);
    card.setContainingPile(this);
    card.toFront();
    numOfCards++;
    layoutCard(card);
  }

  private void layoutCard(CardView card) {
    card.relocate(card.getLayoutX() + card.getTranslateX(),
        card.getLayoutY() + card.getTranslateY());

    card.setTranslateX(0);
    card.setTranslateY(0);
    card.setLayoutX(getLayoutX());
    card.setLayoutY(getLayoutY() + (numOfCards - 1) * cardGap);
  }

  public boolean isEmpty() {
    return cards.isEmpty();
  }

  public CardView getTopCard() {
    return cards.get(cards.size() - 1);
  }

  public List<CardView> cardsAbove(CardView card) {
    return cards.subList(cards.indexOf(card), cards.size());
  }

  public void moveCardsToPile(List<CardView> cardsToMove, CardPileView destPile) {
    numOfCards -= cardsToMove.size();
    cardsToMove.forEach(destPile::addCard);
    cardsToMove.clear();
  }

  public Iterator<CardView> iterator() {
    return cards.iterator();
  }

  @Override
  public void forEach(Consumer<? super CardView> action) {
    cards.forEach(action);
  }

  @Override
  public Spliterator<CardView> spliterator() {
    return cards.spliterator();
  }
}
