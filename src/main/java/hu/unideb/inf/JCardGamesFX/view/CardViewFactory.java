package hu.unideb.inf.JCardGamesFX.view;

import hu.unideb.inf.JCardGamesFX.model.FrenchCard;

/**
 * Factory class for creating {@link CardView} objects.
 */
public class CardViewFactory {

  /**
   * Actual theme of the cards.
   */
  private static CardTheme cardTheme;

  /**
   * Returns the active theme of the cards.
   *
   * @return The {@link CardTheme} object.
   */
  public static CardTheme getCardTheme() {
    return cardTheme;
  }

  /**
   * Sets the active theme of the cards.
   *
   * @param cardTheme The {@link CardTheme} object to be set.
   */
  public static void setCardTheme(CardTheme cardTheme) {
    CardViewFactory.cardTheme = cardTheme;
  }

  /**
   * Creates a {@link CardView} object for the specified
   * {@link FrenchCard} object.
   *
   * @param card The {@link FrenchCard} object to create the view for.
   * @return The created {@link CardView} object.
   */
  public static CardView createCardView(FrenchCard card) {
    CardView result = new CardView();

    result.setFrontFace(cardTheme.getFrontFace(card.getId()));
    result.setBackFace(cardTheme.getBackFace());

    return result;
  }

}
