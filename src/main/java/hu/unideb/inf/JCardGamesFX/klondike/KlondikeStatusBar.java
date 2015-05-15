package hu.unideb.inf.JCardGamesFX.klondike;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * This class represents the status bar for the application.
 */
public class KlondikeStatusBar extends HBox {

  /**
   * Label which displays elapsed time.
   */
  private Label elapsedTimeText;

  /**
   * Helper variable for time.
   */
  private int seconds = 0;

  /**
   * Same.
   */
  private int minutes = 0;

  /**
   * Constructs a new {@link KlondikeStatusBar} object.
   */
  public KlondikeStatusBar() {
    this.elapsedTimeText = new Label();
    setPadding(new Insets(2));

    getChildren().add(elapsedTimeText);

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateStatusText()));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }

  /**
   * Updates the status text periodically with the elapsed time.
   */
  private void updateStatusText() {
    if (seconds > 59) {
      minutes++;
      seconds = 0;
    }

    String mins = "";

    if (minutes > 0)
      mins = minutes + " minutes ";

    elapsedTimeText.setText("Elapsed time: " + mins + (seconds++) + " seconds");
  }

}
