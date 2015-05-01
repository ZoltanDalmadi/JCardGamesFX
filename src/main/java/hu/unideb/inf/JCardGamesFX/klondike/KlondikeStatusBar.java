package hu.unideb.inf.JCardGamesFX.klondike;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class KlondikeStatusBar extends HBox {
  public KlondikeStatusBar() {
    getChildren().add(new Label("Dummy Status Bar Text"));
    setPadding(new Insets(2));
  }
}
