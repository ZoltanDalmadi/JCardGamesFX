package hu.unideb.inf.JCardGamesFX.klondike;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;

/**
 * This class represents the menu bar for the application.
 */
public class KlondikeMenu extends MenuBar {

  /**
   * Constructs a {@link KlondikeMenu} object for the given {@link KlondikeApp}.
   *
   * @param klondikeApp The {@link KlondikeApp} instance to create the menu for.
   */
  public KlondikeMenu(KlondikeApp klondikeApp) {
    Menu gameMenu = new Menu("Game");
    Menu settingsMenu = new Menu("Settings");
    getMenus().addAll(gameMenu, settingsMenu);

    MenuItem newGameMenuItem = new MenuItem("New Game");

    MenuItem exitGameMenuItem = new MenuItem("Exit");
    exitGameMenuItem.setOnAction(e -> Platform.exit());

    gameMenu.getItems().addAll(newGameMenuItem, exitGameMenuItem);

    // card themes
    Menu cardThemeSettingsMenu = new Menu("Select card theme");
    ToggleGroup cardThemeToggleGroup = new ToggleGroup();

    RadioMenuItem classicMenuItem = new RadioMenuItem("Classic");
    classicMenuItem.setToggleGroup(cardThemeToggleGroup);
    classicMenuItem.setOnAction(e -> {
      klondikeApp.cardTheme.setThemeFile("/cardfaces/classic/theme.json");
      klondikeApp.gameArea.updateCardViews(klondikeApp.cardTheme);
    });

    RadioMenuItem piatnikImperialMenuItem = new RadioMenuItem("Piatnik Imperial");
    piatnikImperialMenuItem.setToggleGroup(cardThemeToggleGroup);
    piatnikImperialMenuItem.setOnAction(e -> {
      klondikeApp.cardTheme.setThemeFile("/cardfaces/piatnik_imperial/theme.json");
      klondikeApp.gameArea.updateCardViews(klondikeApp.cardTheme);
    });

    RadioMenuItem piatnikLuxuryMenuItem = new RadioMenuItem("Piatnik Luxury");
    piatnikLuxuryMenuItem.setToggleGroup(cardThemeToggleGroup);
    piatnikLuxuryMenuItem.setOnAction(e -> {
      klondikeApp.cardTheme.setThemeFile("/cardfaces/piatnik_luxury/theme.json");
      klondikeApp.gameArea.updateCardViews(klondikeApp.cardTheme);
    });

    cardThemeSettingsMenu.getItems().addAll(classicMenuItem,
        piatnikImperialMenuItem, piatnikLuxuryMenuItem);

    cardThemeToggleGroup.selectToggle(cardThemeToggleGroup.getToggles().get(0));

    // card backs
    Menu cardBackSettingsMenu = new Menu("Select card back");
    ToggleGroup cardBackToggleGroup = new ToggleGroup();

    RadioMenuItem classicBlueMenuItem = new RadioMenuItem("Classic blue");
    classicBlueMenuItem.setToggleGroup(cardBackToggleGroup);
    classicBlueMenuItem.setOnAction(e -> {
      klondikeApp.cardTheme.setBackFace(new Image("/backfaces/bb.png"));
      klondikeApp.gameArea.updateCardViews(klondikeApp.cardTheme);
    });

    RadioMenuItem classicRedMenuItem = new RadioMenuItem("Classic red");
    classicRedMenuItem.setToggleGroup(cardBackToggleGroup);
    classicRedMenuItem.setOnAction(e -> {
      klondikeApp.cardTheme.setBackFace(new Image("/backfaces/rb.png"));
      klondikeApp.gameArea.updateCardViews(klondikeApp.cardTheme);
    });

    RadioMenuItem fancyBlueMenuItem = new RadioMenuItem("Fancy blue");
    fancyBlueMenuItem.setToggleGroup(cardBackToggleGroup);
    fancyBlueMenuItem.setOnAction(e -> {
      klondikeApp.cardTheme.setBackFace(new Image("/backfaces/bb_fancy.png"));
      klondikeApp.gameArea.updateCardViews(klondikeApp.cardTheme);
    });

    RadioMenuItem fancyRedMenuItem = new RadioMenuItem("Fancy red");
    fancyRedMenuItem.setToggleGroup(cardBackToggleGroup);
    fancyRedMenuItem.setOnAction(e -> {
      klondikeApp.cardTheme.setBackFace(new Image("/backfaces/rb_fancy.png"));
      klondikeApp.gameArea.updateCardViews(klondikeApp.cardTheme);
    });

    RadioMenuItem hearthStoneMenuItem = new RadioMenuItem("Hearthstone");
    hearthStoneMenuItem.setToggleGroup(cardBackToggleGroup);
    hearthStoneMenuItem.setOnAction(e -> {
      klondikeApp.cardTheme.setBackFace(new Image("/backfaces/hearthstone.png"));
      klondikeApp.gameArea.updateCardViews(klondikeApp.cardTheme);
    });

    RadioMenuItem piatnikImperialBack1 = new RadioMenuItem("Piatnik Imperial 1");
    piatnikImperialBack1.setToggleGroup(cardBackToggleGroup);
    piatnikImperialBack1.setOnAction(e -> {
      klondikeApp.cardTheme.setBackFace(new Image("/backfaces/piatnik_imperial_1.png"));
      klondikeApp.gameArea.updateCardViews(klondikeApp.cardTheme);
    });

    RadioMenuItem piatnikImperialBack2 = new RadioMenuItem("Piatnik Imperial 2");
    piatnikImperialBack2.setToggleGroup(cardBackToggleGroup);
    piatnikImperialBack2.setOnAction(e -> {
      klondikeApp.cardTheme.setBackFace(new Image("/backfaces/piatnik_imperial_2.png"));
      klondikeApp.gameArea.updateCardViews(klondikeApp.cardTheme);
    });

    RadioMenuItem piatnikLuxuryBack1 = new RadioMenuItem("Piatnik Luxury 1");
    piatnikLuxuryBack1.setToggleGroup(cardBackToggleGroup);
    piatnikLuxuryBack1.setOnAction(e -> {
      klondikeApp.cardTheme.setBackFace(new Image("/backfaces/piatnik_luxury_1.png"));
      klondikeApp.gameArea.updateCardViews(klondikeApp.cardTheme);
    });

    RadioMenuItem piatnikLuxuryBack2 = new RadioMenuItem("Piatnik Luxury 2");
    piatnikLuxuryBack2.setToggleGroup(cardBackToggleGroup);
    piatnikLuxuryBack2.setOnAction(e -> {
      klondikeApp.cardTheme.setBackFace(new Image("/backfaces/piatnik_luxury_2.png"));
      klondikeApp.gameArea.updateCardViews(klondikeApp.cardTheme);
    });

    cardBackSettingsMenu.getItems().addAll(classicBlueMenuItem,
        classicRedMenuItem, fancyBlueMenuItem, fancyRedMenuItem,
        hearthStoneMenuItem, piatnikImperialBack1, piatnikImperialBack2,
        piatnikLuxuryBack1, piatnikLuxuryBack2);

    cardBackToggleGroup.selectToggle(cardBackToggleGroup.getToggles().get(0));
    settingsMenu.getItems().addAll(cardThemeSettingsMenu, cardBackSettingsMenu);
  }
}
