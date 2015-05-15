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

import java.util.Iterator;

/**
 * This class serves as the entry point of the application.
 */
public class KlondikeApp extends Application {

  /**
   * Width of the main window.
   */
  private static final double WIDTH = 1280;

  /**
   * Height of the main window.
   */
  private static final double HEIGHT = 800;

  /**
   * Reference to a {@link KlondikeGame} instance.
   */
  KlondikeGame game;

  /**
   * Reference to a {@link KlondikeGameArea} instance.
   */
  KlondikeGameArea gameArea;

  /**
   * Reference to a {@link KlondikeMouseUtil} instance.
   */
  KlondikeMouseUtil mouseUtil;

  /**
   * The current theme of the cards.
   */
  CardTheme cardTheme;

  /**
   * Main function of the application.
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * The main function effectively launches this method, as in all
   * JavaFX applications.
   *
   * @param primaryStage Reference to the main {@link Stage} object.
   */
  @Override
  public void start(Stage primaryStage) {

    // Game area
    gameArea = new KlondikeGameArea(new Image("/tableaous/green-felt.png"));

    // Menu bar
    KlondikeMenu menuBar = new KlondikeMenu(this);

    // Status bar
    KlondikeStatusBar statusBar = new KlondikeStatusBar();

    // Main element
    BorderPane bord = new BorderPane();
    bord.setCenter(gameArea);
    bord.setTop(menuBar);
    bord.setBottom(statusBar);

    Scene scene = new Scene(bord, WIDTH, HEIGHT);

    cardTheme = new CardTheme("/cardfaces/classic/theme.json", "/backfaces/bb.png");
    CardViewFactory.setCardTheme(cardTheme);

    game = new KlondikeGame();
    game.startNewGame();
    mouseUtil = new KlondikeMouseUtil(game, gameArea);
    prepareGameAreaForNewGame();

    // auto-flip cards
    for (int i = 0; i < game.getStandardPiles().size(); i++) {
      CardPileView actPileView = gameArea.getStandardPileViews().get(i);
      CardPile actPile = game.getPileById(actPileView.getShortID());

      actPileView.getCards().addListener(
          (ListChangeListener<CardView>) c -> {
            while (c.next()) {
              if (c.wasRemoved()) {
                if (!actPileView.isEmpty()) {
                  CardView toFlip = actPileView.getTopCardView();
                  toFlip.setMouseTransparent(false);
                  if (toFlip.isFaceDown())
                    toFlip.flip();
                }

                if (!actPile.isEmpty() && actPile.getTopCard().isFaceDown())
                  actPile.getTopCard().flip();
              }
            }
          });
    }

    primaryStage.setTitle("JavaFX Klondike");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Sets up the {@link KlondikeGameArea} object for a new game.
   */
  private void prepareGameAreaForNewGame() {
    // deal to piles
    Iterator<Card> deckIterator = game.getDeck().iterator();

    int cardsToPut = 1;

    for (CardPileView standardPileView : gameArea.getStandardPileViews()) {
      for (int i = 0; i < cardsToPut; i++) {
        standardPileView.addCardView(CardViewFactory.createCardView(deckIterator.next()));
        gameArea.getChildren().add(standardPileView.getTopCardView());
        gameArea.cardViewList.add(standardPileView.getTopCardView());
        mouseUtil.makeDraggable(standardPileView.getTopCardView());
        standardPileView.getTopCardView().setMouseTransparent(true);
      }

      standardPileView.getTopCardView().setMouseTransparent(false);
      cardsToPut++;
    }

    deckIterator.forEachRemaining(card -> {
      gameArea.getStockView().addCardView(CardViewFactory.createCardView(card));
      mouseUtil.makeClickable(gameArea.getStockView().getTopCardView());
      gameArea.cardViewList.add(gameArea.getStockView().getTopCardView());
      gameArea.getChildren().add(gameArea.getStockView().getTopCardView());
    });

    gameArea.getStockView().setOnMouseClicked(mouseUtil.stockReverseCardsHandler);
  }

}
