package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.FrenchCard;
import hu.unideb.inf.JCardGamesFX.model.FrenchRank;
import hu.unideb.inf.JCardGamesFX.model.FrenchSuit;
import javafx.scene.image.Image;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Unit test for class <code>FrenchCardView</code>.
 */
public class FrenchCardViewTest {

  /**
   * Rule for testing in a JavaFX thread.
   */
  @Rule
  public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

  /**
   * Reference to the <code>FrenchCardView</code> object under test.
   */
  FrenchCardView cardView;

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
}
