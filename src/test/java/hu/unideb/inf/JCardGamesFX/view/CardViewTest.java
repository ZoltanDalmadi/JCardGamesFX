package hu.unideb.inf.JCardGamesFX.view;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for class {@link CardView}.
 */
public class CardViewTest {

  /**
   * Rule for testing in a JavaFX thread.
   */
  @Rule
  public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();
  CardTheme theme;
  CardView cardView;

  /**
   * Creates a CardView object and a {@link CardTheme} object before testing.
   */
  @Before
  public void setUp() {
    cardView = new CardView(true);
    theme = new CardTheme("/cardfaces/classicTest/theme.json", "/test_back.png");
  }

  /**
   * Tests parameter constructor.
   */
  @Test
  public void testParameterConstructor() {
    CardView cardView1 =
        new CardView(false, theme.getFrontFace("QH"), theme.getBackFace(), "QH");

    assertNotNull(cardView1);
  }

  /**
   * Tests getter and setter for <code>shortID</code> field.
   */
  @Test
  public void testShortIDGetterSetter() {
    cardView.setShortID("TEST");
    assertSame("TEST", cardView.getShortID());
  }

  /**
   * Tests all methods.
   */
  @Test
  public void testGettersAndSettersAndFlip() {
    cardView.setBackFace(theme.getBackFace());
    assertEquals(theme.getBackFace(), cardView.getBackFace());
    cardView.setFrontFace(theme.getFrontFace("KC"));
    assertEquals(theme.getFrontFace("KC"), cardView.getFrontFace());
    assertEquals(theme.getBackFace(), cardView.getImage());
    cardView.flip();
    assertEquals(theme.getFrontFace("KC"), cardView.getImage());
    assertFalse(cardView.isFaceDown());
    cardView.flip();
    assertTrue(cardView.isFaceDown());
    assertEquals(theme.getBackFace(), cardView.getImage());

    assertEquals(2, cardView.getDropShadow().getRadius(), 0);
  }

}
