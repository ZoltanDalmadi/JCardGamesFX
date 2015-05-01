package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.FrenchCard;
import hu.unideb.inf.JCardGamesFX.model.FrenchRank;
import hu.unideb.inf.JCardGamesFX.model.FrenchSuit;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

import static org.junit.Assert.*;

public class CardPileViewTest {

  /**
   * Rule for testing in a JavaFX thread.
   */
  @Rule
  public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

  CardPileView cardPileView;

  @Before
  public void setUp() throws IOException, ParseException {
    cardPileView = new CardPileView(40);

    CardTheme theme = new CardTheme("/cardfaces/classicTest/theme.json", "/test_back.png");
    CardViewFactory.setCardTheme(theme);

    FrenchCard card1 = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.Jack);
    CardView cardView1 = CardViewFactory.createCardView(card1);
    cardPileView.addCardView(cardView1);

    FrenchCard card2 = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.Ten);
    CardView cardView2 = CardViewFactory.createCardView(card2);
    cardPileView.addCardView(cardView2);

    FrenchCard card3 = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.Nine);
    CardView cardView3 = CardViewFactory.createCardView(card3);
    cardPileView.addCardView(cardView3);

    FrenchCard card4 = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.Eight);
    CardView cardView4 = CardViewFactory.createCardView(card4);
    cardPileView.addCardView(cardView4);
  }

  @Test
  public void testGetSetCardGap() {
    assertEquals(40, cardPileView.getCardGap(), 0);
    cardPileView.setCardGap(30);
    assertEquals(30, cardPileView.getCardGap(), 0);
  }

  @Test
  public void testAddCardAndGetTopCard() {
    FrenchCard card = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.Seven);
    CardView cardView = CardViewFactory.createCardView(card);
    cardPileView.addCardView(cardView);
    assertEquals(cardPileView, cardView.getContainingPile());
    assertEquals(5, cardPileView.numOfCards());
    assertEquals(cardView, cardPileView.getTopCardView());
  }

  @Test
  public void testIsEmpty() {
    assertFalse(cardPileView.isEmpty());
    cardPileView.getCards().clear();
    assertTrue(cardPileView.isEmpty());
  }

  @Test
  public void testCardsAbove() {
    List<CardView> list = cardPileView.cardViewsAbove(cardPileView.getCards().get(1));

    assertEquals(cardPileView.getCards().get(1), list.get(0));
    assertEquals(cardPileView.getCards().get(2), list.get(1));
    assertEquals(cardPileView.getCards().get(3), list.get(2));
  }

  @Test
  public void testMoveCardsToPile() {
    CardPileView destPile = new CardPileView(40);

    List<CardView> list = cardPileView.cardViewsAbove(cardPileView.getCards().get(1));

    cardPileView.moveCardViewsToPile(list, destPile);
    assertEquals(1, cardPileView.numOfCards());
    assertEquals(3, destPile.numOfCards());
    assertTrue(list.isEmpty());
  }

  @Test
  public void testIterator() {
    Iterator<CardView> testIterator = cardPileView.iterator();
    assertSame(testIterator.next(), cardPileView.getCards().get(0));
    assertSame(testIterator.next(), cardPileView.getCards().get(1));
    assertSame(testIterator.next(), cardPileView.getCards().get(2));
  }

  @Test
  public void testForEach() {
    Iterator<CardView> testIterator = cardPileView.iterator();
    cardPileView.forEach(cardView -> assertSame(cardView, testIterator.next()));
  }

  @Test
  public void testSpliterator() {
    Spliterator<CardView> testSpliterator = cardPileView.spliterator();
    assertEquals(testSpliterator.characteristics(),
        cardPileView.spliterator().characteristics());
  }
}
