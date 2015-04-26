package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.FrenchCard;
import hu.unideb.inf.JCardGamesFX.model.FrenchRank;
import hu.unideb.inf.JCardGamesFX.model.FrenchSuit;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for class <code>FrenchCardViewFactory</code>.
 */
public class FrenchCardViewFactoryTest {

  /**
   * Creates a JavaFX thread to run the tests in.
   */
  @BeforeClass
  public static void initJFX() throws Exception {
    Thread t = new Thread("JavaFX init thread") {
      public void run() {
        Application.launch(TestApp.class);
      }
    };
    t.setDaemon(true);
    t.start();
    Thread.sleep(1000);
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
  public void testCreateCardView() {
    CardTheme theme = new CardTheme("/cardfaces/classicTest/theme.json", "/test_back.png");
    FrenchCardViewFactory.setCardTheme(theme);
    FrenchCard card = new FrenchCard(false, FrenchSuit.Clubs, FrenchRank.King);
    FrenchCardView cardView = FrenchCardViewFactory.createCardView(card);
    assertSame(theme.getFrontFace("KC"), cardView.getDisplay().getImage());
    card.flip();
    assertSame(theme.getBackFace(), cardView.getDisplay().getImage());
  }

  /**
   * Needed class for the JavaFX thread.
   */
  public static class TestApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
    }
  }
}
