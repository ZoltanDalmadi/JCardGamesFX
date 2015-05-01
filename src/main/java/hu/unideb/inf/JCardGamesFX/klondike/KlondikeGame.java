package hu.unideb.inf.JCardGamesFX.klondike;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class KlondikeGame extends Application {

  private static final double WIDTH = 1280;
  private static final double HEIGHT = 720;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // Game area
    Pane gameArea = new Pane();

    Image tableaouBackground = new Image("/tableaous/green-felt.png");

    gameArea.setBackground(new Background(new BackgroundImage(tableaouBackground,
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, null, null)));

    // TODO: change to actual menubar class
    MenuBar menuBar = new MenuBar(new Menu("Dummy Menu"));

    // TODO: change to actual status bar class
    HBox statusBar = new HBox(20, new Label("Dummy status bar text"));
    statusBar.setPadding(new Insets(2));

    // main element
    BorderPane bord = new BorderPane();
    bord.setCenter(gameArea);
    bord.setTop(menuBar);
    bord.setBottom(statusBar);

    // scene
    Scene scene = new Scene(bord, WIDTH, HEIGHT);

    primaryStage.setTitle("JavaFX Klondike");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
