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

  public int numOfCards() {
    return cards.size();
  }

  public void addCardView(CardView cardView) {
    cards.add(cardView);
    cardView.setContainingPile(this);
    cardView.toFront();
    layoutCard(cardView);
  }

  private void layoutCard(CardView cardView) {
    cardView.relocate(cardView.getLayoutX() + cardView.getTranslateX(),
        cardView.getLayoutY() + cardView.getTranslateY());

    cardView.setTranslateX(0);
    cardView.setTranslateY(0);
    cardView.setLayoutX(getLayoutX());
    cardView.setLayoutY(getLayoutY() + (cards.size() - 1) * cardGap);
  }

  public boolean isEmpty() {
    return cards.isEmpty();
  }

  public CardView getTopCard() {
    return cards.get(cards.size() - 1);
  }

  public List<CardView> cardsAbove(CardView cardView) {
    return cards.subList(cards.indexOf(cardView), cards.size());
  }

  public void moveCardsToPile(List<CardView> cardsToMove, CardPileView destPile) {
    cardsToMove.forEach(destPile::addCardView);
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
