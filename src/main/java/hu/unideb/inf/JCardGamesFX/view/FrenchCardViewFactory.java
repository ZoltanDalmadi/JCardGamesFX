package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.FrenchCard;
import javafx.scene.image.Image;

public class FrenchCardViewFactory {

  private static CardTheme cardTheme;

  public static CardTheme getCardTheme() {
    return cardTheme;
  }

  public static void setCardTheme(CardTheme cardTheme) {
    FrenchCardViewFactory.cardTheme = cardTheme;
  }

  public static FrenchCardView createCardView(FrenchCard card, Image backFace) {
    FrenchCardView result = new FrenchCardView(card);

    result.setFrontFace(new Image(cardTheme.getFrontFacePathFor(card.getId())));
    result.setBackFace(backFace);
    card.addView(result);

    return result;
  }

}
