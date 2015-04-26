package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.FrenchCard;
import hu.unideb.inf.JCardGamesFX.model.FrenchRank;
import hu.unideb.inf.JCardGamesFX.model.FrenchSuit;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Unit test for class <code>FrenchCardView</code>.
 */
public class FrenchCardViewTest {

  /**
   * Reference to the <code>FrenchCardView</code> object under test.
   */
  FrenchCardView cardView;

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
   * Sets up the needed objects for the unit tests.
   */
  @Before
  public void setUp() {
    FrenchCard card = new FrenchCard(true, FrenchSuit.Hearts, FrenchRank.Queen);
    Image frontFace = new Image("/test_front.png");
    Image backFace = new Image("/test_back.png");
    cardView = new FrenchCardView(card, frontFace, backFace);
    card.addView(cardView);

    // For better coverage
    FrenchCardView otherCardView = new FrenchCardView();
    otherCardView.setCard(card);
    cardView.setFrontFace(frontFace);
    cardView.setBackFace(backFace);
    card.addView(otherCardView);
  }

  /**
   * Tests <code>getBackFace()</code> method.
   */
  @Test
  public void testGetBackFace() {
    assertEquals(130.0, cardView.getBackFace().getWidth(), 0.0);
  }

  /**
   * Tests <code>getCard()</code> method.
   */
  @Test
  public void testGetCard() {
    assertEquals(FrenchRank.Queen, cardView.getCard().getRank());
    assertEquals(FrenchSuit.Hearts, cardView.getCard().getSuit());
  }

  /**
   * Tests <code>getDisplay()</code> method.
   */
  @Test
  public void testGetDisplay() {
    assertEquals(130.0, cardView.getDisplay().getImage().getWidth(), 0.0);
    assertEquals(180.0, cardView.getDisplay().getImage().getHeight(), 0.0);
  }

  /**
   * Tests <code>getFrontFace()</code> method.
   */
  @Test
  public void testGetFrontFace() {
    assertEquals(180.0, cardView.getFrontFace().getHeight(), 0.0);
  }

  /**
   * Tests if the view properly updates when the underlying
   * {@link FrenchCard} object is flipped.
   */
  @Test
  public void testUpdate() {
    Image displayedImage = cardView.getDisplay().getImage();
    cardView.getCard().flip();
    Image afterFlipImage = cardView.getDisplay().getImage();
    assertNotEquals(displayedImage, afterFlipImage);
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
