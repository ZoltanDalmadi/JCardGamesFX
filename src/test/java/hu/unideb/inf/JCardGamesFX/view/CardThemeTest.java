package hu.unideb.inf.JCardGamesFX.view;

import javafx.scene.image.Image;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for class <code>CardTheme</code>.
 */
public class CardThemeTest {

  /**
   * Rule for testing in a JavaFX thread.
   */
  @Rule
  public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

  /**
   * Reference to the <code>CardTheme</code> object under test.
   */
  CardTheme theme;

  /**
   * Instantiates a new <code>CardTheme</code> object before each test.
   */
  @Before
  public void setUp() {
    theme = new CardTheme();
  }

  /**
   * Tests setter and getter for theme file path.
   */
  @Test
  public void testThemeFileOperations() {
    theme.setThemeFile("/cardfaces/classicTest/theme.json");
    assertEquals("/cardfaces/classicTest/theme.json", theme.getThemeFile());
  }

  /**
   * Tests getter and setter for field <code>backFace</code>.
   */
  @Test
  public void testBackFacePathOperations() {
    Image testBackFace = new Image("/test_back.png");
    theme.setBackFace(testBackFace);
    assertSame(testBackFace, theme.getBackFace());
  }

  /**
   * Tests insertion and removal operations.
   */
  @Test
  public void testMapOperations() {
    Image testFrontFace = new Image("/cardfaces/classicTest/10C.png");
    theme.addFrontFace("TEST KEY", testFrontFace);
    assertSame(testFrontFace, theme.getFrontFace("TEST KEY"));
    theme.removeFrontFace("TEST KEY");
    assertTrue(theme.getImages().isEmpty());
  }

  /**
   * Tests theme parsing.
   */
  @Test
  public void testParseTheme() {
    theme.setBackFace(new Image("/test_back.png"));
    theme.setThemeFile("/cardfaces/classicTest/theme.json");
    assertEquals(13, theme.getImages().size());
    assertEquals(130.0, theme.getFrontFace("10C").getWidth(), 0.0);
    assertEquals(180.0, theme.getFrontFace("QC").getHeight(), 0.0);
  }

}
