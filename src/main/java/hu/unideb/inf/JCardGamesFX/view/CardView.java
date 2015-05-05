package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.Card;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

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
   * Short identifier.
   */
  private String shortID;

  /**
   * Reference to the {@link CardPileView} that is currently containing this
   * {@link CardView}.
   */
  private CardPileView containingPile;

  /**
   * Whether the {@link CardView} is facing down.
   */
  private boolean faceDown;

  /**
   * Drop shadow effect of the card.
   */
  private DropShadow dropShadow;

  /**
   * Constructs a {@link CardView} object with the two {@link Image} objects
   * as a representation in the game.
   *
   * @param faceDown  Whether the card is facing down.
   * @param frontFace The {@link Image} object for the front face.
   * @param backFace  The {@link Image} object for the back face.
   * @param shortID   The short identifier.
   */
  public CardView(boolean faceDown, Image frontFace, Image backFace, String shortID) {
    this(faceDown);
    this.frontFace = frontFace;
    this.backFace = backFace;
    this.shortID = shortID;
    setImage(backFace);
  }

  /**
   * Constructs an empty {@link CardView}.
   *
   * @param faceDown Whether the card is facing down.
   */
  public CardView(boolean faceDown) {
    this.dropShadow = new DropShadow(2, Color.gray(0, 0.75));
    this.faceDown = faceDown;
    setEffect(dropShadow);
  }

  /**
   * Returns the {@link DropShadow} object associated with this card.
   *
   * @return The {@link DropShadow} object.
   */
  public DropShadow getDropShadow() {
    return dropShadow;
  }

  /**
   * Returns whether the {@link CardView} is facing down.
   *
   * @return true if the {@link CardView} is facing down, false otherwise.
   */
  public boolean isFaceDown() {
    return faceDown;
  }

  /**
   * Returns the short identifier for the card that this view represents.
   *
   * @return The short identifier.
   */
  public String getShortID() {
    return shortID;
  }

  /**
   * Sets the short identifier.
   *
   * @param shortID The new short identifier.
   */
  public void setShortID(String shortID) {
    this.shortID = shortID;
  }

  /**
   * Returns the {@link CardPileView} object currently containing this card.
   *
   * @return the {@link CardPileView} object currently containing this card.
   */
  public CardPileView getContainingPile() {
    return containingPile;
  }

  /**
   * Sets the {@link CardPileView} object that will contain this card.
   *
   * @param containingPile the {@link CardPileView} object that will contain
   *                       this card.
   */
  public void setContainingPile(CardPileView containingPile) {
    this.containingPile = containingPile;
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
    if (faceDown)
      setImage(this.backFace);
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
    if (!faceDown)
      setImage(this.frontFace);
  }

  /**
   * Swaps the displayed image of the card
   * and negates the field <code>faceDown</code>.
   */
  public void flip() {
    faceDown = !faceDown;
    setImage(faceDown ? backFace : frontFace);
  }

}
