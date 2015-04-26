package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.FrenchCard;

public class FrenchCardViewFactory {

  private static CardTheme cardTheme;

  public static CardTheme getCardTheme() {
    return cardTheme;
  }

  public static void setCardTheme(CardTheme cardTheme) {
    FrenchCardViewFactory.cardTheme = cardTheme;
  }

  public static FrenchCardView createCardView(FrenchCard card) {
    FrenchCardView result = new FrenchCardView(card);

    result.setFrontFace(cardTheme.getFrontFace(card.getId()));
    result.setBackFace(cardTheme.getBackFace());
    card.addView(result);

    return result;
  }

}
