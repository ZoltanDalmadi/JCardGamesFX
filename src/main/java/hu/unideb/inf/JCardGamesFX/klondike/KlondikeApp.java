package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.model.Card;
import hu.unideb.inf.JCardGamesFX.model.CardPile;
import hu.unideb.inf.JCardGamesFX.view.CardPileView;
import hu.unideb.inf.JCardGamesFX.view.CardTheme;
import hu.unideb.inf.JCardGamesFX.view.CardView;
import hu.unideb.inf.JCardGamesFX.view.CardViewFactory;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;
import java.util.stream.IntStream;

public class KlondikeApp extends Application {

  private static final double WIDTH = 1280;
  private static final double HEIGHT = 800;

  private KlondikeGame game;
  private KlondikeGameArea gameArea;
  private KlondikeMouseUtil mouseUtil;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    // Game area
    gameArea = new KlondikeGameArea(new Image("/tableaous/green-felt.png"));

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

    try {
      CardViewFactory.setCardTheme(new CardTheme("/cardfaces/classic/theme.json", "/backfaces/bb.png"));
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }

    game = new KlondikeGame();
    game.startNewGame();
    mouseUtil = new KlondikeMouseUtil(game, gameArea);
    prepareGameAreaForNewGame();

    // auto-flip cards
    IntStream.range(0, game.getStandardPiles().size()).forEach(i -> {
      CardPileView actPileView = gameArea.getStandardPileViews().get(i);
      CardPile actPile = game.getPileById(actPileView.getShortID());

      actPileView.getCards().addListener(
          (ListChangeListener<CardView>) c -> {
            while (c.next()) {
              if (c.wasRemoved()) {
                if (!actPileView.isEmpty()) {
                  CardView toFlip = actPileView.getTopCardView();
                  toFlip.setMouseTransparent(false);
                  if (!toFlip.isFaceDown())
                    toFlip.flip();
                }
                if (!actPile.isEmpty())
                  actPile.getTopCard().flip();
              }
            }
          });
    });


    primaryStage.setTitle("JavaFX Klondike");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void prepareGameAreaForNewGame() {
    // deal to piles
    Iterator<Card> deckIterator = game.getDeck().iterator();

    int cardsToPut = 1;

    for (CardPileView standardPileView : gameArea.getStandardPileViews()) {
      for (int i = 0; i < cardsToPut; i++) {
        standardPileView.addCardView(CardViewFactory.createCardView(deckIterator.next()));
        gameArea.getChildren().add(standardPileView.getTopCardView());
        mouseUtil.makeDraggable(standardPileView.getTopCardView());
        standardPileView.getTopCardView().setMouseTransparent(true);
      }

      standardPileView.getTopCardView().flip();
      standardPileView.getTopCardView().setMouseTransparent(false);
      cardsToPut++;
    }

    deckIterator.forEachRemaining(card -> {
      gameArea.getStockView().addCardView(CardViewFactory.createCardView(card));
      gameArea.getChildren().add(gameArea.getStockView().getTopCardView());
    });
  }

}
