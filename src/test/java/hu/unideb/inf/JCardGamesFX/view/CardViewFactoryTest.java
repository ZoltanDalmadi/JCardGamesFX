package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.FrenchCard;
import hu.unideb.inf.JCardGamesFX.model.FrenchRank;
import hu.unideb.inf.JCardGamesFX.model.FrenchSuit;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for class <code>CardViewFactory</code>.
 */
public class CardViewFactoryTest {

  /**
   * Rule for testing in a JavaFX thread.
   */
  @Rule
  public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

  /**
   * Tests instantiation for better coverage.
   */
  @Test
  public void testInstantiate() {
    CardViewFactory testObject = new CardViewFactory();
    assertNotNull(testObject);
  }

  /**
   * Tests static setter and getter methods for the <code>theme</code> field.
   */
  @Test
  public void testSetterGetter() {
    CardViewFactory.setCardTheme(null);
    assertNull(CardViewFactory.getCardTheme());
    CardTheme theme = new CardTheme();
    CardViewFactory.setCardTheme(theme);
    assertEquals(theme, CardViewFactory.getCardTheme());
  }

  /**
   * Tests the static method <code>createCardView()</code>.
   */
  @Test
  public void testCreateCardView() {
    CardTheme theme = new CardTheme("/cardfaces/classicTest/theme.json", "/test_back.png");
    CardViewFactory.setCardTheme(theme);
    FrenchCard card = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.King);
    CardView cardView = CardViewFactory.createCardView(card);
    assertSame(theme.getFrontFace("KC"), cardView.getFrontFace());
    assertSame(theme.getBackFace(), cardView.getBackFace());
  }

}
