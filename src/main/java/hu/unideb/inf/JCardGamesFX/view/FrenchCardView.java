package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.FrenchCard;
import javafx.scene.image.Image;

/**
 * This class provides a JavaFX based view for a {@link FrenchCard}.
 *
 * @author Zoltan Dalmadi
 */
public class FrenchCardView extends CardView {

  /**
   * Constructs a {@link FrenchCardView} object for the given
   * {@link FrenchCard} object with the {@link Image} object as a
   * representation in the game.
   *
   * @param card      The {@link FrenchCard} object to be represented.
   * @param frontFace The {@link Image} object for representing the card.
   */
  public FrenchCardView(FrenchCard card, Image frontFace) {
    super(card, frontFace);
  }

  /**
   * Constructs a {@link FrenchCardView} object for the given
   * {@link FrenchCard} object.
   *
   * @param card The {@link FrenchCard} object to be represented.
   */
  public FrenchCardView(FrenchCard card) {
    super(card);
  }

  /**
   * Constructs an empty FrenchCardView object.
   */
  public FrenchCardView() {
  }
}
