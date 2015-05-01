package hu.unideb.inf.JCardGamesFX.klondike;

import hu.unideb.inf.JCardGamesFX.view.CardPileView;
import hu.unideb.inf.JCardGamesFX.view.CardView;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
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
  private List<CardView> draggedCards;
  private List<CardPileView> piles;

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
    CardView card = (CardView) e.getSource();

    // Get the pile that contained the actual card
    CardPileView activePile = card.getContainingPile();

    // Put this card and all above it to the list of dragged cards
    draggedCards = activePile.cardViewsAbove(card);

    // Bring them to front & apply difference vector to dragged cards
    draggedCards.forEach(cardView -> {
      cardView.toFront();
      cardView.setTranslateX(offsetX);
      cardView.setTranslateY(offsetY);
    });
  };

  EventHandler<MouseEvent> onMouseReleasedHandler = e -> {
    // Get the actual card
    CardView card = (CardView) e.getSource();

    // Get the pile that contained the actual card
    CardPileView activePile = card.getContainingPile();

    for (CardPileView pile : piles) {
      if (pile.equals(activePile))
        continue;

      if (pile.isEmpty()) {
        if (card.getBoundsInParent().intersects(pile.getBoundsInParent())) {
//          slideToPile(draggedCards, pile);
          activePile.moveCardViewsToPile(draggedCards, pile);
          return;
        }
      } else {
        if (card.getBoundsInParent().intersects(pile.getTopCardView().getBoundsInParent())) {
//          slideToPile(draggedCards, pile);
          activePile.moveCardViewsToPile(draggedCards, pile);
          return;
        }
      }
    }

    draggedCards.forEach(this::slideBack);
  };

  public KlondikeMouseUtil(List<CardView> draggedCards, List<CardPileView> piles) {
    this.draggedCards = draggedCards;
    this.piles = piles;
  }

  public void makeDraggable(final CardView card) {
    card.setOnMousePressed(onMousePressedHandler);
    card.setOnMouseDragged(onMouseDraggedHandler);
    card.setOnMouseReleased(onMouseReleasedHandler);
  }

  private void slideBack(CardView card) {
    double sourceX = card.getLayoutX() + card.getTranslateX();
    double sourceY = card.getLayoutY() + card.getTranslateY();

    double targetX = card.getLayoutX();
    double targetY = card.getLayoutY();

    animateCardMovement(card, sourceX, sourceY, targetX, targetY, Duration.millis(150));
  }

  private void slideToPile(List<CardView> draggedCards, CardPileView destPile) {
    double sourceX =
        draggedCards.get(0).getLayoutX() + draggedCards.get(0).getTranslateX();
    double sourceY =
        draggedCards.get(0).getLayoutY() + draggedCards.get(0).getTranslateY();

    double targetX;
    double targetY;

    if (destPile.isEmpty()) {
      targetX = destPile.getBoundsInParent().getMinX();
      targetY = destPile.getBoundsInParent().getMinY();
    } else {
      targetX = destPile.getTopCardView().getLayoutX();
      targetY = destPile.getTopCardView().getLayoutY();
    }

    draggedCards.forEach(card -> animateCardMovement(card, sourceX, sourceY, targetX, targetY, Duration.millis(150)));
  }

  private void animateCardMovement(
      CardView card, double sourceX, double sourceY,
      double targetX, double targetY, Duration duration) {

    Path path = new Path();
    path.getElements().add(new MoveToAbs(card, sourceX, sourceY));
    path.getElements().add(new LineToAbs(card, targetX, targetY));

    PathTransition pathTransition =
        new PathTransition(duration, path, card);
    pathTransition.setInterpolator(Interpolator.EASE_IN);

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
