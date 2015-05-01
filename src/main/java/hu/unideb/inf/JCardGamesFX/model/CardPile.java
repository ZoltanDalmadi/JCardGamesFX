package hu.unideb.inf.JCardGamesFX.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class CardPile implements Iterable<Card> {

  private List<Card> cards = new ArrayList<>();

  public List<Card> getCards() {
    return cards;
  }

  public int numOfCards() {
    return cards.size();
  }

  public void addCard(Card card) {
    cards.add(card);
  }

  public boolean isEmpty() {
    return cards.isEmpty();
  }

  public Card getTopCard() {
    return cards.get(cards.size() - 1);
  }

  public List<Card> cardsAbove(Card card) {
    return cards.subList(cards.indexOf(card), cards.size());
  }

  public void moveCardsToPile(List<Card> cardsToMove, CardPile destPile) {
    cardsToMove.forEach(destPile::addCard);
    cardsToMove.clear();
  }

  @Override
  public Iterator<Card> iterator() {
    return cards.iterator();
  }

  @Override
  public void forEach(Consumer<? super Card> action) {
    cards.forEach(action);
  }

  @Override
  public Spliterator<Card> spliterator() {
    return cards.spliterator();
  }
}
