package hu.unideb.inf.JCardGamesFX.klondike;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

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
    RadioMenuItem classicRedMenuItem = new RadioMenuItem("Classic red");
    classicRedMenuItem.setToggleGroup(cardBackToggleGroup);
    RadioMenuItem fancyBlueMenuItem = new RadioMenuItem("Fancy blue");
    fancyBlueMenuItem.setToggleGroup(cardBackToggleGroup);
    RadioMenuItem fancyRedMenuItem = new RadioMenuItem("Fancy red");
    fancyRedMenuItem.setToggleGroup(cardBackToggleGroup);

    cardBackSettingsMenu.getItems().addAll(classicBlueMenuItem,
        classicRedMenuItem, fancyBlueMenuItem, fancyRedMenuItem);

    cardBackToggleGroup.selectToggle(cardBackToggleGroup.getToggles().get(0));
    settingsMenu.getItems().add(cardBackSettingsMenu);
  }
}
