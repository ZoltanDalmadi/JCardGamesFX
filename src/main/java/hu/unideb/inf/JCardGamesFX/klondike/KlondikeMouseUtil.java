package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.model.Card;
import hu.unideb.inf.JCardGamesFX.model.CardPile;
import hu.unideb.inf.JCardGamesFX.view.CardPileView;
import hu.unideb.inf.JCardGamesFX.view.CardView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.List;
import java.util.ListIterator;

/**
 * This class serves as the controller for the application (sort-of).
 */
public class KlondikeMouseUtil {

  /**
   * Helper inner class for determining the position of the mouse.
   */
  private final MousePos mousePos = new MousePos();

  /**
   * Dragged cards will be put into this list.
   */
  private List<Card> draggedCards;

  /**
   * Same for the view of the cards.
   */
  private List<CardView> draggedCardViews;

  /**
   * The {@link KlondikeGame} object to manipulate.
   */
  private KlondikeGame game;

  /**
   * The {@link KlondikeGameArea} object to manipulate.
   */
  private KlondikeGameArea gameArea;

  /**
   * This event handler is attached to cards that are still on the stock.
   * When the user clicks on a card, it will be flipped and put on the waste.
   */
  EventHandler<MouseEvent> onMouseClickedHandler = e -> {
    // put card from stock to waste and flip them
    /** get current cardView. */
    CardView cardView = (CardView) e.getSource();
    /** get current card. */
    Card card = game.getDeck().getById(cardView.getShortID());

    game.drawFromStock(card);
    gameArea.getStockView().moveCardViewToPile(cardView, gameArea.getWasteView());
    cardView.flip();
    cardView.setMouseTransparent(false);
    makeDraggable(cardView);
    KlondikeApp.LOG.info("Placed {} to the waste.", card);
  };

  /**
   * This event handler is attached to the stock itself.
   * It puts all cards on the waste back on the stock.
   */
  EventHandler<MouseEvent> stockReverseCardsHandler = e -> {
    // put all cards on waste back to stock and flip them
    game.refillStockFromWaste();

    /** get view for waste. */
    CardPileView wasteView = gameArea.getWasteView();

    /** get view for stock. */
    CardPileView stockView = gameArea.getStockView();

    /** reverse iterator for list. */
    ListIterator<CardView> revIt = wasteView.getCards().listIterator(wasteView.numOfCards());

    while (revIt.hasPrevious()) {
      /** get current card view. */
      CardView currentCardView = revIt.previous();
      currentCardView.flip();
      makeClickable(currentCardView);
      stockView.addCardView(currentCardView);
      revIt.remove();
    }

    KlondikeApp.LOG.info("Refilled stock from waste.");
  };

  /**
   * This event handler is attached to all the cards that are not on the stock.
   * Stores the position where the user clicked.
   */
  EventHandler<MouseEvent> onMousePressedHandler = e -> {
    // Store mouse click position
    mousePos.x = e.getSceneX();
    mousePos.y = e.getSceneY();
  };

  /**
   * This event handler is attached to all the cards that are not on the stock.
   * Handles the card movements, applies a drop shadow effect, and others.
   */
  EventHandler<MouseEvent> onMouseDraggedHandler = e -> {
    // Calculate difference vector from clicked point
    /** x component. */
    double offsetX = e.getSceneX() - mousePos.x;
    /** y component. */
    double offsetY = e.getSceneY() - mousePos.y;

    // Get the actual card
    /** get current cardView. */
    CardView cardView = (CardView) e.getSource();
    /** get current card. */
    Card card = game.getDeck().getById(cardView.getShortID());

    // Setup drop shadow
    cardView.getDropShadow().setRadius(20);
    cardView.getDropShadow().setOffsetX(10);
    cardView.getDropShadow().setOffsetY(10);

    // Get the pile that contained the actual card
    /** get current pile view. */
    CardPileView activePileView = cardView.getContainingPile();
    /** get current pile. */
    CardPile activePile = game.getPileById(activePileView.getShortID());

    // Put this card and all above it to the list of dragged cards
    draggedCardViews = activePileView.cardViewsAbove(cardView);
    draggedCards = activePile.cardsAbove(card);

    // Bring them to front & apply difference vector to dragged cards
    draggedCardViews.forEach(cw -> {
      cw.toFront();
      cw.setTranslateX(offsetX);
      cw.setTranslateY(offsetY);
    });
  };

  /**
   * This event handler is attached to all the cards that are not on the stock.
   * Decides if the move is valid, and acts appropriately.
   */
  EventHandler<MouseEvent> onMouseReleasedHandler = e -> {
    // if no cards are dragged, return immediately
    if (draggedCards == null && draggedCardViews == null)
      return;

    // Get the actual card
    /** get current card view. */
    CardView cardView = (CardView) e.getSource();
    /** get current card. */
    Card card = game.getDeck().getById(cardView.getShortID());

    // Get the pile that contained the actual card
    /** get current pile view. */
    CardPileView activePileView = cardView.getContainingPile();
    /** get current pile. */
    CardPile activePile = game.getPileById(activePileView.getShortID());

    // check if card(s) are intersecting with any of the piles
    if (checkAllPiles(card, cardView, activePile, activePileView)) {
      if (game.isGameWon()) {
        KlondikeApp.LOG.info("The game has been won.");

        /** Alert dialog box informing the player that he/she has won. */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("JCardGamesFX");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations, you have won the game!");
        alert.showAndWait();
      }

      return;
    }

    // if not intersecting with any valid pile, slide them back
    draggedCardViews.forEach(this::slideBack);

    // throw away dragged cards info
    draggedCards = null;
    draggedCardViews = null;
  };

  /**
   * Constructs a {@link KlondikeMouseUtil} object for the given
   * {@link KlondikeGame} and {@link KlondikeGameArea} objects.
   *
   * @param game     The {@link KlondikeGame object}.
   * @param gameArea The {@link KlondikeGameArea} object.
   */
  public KlondikeMouseUtil(KlondikeGame game, KlondikeGameArea gameArea) {
    this.game = game;
    this.gameArea = gameArea;
  }

  /**
   * Applies the appropriate event handlers for cards not on the stock.
   *
   * @param card The {@link CardView} to apply event listeners for.
   */
  public void makeDraggable(CardView card) {
    card.setOnMouseClicked(null);
    card.setOnMousePressed(onMousePressedHandler);
    card.setOnMouseDragged(onMouseDraggedHandler);
    card.setOnMouseReleased(onMouseReleasedHandler);
  }

  /**
   * Applies the appropriate event handlers for cards on the stock.
   *
   * @param card The {@link CardView} to apply event listeners for.
   */
  public void makeClickable(CardView card) {
    card.setOnMousePressed(null);
    card.setOnMouseDragged(null);
    card.setOnMouseReleased(null);
    card.setOnMouseClicked(onMouseClickedHandler);
  }

  /**
   * Check if the actual card is intersecting with any of the piles.
   *
   * @param card           The card to check.
   * @param cardView       The view of the card.
   * @param activePile     The pile the card is currently in.
   * @param activePileView The view for the pile.
   * @return true if intersects with any pile, false otherwise.
   */
  private boolean checkAllPiles(
      Card card, CardView cardView, CardPile activePile,
      CardPileView activePileView) {

    // check the standard piles
    if (checkPiles(card, cardView, activePile,
        activePileView, gameArea.getStandardPileViews()))
      return true;

    // check the foundation piles
    return checkPiles(card, cardView, activePile,
        activePileView, gameArea.getFoundationPileViews());
  }

  /**
   * Check a list of pile views for card intersection.
   *
   * @param card           The card to check.
   * @param cardView       The view for the card.
   * @param activePile     The pile the card is currently in.
   * @param activePileView The view for the pile.
   * @param pileViews      The list of piles to check.
   * @return true if intersects with any pile, false otherwise.
   */
  private boolean checkPiles(
      Card card, CardView cardView, CardPile activePile,
      CardPileView activePileView, List<CardPileView> pileViews) {

    boolean result = false;

    for (CardPileView pileView : pileViews) {
      // skip checking the same pile
      if (pileView.equals(activePileView))
        continue;

      // check for intersection
      if (isOverPile(cardView, pileView) &&
          handleValidMove(card, activePile, activePileView, pileView))
        result = true;
    }

    return result;
  }

  /**
   * Checks if a cardView is over a pile.
   *
   * @param cardView The cardView to check.
   * @param pileView The pile to check.
   * @return true if the card is over the pile, false otherwise.
   */
  private boolean isOverPile(CardView cardView, CardPileView pileView) {
    if (pileView.isEmpty())
      return cardView.getBoundsInParent().intersects(pileView.getBoundsInParent());
    else
      return cardView.getBoundsInParent().intersects(pileView.getTopCardView().getBoundsInParent());
  }

  /**
   * Handles a move. If valid, move the model cards to the destination pile,
   * as well as their views.
   *
   * @param card           The card to move.
   * @param sourcePile     The view of the moved card.
   * @param sourcePileView The source pile view.
   * @param destPileView   The destination pile view.
   * @return true if the move is valid, false otherwise.
   */
  private boolean handleValidMove(Card card, CardPile sourcePile,
                                  CardPileView sourcePileView,
                                  CardPileView destPileView) {
    CardPile destPile = game.getPileById(destPileView.getShortID());

    if (game.getRules().isMoveValid(card, destPile)) {
      String msg = null;

      if (destPile.isEmpty()) {
        if (destPile.getType().equals(CardPile.Type.Foundation))
          msg = String.format("Placed %s to the foundation.", card);

        if (destPile.getType().equals(CardPile.Type.Klondike))
          msg = String.format("Placed %s to a new pile.", card);
      } else {
        msg = String.format("Placed %s to %s.",
            card, destPile.getTopCard());
      }

      KlondikeApp.LOG.info(msg);

      game.moveCards(draggedCards, sourcePile, destPile);
      slideToPile(draggedCardViews, sourcePileView, destPileView);
      draggedCards = null;
      draggedCardViews = null;
      return true;
    } else {
      String msg = null;

      if (destPile.isEmpty()) {
        if (destPile.getType().equals(CardPile.Type.Foundation))
          msg = String.format("%s is not an Ace!", card);

        if (destPile.getType().equals(CardPile.Type.Klondike))
          msg = String.format("%s is not a King!", card);

      } else {
        msg = String.format("Cannot place %s to %s.", card, destPile.getTopCard());
      }

      KlondikeApp.LOG.warn(msg);

      return false;
    }
  }

  /**
   * Slide back card to its original position if the move was not valid.
   *
   * @param card The card view to slide back.
   */
  private void slideBack(CardView card) {
    double sourceX = card.getLayoutX() + card.getTranslateX();
    double sourceY = card.getLayoutY() + card.getTranslateY();

    double targetX = card.getLayoutX();
    double targetY = card.getLayoutY();

    animateCardMovement(card, sourceX, sourceY,
        targetX, targetY, Duration.millis(150), e -> {
          card.getDropShadow().setRadius(2);
          card.getDropShadow().setOffsetX(0);
          card.getDropShadow().setOffsetY(0);
        });
  }

  /**
   * Slides the list of dragged cards from the source pile to the destination
   * pile.
   *
   * @param cardsToSlide The list of dragged cards.
   * @param sourcePile   The source pile.
   * @param destPile     The destination pile.
   */
  private void slideToPile(List<CardView> cardsToSlide, CardPileView sourcePile,
                           CardPileView destPile) {
    if (cardsToSlide == null)
      return;

    double destCardGap = destPile.getCardGap();

    double targetX;
    double targetY;

    if (destPile.isEmpty()) {
      targetX = destPile.getLayoutX();
      targetY = destPile.getLayoutY();
    } else {
      targetX = destPile.getTopCardView().getLayoutX();
      targetY = destPile.getTopCardView().getLayoutY();
    }

    for (int i = 0; i < cardsToSlide.size(); i++) {
      CardView currentCardView = cardsToSlide.get(i);
      double sourceX =
          currentCardView.getLayoutX() + currentCardView.getTranslateX();
      double sourceY =
          currentCardView.getLayoutY() + currentCardView.getTranslateY();

      animateCardMovement(currentCardView, sourceX, sourceY, targetX,
          targetY + ((destPile.isEmpty() ? i : i + 1) * destCardGap),
          Duration.millis(150),
          e -> {
            sourcePile.moveCardViewToPile(currentCardView, destPile);
            currentCardView.getDropShadow().setRadius(2);
            currentCardView.getDropShadow().setOffsetX(0);
            currentCardView.getDropShadow().setOffsetY(0);
          });
    }
  }

  /**
   * Animates card movements.
   *
   * @param card     The card view to animate.
   * @param sourceX  Source X coordinate of the card view.
   * @param sourceY  Source Y coordinate of the card view.
   * @param targetX  Destination X coordinate of the card view.
   * @param targetY  Destination Y coordinate of the card view.
   * @param duration The duration of the animation.
   * @param doAfter  The action to perform after the animation has been completed.
   */
  private void animateCardMovement(
      CardView card, double sourceX, double sourceY,
      double targetX, double targetY, Duration duration,
      EventHandler<ActionEvent> doAfter) {

    Path path = new Path();
    path.getElements().add(new MoveToAbs(card, sourceX, sourceY));
    path.getElements().add(new LineToAbs(card, targetX, targetY));

    PathTransition pathTransition =
        new PathTransition(duration, path, card);
    pathTransition.setInterpolator(Interpolator.EASE_IN);
    pathTransition.setOnFinished(doAfter);

    Timeline blurReset = new Timeline();
    KeyValue bx = new KeyValue(card.getDropShadow().offsetXProperty(), 0, Interpolator.EASE_IN);
    KeyValue by = new KeyValue(card.getDropShadow().offsetYProperty(), 0, Interpolator.EASE_IN);
    KeyValue br = new KeyValue(card.getDropShadow().radiusProperty(), 2, Interpolator.EASE_IN);
    KeyFrame bKeyFrame = new KeyFrame(duration, bx, by, br);
    blurReset.getKeyFrames().add(bKeyFrame);

    ParallelTransition pt = new ParallelTransition(card, pathTransition, blurReset);
    pt.play();
  }

  /**
   * Helper class for calculating card positions.
   */
  private static class MoveToAbs extends MoveTo {
    /**
     * Creates a new instance.
     *
     * @param node The {@link Node} object to calculate position for.
     * @param x    The x coordinate.
     * @param y    The y coordinate.
     */
    public MoveToAbs(Node node, double x, double y) {
      super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2,
          y - node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
    }
  }

  /**
   * Helper class for calculating card positions.
   */
  private static class LineToAbs extends LineTo {
    /**
     * Creates a new instance.
     *
     * @param node The {@link Node} object to calculate position for.
     * @param x    The x coordinate.
     * @param y    The y coordinate.
     */
    public LineToAbs(Node node, double x, double y) {
      super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2,
          y - node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
    }
  }

  /**
   * Helper class for determining mouse position.
   */
  private static class MousePos {
    /**
     * x and y coordinate.
     */
    double x, y;
  }

}
