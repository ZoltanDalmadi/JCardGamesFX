package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This abstract class provides a base for classes that implements various
 * JavaFX based views for {@link Card} objects.
 */
public class CardView extends ImageView {

  /**
   * This field holds the Image object that will be
   * the back face of this card.
   */
  private Image backFace;

  /**
   * This field holds the Image object that will be the front face
   * of this card.
   */
  private Image frontFace;

  /**
   * Constructs a {@link CardView} object with the two {@link Image} objects
   * as a representation in the game.
   *
   * @param frontFace The {@link Image} object for the front face.
   * @param backFace  The {@link Image} object for the back face.
   */
  public CardView(Image frontFace, Image backFace) {
    this.frontFace = frontFace;
    this.backFace = backFace;

    setImage(frontFace);
  }

  /**
   * Constructs an empty {@link CardView}.
   */
  public CardView() {
  }

  /**
   * Returns the {@link Image} object that holds the back face of this card.
   *
   * @return The back face {@link Image} object.
   */
  public Image getBackFace() {
    return backFace;
  }

  /**
   * Sets the {@link Image} object that holds the back face of this card.
   *
   * @param backFace The {@link Image} object to be set.
   */
  public void setBackFace(Image backFace) {
    this.backFace = backFace;
  }

  /**
   * Returns the {@link Image} object that holds the front face of this card.
   *
   * @return The back face {@link Image} object.
   */
  public Image getFrontFace() {
    return frontFace;
  }

  /**
   * Sets the {@link Image} object that holds the front face of this card.
   *
   * @param frontFace The {@link Image} object to be set.
   */
  public void setFrontFace(Image frontFace) {
    this.frontFace = frontFace;
  }

  /**
   * Swaps the displayed image of the card.
   */
  public void flip() {
    setImage(getImage().equals(frontFace) ? backFace : frontFace);
  }

}
