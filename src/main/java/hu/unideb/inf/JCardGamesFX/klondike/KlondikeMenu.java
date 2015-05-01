package hu.unideb.inf.JCardGamesFX.klondike;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class KlondikeMenu extends MenuBar {
  public KlondikeMenu() {
    Menu menu = new Menu("Dummy Menu");
    menu.getItems().add(new MenuItem("Dummy Menu Command"));
    getMenus().add(menu);
  }
}
