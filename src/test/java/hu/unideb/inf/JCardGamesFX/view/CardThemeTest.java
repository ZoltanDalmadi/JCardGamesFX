package hu.unideb.inf.JCardGamesFX.view;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardThemeTest {

  CardTheme theme;

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

  @Before
  public void setUp() {
    theme = new CardTheme();
  }

  @Test
  public void testThemeFileOperations() {
    theme.setThemeFile("/cardfaces/classicTest/theme.json");
    assertEquals("/cardfaces/classicTest/theme.json", theme.getThemeFile());
  }

  @Test
  public void testBackFacePathOperations() {
    Image testBackFace = new Image("/test_back.png");
    theme.setBackFace(testBackFace);
    assertSame(testBackFace, theme.getBackFace());
  }

  @Test
  public void testMapOperations() {
    Image testFrontFace = new Image("/cardfaces/classicTest/10C.png");
    theme.addFrontFace("TEST KEY", testFrontFace);
    assertSame(testFrontFace, theme.getFrontFace("TEST KEY"));
    theme.removeFrontFace("TEST KEY");
    assertTrue(theme.getImages().isEmpty());
  }

  @Test
  public void testParseTheme() {
    theme.setBackFace(new Image("/test_back.png"));
    theme.setThemeFile("/cardfaces/classicTest/theme.json");
    assertEquals(13, theme.getImages().size());
    assertEquals(130.0, theme.getFrontFace("10C").getWidth(), 0.0);
    assertEquals(180.0, theme.getFrontFace("QC").getHeight(), 0.0);
  }

  public static class TestApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
    }
  }
}
