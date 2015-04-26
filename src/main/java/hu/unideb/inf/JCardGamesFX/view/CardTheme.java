package hu.unideb.inf.JCardGamesFX.view;

import javafx.scene.image.Image;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CardTheme {
  private Map<String, Image> frontFaces = new HashMap<>();
  private Image backFace;
  private String themeFile;

  public CardTheme(String themeFile, String backFacePath) {
    this.themeFile = themeFile;
    this.backFace = new Image(backFacePath);
    parseTheme();
  }

  public CardTheme() {
  }

  public CardTheme(String backFacePath) {
    this.backFace = new Image(backFacePath);
  }

  public String getThemeFile() {
    return themeFile;
  }

  public void setThemeFile(String themeFile) {
    this.themeFile = themeFile;
    parseTheme();
  }

  public Image getBackFace() {
    return backFace;
  }

  public void setBackFace(Image backFace) {
    this.backFace = backFace;
  }

  public Image getFrontFace(String key) {
    return frontFaces.get(key);
  }

  public void addFrontFace(String key, Image value) {
    frontFaces.put(key, value);
  }

  public void removeFrontFace(String key) {
    frontFaces.remove(key);
  }

  public Map<String, Image> getImages() {
    return frontFaces;
  }

  public void parseTheme() {
    JSONParser jsonParser = new JSONParser();

    try {

      JSONObject jo = (JSONObject) jsonParser.parse(new InputStreamReader(
          getClass().getResource(themeFile).openStream()));

      for (Object key : jo.keySet()) {
        frontFaces.put((String) key, new Image((String) jo.get(key)));
      }

    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }
}
