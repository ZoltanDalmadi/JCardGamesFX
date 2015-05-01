package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.view.CardTheme;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class KlondikeApp extends Application {

  private static final double WIDTH = 1280;
  private static final double HEIGHT = 720;

  private static CardTheme cardTheme;
//  private static List<CardView> cardViews;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    // Game area
    KlondikeGameArea gameArea =
        new KlondikeGameArea(new Image("/tableaous/green-felt.png"));

    // Menu bar
    KlondikeMenu menuBar = new KlondikeMenu();

    // Status bar
    KlondikeStatusBar statusBar = new KlondikeStatusBar();

    // Main element
    BorderPane bord = new BorderPane();
    bord.setCenter(gameArea);
    bord.setTop(menuBar);
    bord.setBottom(statusBar);

    Scene scene = new Scene(bord, WIDTH, HEIGHT);

//    cardViews = FXCollections.observableArrayList();

//    try {
//      cardTheme = new CardTheme("/cardfaces/classic/theme.json", "/backfaces/bb.png");
//    } catch (IOException | ParseException e) {
//      e.printStackTrace(System.err);
//      primaryStage.close();
//    }

//    CardViewFactory.setCardTheme(cardTheme);

//    deck.forEach(card -> cardViews.add(CardViewFactory.createCardView(card)));

    KlondikeGame game = new KlondikeGame();
    game.startNewGame();

    primaryStage.setTitle("JavaFX Klondike");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
