package hu.unideb.inf.JCardGamesFX.klondike;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class KlondikeStatusBar extends HBox {

  private Label statusText;
  private int seconds = 0;
  private int minutes = 0;

  public KlondikeStatusBar() {
    this.statusText = new Label();
    setPadding(new Insets(2));
    getChildren().add(statusText);

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateStatusText()));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }

  private void updateStatusText() {
    if (seconds > 59) {
      minutes++;
      seconds = 0;
    }

    String mins = "";

    if (minutes > 0)
      mins = minutes + " minutes ";

    statusText.setText("Elapsed time: " + mins + (seconds++) + " seconds");
  }

}
