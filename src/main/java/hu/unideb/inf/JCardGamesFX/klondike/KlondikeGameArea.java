package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.view.CardPileView;
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

import java.util.List;
import java.util.stream.IntStream;

public class KlondikeGameArea extends Pane {

  private List<CardPileView> standardPileViews;
  private List<CardPileView> foundationPileViews;
  private CardPileView stockView;
  private CardPileView wasteView;
  private double cardGap = 30;

  public KlondikeGameArea() {
    this.standardPileViews = FXCollections.observableArrayList();
    this.foundationPileViews = FXCollections.observableArrayList();
    this.stockView = new CardPileView(1, "S");
    this.wasteView = new CardPileView(1, "W");
    initGameArea();
  }

  public KlondikeGameArea(Image tableauBackground) {
    this();
    setTableauBackground(tableauBackground);
  }

  private void initGameArea() {
    buildStock();
    buildWaste();
    buildFoundationPiles();
    buildStandardPiles();
  }

  private void buildStock() {
    BackgroundFill backgroundFill = new BackgroundFill(
        Color.gray(0.0, 0.2), null, null);

    Background background = new Background(backgroundFill);

    GaussianBlur gaussianBlur = new GaussianBlur(10);

    stockView.setPrefSize(130, 180);
    stockView.setBackground(background);
    stockView.setLayoutX(80);
    stockView.setLayoutY(20);
    stockView.setEffect(gaussianBlur);
    getChildren().add(stockView);
  }

  private void buildWaste() {
    BackgroundFill backgroundFill = new BackgroundFill(
        Color.gray(0.0, 0.2), null, null);

    Background background = new Background(backgroundFill);

    GaussianBlur gaussianBlur = new GaussianBlur(10);

    wasteView.setPrefSize(130, 180);
    wasteView.setBackground(background);
    wasteView.setLayoutX(240);
    wasteView.setLayoutY(20);
    wasteView.setEffect(gaussianBlur);
    getChildren().add(wasteView);
  }

  private void buildFoundationPiles() {
    BackgroundFill backgroundFill = new BackgroundFill(
        Color.gray(0.0, 0.2), null, null);

    Background background = new Background(backgroundFill);

    GaussianBlur gaussianBlur = new GaussianBlur(10);

    IntStream.range(0, 4).forEach(i -> {
      foundationPileViews.add(new CardPileView(cardGap, "F" + i));
      foundationPileViews.get(i).setPrefSize(130, 180);
      foundationPileViews.get(i).setBackground(background);
      foundationPileViews.get(i).setLayoutX(560 + i * 160);
      foundationPileViews.get(i).setLayoutY(20);
      foundationPileViews.get(i).setEffect(gaussianBlur);
      getChildren().add(foundationPileViews.get(i));
    });
  }

  private void buildStandardPiles() {
    BackgroundFill backgroundFill = new BackgroundFill(
        Color.gray(0.0, 0.2), null, null);

    Background background = new Background(backgroundFill);

    GaussianBlur gaussianBlur = new GaussianBlur(10);

    IntStream.range(0, 7).forEach(i -> {
      standardPileViews.add(new CardPileView(cardGap, "K" + i));
      standardPileViews.get(i).setPrefSize(130, 180);
      standardPileViews.get(i).setBackground(background);
      standardPileViews.get(i).setLayoutX(80 + i * 160);
      standardPileViews.get(i).setLayoutY(240);
      standardPileViews.get(i).setEffect(gaussianBlur);
      getChildren().add(standardPileViews.get(i));
    });
  }

  public double getCardGap() {
    return cardGap;
  }

  public void setCardGap(double cardGap) {
    this.cardGap = cardGap;
  }

  public List<CardPileView> getStandardPileViews() {
    return standardPileViews;
  }

  public List<CardPileView> getFoundationPileViews() {
    return foundationPileViews;
  }

  public CardPileView getStockView() {
    return stockView;
  }

  public CardPileView getWasteView() {
    return wasteView;
  }

  public void setTableauBackground(Image tableauBackground) {
    setBackground(new Background(new BackgroundImage(tableauBackground,
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
        BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
  }

}
