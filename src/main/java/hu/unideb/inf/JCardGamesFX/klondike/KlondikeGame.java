package hu.unideb.inf.JCardGamesFX.klondike;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class KlondikeGame extends Application {

  private static final double WIDTH = 1280;
  private static final double HEIGHT = 720;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // middle part
    Pane root = new Pane();
    root.setBackground(new Background(new BackgroundFill(Color.OLIVEDRAB, null, null)));

    // top bar
    MenuBar menuBar = new MenuBar();

    // main element
    BorderPane bord = new BorderPane();
    bord.setCenter(root);
    bord.setTop(menuBar);

    // scene
    Scene scene = new Scene(bord, WIDTH, HEIGHT);

    primaryStage.setTitle("JavaFX Klondike");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
