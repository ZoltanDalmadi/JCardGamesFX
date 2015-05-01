package hu.unideb.inf.JCardGamesFX.view;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    cardView = new CardView();
    try {
      theme = new CardTheme("/cardfaces/classicTest/theme.json", "/test_back.png");
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests parameter constructor.
   */
  @Test
  public void testParameterConstructor() {
    CardView cardView1 =
        new CardView(theme.getFrontFace("QH"), theme.getBackFace());

    assertNotNull(cardView1);
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
    assertEquals(theme.getFrontFace("KC"), cardView.getImage());
    cardView.flip();
    assertEquals(theme.getBackFace(), cardView.getImage());
    cardView.flip();
    assertEquals(theme.getFrontFace("KC"), cardView.getImage());
  }

}
