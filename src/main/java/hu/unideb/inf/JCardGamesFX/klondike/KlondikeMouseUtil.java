package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.model.Card;
import hu.unideb.inf.JCardGamesFX.model.CardPile;
import hu.unideb.inf.JCardGamesFX.view.CardPileView;
import hu.unideb.inf.JCardGamesFX.view.CardView;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.List;

public class KlondikeMouseUtil {

  private final MousePos mousePos = new MousePos();
  private List<Card> draggedCards;
  private List<CardView> draggedCardViews;
  private KlondikeGame game;
  private KlondikeGameArea gameArea;

  EventHandler<MouseEvent> onMousePressedHandler = e -> {
    // Store mouse click position
    mousePos.x = e.getSceneX();
    mousePos.y = e.getSceneY();
  };

  EventHandler<MouseEvent> onMouseDraggedHandler = e -> {
    // Calculate difference vector from clicked point
    double offsetX = e.getSceneX() - mousePos.x;
    double offsetY = e.getSceneY() - mousePos.y;

    // Get the actual card
    CardView cardView = (CardView) e.getSource();
    Card card = game.getDeck().getById(cardView.getShortID());

    // Get the pile that contained the actual card
    CardPileView activePileView = cardView.getContainingPile();
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

  EventHandler<MouseEvent> onMouseReleasedHandler = e -> {
    if (draggedCards == null && draggedCardViews == null)
      return;

    // Get the actual card
    CardView cardView = (CardView) e.getSource();
    Card card = game.getDeck().getById(cardView.getShortID());

    // Get the pile that contained the actual card
    CardPileView activePileView = cardView.getContainingPile();
    CardPile activePile = game.getPileById(activePileView.getShortID());

    for (CardPileView pileView : gameArea.getStandardPileViews()) {
      if (pileView.equals(activePileView))
        continue;

      if (pileView.isEmpty()) {
        if (cardView.getBoundsInParent().intersects(pileView.getBoundsInParent())) {
          CardPile pile = game.getPileById(pileView.getShortID());

          if (game.getRules().isMoveValid(card, pile)) {
            game.moveCards(draggedCards, activePile, pile);
            slideToPile(draggedCardViews, activePileView, pileView);
            draggedCards = null;
            draggedCardViews = null;
            return;
          }
        }
      } else {
        if (cardView.getBoundsInParent().intersects(pileView.getTopCardView().getBoundsInParent())) {
          CardPile pile = game.getPileById(pileView.getShortID());

          if (game.getRules().isMoveValid(card, pile)) {
            game.moveCards(draggedCards, activePile, pile);
            slideToPile(draggedCardViews, activePileView, pileView);
            draggedCards = null;
            draggedCardViews = null;
            return;
          }
        }
      }
    }

    draggedCardViews.forEach(this::slideBack);
    draggedCards = null;
    draggedCardViews = null;
  };

  public KlondikeMouseUtil(KlondikeGame game, KlondikeGameArea gameArea) {
    this.game = game;
    this.gameArea = gameArea;
  }

  public void makeDraggable(CardView card) {
    card.setOnMousePressed(onMousePressedHandler);
    card.setOnMouseDragged(onMouseDraggedHandler);
    card.setOnMouseReleased(onMouseReleasedHandler);
  }

  private void slideBack(CardView card) {
    double sourceX = card.getLayoutX() + card.getTranslateX();
    double sourceY = card.getLayoutY() + card.getTranslateY();

    double targetX = card.getLayoutX();
    double targetY = card.getLayoutY();

    animateCardMovement(card, sourceX, sourceY, targetX, targetY, Duration.millis(150), null);
  }

  private void slideToPile(List<CardView> cardsToSlide, CardPileView sourcePile, CardPileView destPile) {
    double destCardGap = destPile.getCardGap();

    double targetX;
    double targetY;

    if (destPile.isEmpty()) {
      targetX = destPile.getBoundsInParent().getMinX();
      targetY = destPile.getBoundsInParent().getMinY();
    } else {
      targetX = destPile.getTopCardView().getLayoutX();
      targetY = destPile.getTopCardView().getLayoutY();
    }

    for (int i = 0; i < cardsToSlide.size(); i++) {
      CardView currentCardView = cardsToSlide.get(i);
      double sourceX = currentCardView.getLayoutX() + currentCardView.getTranslateX();
      double sourceY = currentCardView.getLayoutY() + currentCardView.getTranslateY();

      animateCardMovement(currentCardView, sourceX, sourceY, targetX,
          targetY + ((i + 1) * destCardGap), Duration.millis(150),
          e -> sourcePile.moveCardViewToPile(currentCardView, destPile));
    }
  }

  private void animateCardMovement(
      CardView card, double sourceX, double sourceY,
      double targetX, double targetY, Duration duration, EventHandler<ActionEvent> doAfter) {

    Path path = new Path();
    path.getElements().add(new MoveToAbs(card, sourceX, sourceY));
    path.getElements().add(new LineToAbs(card, targetX, targetY));

    PathTransition pathTransition =
        new PathTransition(duration, path, card);
    pathTransition.setInterpolator(Interpolator.EASE_IN);
    pathTransition.setOnFinished(doAfter);

    pathTransition.play();
  }

  private static class MoveToAbs extends MoveTo {
    public MoveToAbs(Node node, double x, double y) {
      super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2,
          y - node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
    }
  }

  private static class LineToAbs extends LineTo {
    public LineToAbs(Node node, double x, double y) {
      super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2,
          y - node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
    }
  }

  private static class MousePos {
    double x, y;
  }

}
