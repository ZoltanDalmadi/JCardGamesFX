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

  @Override
  public Iterator<Card> iterator() {
    return null;
  }

  @Override
  public void forEach(Consumer<? super Card> action) {

  }

  @Override
  public Spliterator<Card> spliterator() {
    return null;
  }
}
