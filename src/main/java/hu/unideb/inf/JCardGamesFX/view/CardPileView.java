package hu.unideb.inf.JCardGamesFX.view;

import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Visual representation of a pile of cards.
 */
public class CardPileView extends Pane implements Iterable<CardView> {

  /**
   * The {@link List} containing the {@link CardView} objects,
   * which are lying on this pile.
   */
  private List<CardView> cards = FXCollections.observableArrayList();

  /**
   * Vertical gap to lay out the cards.
   */
  private double cardGap;

  /**
   * Constructs a {@link CardPileView} object, with the given gap.
   *
   * @param cardGap The vertical gap to lay out the cards.
   */
  public CardPileView(double cardGap) {
    this.cardGap = cardGap;
  }

  /**
   * Returns the vertical gap.
   *
   * @return The vertical gap.
   */
  public double getCardGap() {
    return cardGap;
  }

  /**
   * Sets the vertical gap.
   *
   * @param cardGap The new value of the gap.
   */
  public void setCardGap(double cardGap) {
    this.cardGap = cardGap;
  }

  /**
   * Returns the {@link List} of cards.
   *
   * @return The {@link List} of cards.
   */
  public List<CardView> getCards() {
    return cards;
  }

  /**
   * Returns the number of cards on this pile.
   *
   * @return The number of cards on this pile.
   */
  public int numOfCards() {
    return cards.size();
  }

  /**
   * Adds a {@link CardView} to this pile.
   *
   * @param cardView The {@link CardView} to be added.
   */
  public void addCardView(CardView cardView) {
    cards.add(cardView);
    cardView.setContainingPile(this);
    cardView.toFront();
    layoutCard(cardView);
  }

  /**
   * Lays out the {@link CardView} object to sit nicely in this pile.
   *
   * @param cardView The {@link CardView} object to lay out.
   */
  private void layoutCard(CardView cardView) {
    cardView.relocate(cardView.getLayoutX() + cardView.getTranslateX(),
        cardView.getLayoutY() + cardView.getTranslateY());

    cardView.setTranslateX(0);
    cardView.setTranslateY(0);
    cardView.setLayoutX(getLayoutX());
    cardView.setLayoutY(getLayoutY() + (cards.size() - 1) * cardGap);
  }

  /**
   * Returns whether the pile is empty.
   *
   * @return <code>true</code> if the pile is empty, <code>false</code> otherwise.
   */
  public boolean isEmpty() {
    return cards.isEmpty();
  }

  /**
   * Gets the card which lies on top of the pile.
   *
   * @return The card on top of the pile.
   */
  public CardView getTopCardView() {
    return cards.get(cards.size() - 1);
  }

  /**
   * Returns the card passed in the parameter and all the cards above in a
   * <code>List</code>.
   *
   * @param cardView The starting card of the <code>List</code>.
   * @return The <code>List</code> containing the card passed as parameter
   * and all cards above it in the pile.
   */
  public List<CardView> cardViewsAbove(CardView cardView) {
    return cards.subList(cards.indexOf(cardView), cards.size());
  }

  /**
   * Move cards to another pile. Intended to be used in conjunction with
   * <code>cardViewsAbove()</code>.
   *
   * @param cardsToMove The list of cards to move to the destination pile.
   * @param destPile    The destination pile.
   */
  public void moveCardViewsToPile(List<CardView> cardsToMove, CardPileView destPile) {
    cardsToMove.forEach(destPile::addCardView);
    cardsToMove.clear();
  }

  /**
   * Returns an iterator for iterating through the cards.
   *
   * @return The iterator.
   */
  public Iterator<CardView> iterator() {
    return cards.iterator();
  }

  /**
   * Performs the given action for each of the cards in this pile.
   *
   * @param action The action to perform.
   */
  @Override
  public void forEach(Consumer<? super CardView> action) {
    cards.forEach(action);
  }

  @Override
  public Spliterator<CardView> spliterator() {
    return cards.spliterator();
  }
}
