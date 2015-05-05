package hu.unideb.inf.JCardGamesFX.klondike;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;

public class KlondikeMenu extends MenuBar {

  public KlondikeMenu(KlondikeApp klondikeApp) {
    Menu gameMenu = new Menu("Game");
    Menu settingsMenu = new Menu("Settings");
    getMenus().addAll(gameMenu, settingsMenu);

    MenuItem newGameMenuItem = new MenuItem("New Game");
    newGameMenuItem.setOnAction(e -> klondikeApp.newGame());

    MenuItem exitGameMenuItem = new MenuItem("Exit");
    exitGameMenuItem.setOnAction(e -> Platform.exit());

    gameMenu.getItems().addAll(newGameMenuItem, exitGameMenuItem);

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

    cardBackSettingsMenu.getItems().addAll(classicBlueMenuItem,
        classicRedMenuItem, fancyBlueMenuItem, fancyRedMenuItem, hearthStoneMenuItem);

    cardBackToggleGroup.selectToggle(cardBackToggleGroup.getToggles().get(0));
    settingsMenu.getItems().add(cardBackSettingsMenu);
  }
}
