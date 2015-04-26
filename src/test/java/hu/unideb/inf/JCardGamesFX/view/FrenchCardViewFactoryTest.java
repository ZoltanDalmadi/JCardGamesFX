package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.FrenchCard;
import hu.unideb.inf.JCardGamesFX.model.FrenchRank;
import hu.unideb.inf.JCardGamesFX.model.FrenchSuit;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrenchCardViewFactoryTest {

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

  @Test
  public void testSetterGetter() {
    FrenchCardViewFactory.setCardTheme(null);
    assertNull(FrenchCardViewFactory.getCardTheme());
    CardTheme theme = new CardTheme();
    FrenchCardViewFactory.setCardTheme(theme);
    assertEquals(theme, FrenchCardViewFactory.getCardTheme());
  }

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

  public static class TestApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
    }
  }
}
