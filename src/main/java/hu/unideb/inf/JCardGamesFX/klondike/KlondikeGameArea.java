package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.view.CardPileView;
import hu.unideb.inf.JCardGamesFX.view.CardTheme;
import hu.unideb.inf.JCardGamesFX.view.CardView;
import javafx.collections.FXCollections;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * This class represents the area where the game is taking place.
 */
public class KlondikeGameArea extends Pane {

  /**
   * The list of {@link CardView} objects that are on the playing area.
   */
  List<CardView> cardViewList = new ArrayList<>();

  /**
   * The list of {@link CardPileView} objects that serves as the view for
   * the standard piles.
   */
  private List<CardPileView> standardPileViews;

  /**
   * The list of {@link CardPileView} objects that serves as the view for
   * the foundation piles.
   */
  private List<CardPileView> foundationPileViews;

  /**
   * The {@link CardPileView} object that serves as the view for the stock.
   */
  private CardPileView stockView;

  /**
   * The {@link CardPileView} object that serves as the view for the waste.
   */
  private CardPileView wasteView;

  /**
   * The gap (offset) between the cards.
   */
  private double cardGap = 30;

  /**
   * Constructs a new {@link KlondikeGameArea} object.
   */
  public KlondikeGameArea() {
    this.standardPileViews = FXCollections.observableArrayList();
    this.foundationPileViews = FXCollections.observableArrayList();
    this.stockView = new CardPileView(1, "S");
    this.wasteView = new CardPileView(1, "W");
    initGameArea();
  }

  /**
   * Constructs a new {@link KlondikeGameArea} object, with the given image
   * as the background.
   *
   * @param tableauBackground The {@link Image} object for the background.
   */
  public KlondikeGameArea(Image tableauBackground) {
    this();
    setTableauBackground(tableauBackground);
  }

  /**
   * Initializes the game area.
   */
  private void initGameArea() {
    buildStock();
    buildWaste();
    buildFoundationPiles();
    buildStandardPiles();
  }

  /**
   * Configures the {@link CardPileView} object that serves as the view
   * of the stock.
   */
  private void buildStock() {
    BackgroundFill backgroundFill = new BackgroundFill(
        Color.gray(0.0, 0.2), null, null);

    Background background = new Background(backgroundFill);

    GaussianBlur gaussianBlur = new GaussianBlur(10);

    stockView.setPrefSize(130, 180);
    stockView.setBackground(background);
    stockView.setLayoutX(95);
    stockView.setLayoutY(20);
    stockView.setEffect(gaussianBlur);
    getChildren().add(stockView);
  }

  /**
   * Configures the {@link CardPileView} object that serves as the view
   * of the waste.
   */
  private void buildWaste() {
    BackgroundFill backgroundFill = new BackgroundFill(
        Color.gray(0.0, 0.2), null, null);

    Background background = new Background(backgroundFill);

    GaussianBlur gaussianBlur = new GaussianBlur(10);

    wasteView.setPrefSize(130, 180);
    wasteView.setBackground(background);
    wasteView.setLayoutX(255);
    wasteView.setLayoutY(20);
    wasteView.setEffect(gaussianBlur);
    getChildren().add(wasteView);
  }

  /**
   * Configures the {@link CardPileView} objects that serves as the view
   * of the foundation piles.
   */
  private void buildFoundationPiles() {
    BackgroundFill backgroundFill = new BackgroundFill(
        Color.gray(0.0, 0.2), null, null);

    Background background = new Background(backgroundFill);

    GaussianBlur gaussianBlur = new GaussianBlur(10);

    IntStream.range(0, 4).forEach(i -> {
      foundationPileViews.add(new CardPileView(0, "F" + i));
      foundationPileViews.get(i).setPrefSize(130, 180);
      foundationPileViews.get(i).setBackground(background);
      foundationPileViews.get(i).setLayoutX(575 + i * 160);
      foundationPileViews.get(i).setLayoutY(20);
      foundationPileViews.get(i).setEffect(gaussianBlur);
      getChildren().add(foundationPileViews.get(i));
    });
  }

  /**
   * Configures the {@link CardPileView} objects that serves as the view
   * of the standard piles.
   */
  private void buildStandardPiles() {
    BackgroundFill backgroundFill = new BackgroundFill(
        Color.gray(0.0, 0.2), null, null);

    Background background = new Background(backgroundFill);

    GaussianBlur gaussianBlur = new GaussianBlur(10);

    IntStream.range(0, 7).forEach(i -> {
      standardPileViews.add(new CardPileView(cardGap, "K" + i));
      standardPileViews.get(i).setPrefSize(130, 180);
      standardPileViews.get(i).setBackground(background);
      standardPileViews.get(i).setLayoutX(95 + i * 160);
      standardPileViews.get(i).setLayoutY(240);
      standardPileViews.get(i).setEffect(gaussianBlur);
      getChildren().add(standardPileViews.get(i));
    });
  }

  /**
   * Returns the {@link List} of {@link CardPileView} objects that serves
   * as the view of the standard piles.
   *
   * @return The {@link List} of {@link CardPileView} objects.
   */
  public List<CardPileView> getStandardPileViews() {
    return standardPileViews;
  }

  /**
   * Returns the {@link List} of {@link CardPileView} objects that serves
   * as the view of the foundation piles.
   *
   * @return The {@link List} of {@link CardPileView} objects.
   */
  public List<CardPileView> getFoundationPileViews() {
    return foundationPileViews;
  }

  /**
   * Returns the {@link CardPileView} object that serves as the view
   * of the stock.
   *
   * @return The {@link CardPileView} object.
   */
  public CardPileView getStockView() {
    return stockView;
  }

  /**
   * Returns the {@link CardPileView} object that serves as the view
   * of the waste.
   *
   * @return The {@link CardPileView} object.
   */
  public CardPileView getWasteView() {
    return wasteView;
  }

  /**
   * Sets the background image for the tableau.
   *
   * @param tableauBackground The {@link Image} object to set.
   */
  public void setTableauBackground(Image tableauBackground) {
    setBackground(new Background(new BackgroundImage(tableauBackground,
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
        BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
  }

  /**
   * Updates the card images of each card on the game area.
   * Mainly used when switching card themes.
   *
   * @param cardTheme The current card theme to update from.
   */
  public void updateCardViews(CardTheme cardTheme) {
    cardViewList.forEach(cardView -> {
      cardView.setFrontFace(cardTheme.getFrontFace(cardView.getShortID()));
      cardView.setBackFace(cardTheme.getBackFace());
    });
  }
}
