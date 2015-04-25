package hu.unideb.inf.JCardGamesFX.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CardThemeTest {

  CardTheme theme;

  @Before
  public void setUp() {
    theme = new CardTheme("/cardfaces/classicTest/theme.json", "/test_back.png");
  }

  @Test
  public void testThemeFileOperations() {
    theme.setThemeFile("TEST");
    assertEquals("TEST", theme.getThemeFile());

    CardTheme theme2 = new CardTheme();
    theme2.setThemeFile("TEST");
    assertEquals("TEST", theme2.getThemeFile());
  }

  @Test
  public void testBackFacePathOperations() {
    theme.setBackFacePath("TEST");
    assertEquals("TEST", theme.getBackFacePath());

    CardTheme theme2 = new CardTheme("TEST");
    assertEquals("TEST", theme2.getBackFacePath());
  }

  @Test
  public void testMapOperations() {
    theme.addFrontFacePathFor("TEST KEY", "TEST VALUE");
    assertEquals("TEST VALUE", theme.getFrontFacePathFor("TEST KEY"));
    theme.removeFrontFacePath("TEST KEY");
    assertTrue(theme.getPaths().isEmpty());
  }

  @Test
  public void testParseTheme() throws Exception {
    theme.parseTheme();
    assertEquals(13, theme.getPaths().size());
    assertEquals("/cardfaces/classicTest/10C.png", theme.getFrontFacePathFor("10C"));
    assertEquals("/cardfaces/classicTest/QC.png", theme.getFrontFacePathFor("QC"));
  }
}
