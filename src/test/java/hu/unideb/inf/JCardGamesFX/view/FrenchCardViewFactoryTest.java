package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.FrenchCard;
import hu.unideb.inf.JCardGamesFX.model.FrenchRank;
import hu.unideb.inf.JCardGamesFX.model.FrenchSuit;
import org.json.simple.parser.ParseException;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Unit test for class <code>FrenchCardViewFactory</code>.
 */
public class FrenchCardViewFactoryTest {

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
    FrenchCardViewFactory testObject = new FrenchCardViewFactory();
    assertNotNull(testObject);
  }

  /**
   * Tests static setter and getter methods for the <code>theme</code> field.
   */
  @Test
  public void testSetterGetter() {
    FrenchCardViewFactory.setCardTheme(null);
    assertNull(FrenchCardViewFactory.getCardTheme());
    CardTheme theme = new CardTheme();
    FrenchCardViewFactory.setCardTheme(theme);
    assertEquals(theme, FrenchCardViewFactory.getCardTheme());
  }

  /**
   * Tests the static method <code>createCardView()</code>.
   */
  @Test
  public void testCreateCardView() throws IOException, ParseException {
    CardTheme theme = new CardTheme("/cardfaces/classicTest/theme.json", "/test_back.png");
    FrenchCardViewFactory.setCardTheme(theme);
    FrenchCard card = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.King);
    FrenchCardView cardView = FrenchCardViewFactory.createCardView(card);
    assertSame(theme.getFrontFace("KC"), cardView.getDisplay().getImage());
    card.flip();
    assertSame(theme.getBackFace(), cardView.getDisplay().getImage());
  }

}
