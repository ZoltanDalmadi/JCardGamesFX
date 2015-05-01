package hu.unideb.inf.JCardGamesFX.klondike;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class KlondikeGameArea extends Pane {

  public KlondikeGameArea() {
  }

  public KlondikeGameArea(Image tableaouBackground) {

    setBackground(new Background(new BackgroundImage(tableaouBackground,
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
        BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
  }

  public void setTableaouBackground(Image tableaouBackground) {
    setBackground(new Background(new BackgroundImage(tableaouBackground,
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
        BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
  }
}
