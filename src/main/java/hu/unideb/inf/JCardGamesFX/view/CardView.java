package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This abstract class provides a base for classes that implements various
 * JavaFX based views for {@link Card} objects.
 */
public class CardView implements CardObserver {

  /**
   * This field holds the Image object that will be
   * the back face of this card.
   */
  private Image backFace;

  /**
   * The {@link Card} object to be represented.
   */
  private Card card;

  /**
   * This field holds the Image object that will be the front face
   * of this card.
   */
  private Image frontFace;

  /**
   * This field is responsible for actually displaying
   * the {@link Image} objects.
   */
  private ImageView display = new ImageView();

  /**
   * Constructs a {@link CardView} object for the given {@link Card} object
   * with the {@link Image} object as a representation in the game.
   *
   * @param card      The {@link Card} object to be represented.
   * @param frontFace The {@link Image} object for representing the card.
   * @param backFace  The {@link Image} object for the back face.
   */
  public CardView(Card card, Image frontFace, Image backFace) {
    this.card = card;
    this.frontFace = frontFace;
    this.backFace = backFace;
  }

  /**
   * Constructs a {@link CardView} object for the given {@link Card} object.
   *
   * @param card The {@link Card} object to be represented.
   */
  public CardView(Card card) {
    this.card = card;
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
   * Returns the {@link Card} object that this object represents.
   *
   * @return The {@link Card} object.
   */
  public Card getCard() {
    return card;
  }

  /**
   * Sets the {@link Card} object that this object represents.
   *
   * @param card The {@link Card} object to be represented.
   */
  public void setCard(Card card) {
    this.card = card;
  }

  /**
   * Returns the {@link ImageView} object that displays the card face images.
   *
   * @return The {@link ImageView} object.
   */
  public ImageView getDisplay() {
    return display;
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
   * Sets the current {@link Image} object for the <code>display</code> field.
   */
  @Override
  public void update() {
    display.setImage(card.isFaceDown() ? backFace : frontFace);
  }

}
