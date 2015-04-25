package hu.unideb.inf.JCardGamesFX.view;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CardTheme {
  private Map<String, String> frontFacePaths = new HashMap<>();
  private String backFacePath;
  private String themeFile;

  public CardTheme(String themeFile, String backFacePath) {
    this.themeFile = themeFile;
    this.backFacePath = backFacePath;
  }

  public CardTheme() {
  }

  public CardTheme(String backFacePath) {
    this.backFacePath = backFacePath;
  }

  public String getThemeFile() {
    return themeFile;
  }

  public void setThemeFile(String themeFile) {
    this.themeFile = themeFile;
  }

  public String getBackFacePath() {
    return backFacePath;
  }

  public void setBackFacePath(String backFacePath) {
    this.backFacePath = backFacePath;
  }

  public String getFrontFacePathFor(String key) {
    return frontFacePaths.get(key);
  }

  public void addFrontFacePathFor(String key, String value) {
    frontFacePaths.put(key, value);
  }

  public void removeFrontFacePath(String key) {
    frontFacePaths.remove(key);
  }

  public Map<String, String> getPaths() {
    return frontFacePaths;
  }

  public void parseTheme() {
    JSONParser jsonParser = new JSONParser();

    try {

      JSONObject jo = (JSONObject) jsonParser.parse(new InputStreamReader(
          getClass().getResource(themeFile).openStream()));

      for (Object key : jo.keySet()) {
        frontFacePaths.put((String) key, (String) jo.get(key));
      }

    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }
}
