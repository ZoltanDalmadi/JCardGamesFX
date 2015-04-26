package hu.unideb.inf.JCardGamesFX.view;

import javafx.scene.image.Image;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a card theme.
 */
public class CardTheme {

  /**
   * This map holds all the {@link Image} objects for the specific key values.
   */
  private Map<String, Image> frontFaces = new HashMap<>();

  /**
   * This {@link Image} object holds the back face,
   * which is the same for all cards.
   */
  private Image backFace;

  /**
   * Path to the theme json file.
   */
  private String themeFile;

  /**
   * Constructs a <code>CardTheme</code> object with the specified json file
   * and image file as back face.
   *
   * @param themeFile    Path to the theme json file.
   * @param backFacePath Path to the back face image file.
   */
  public CardTheme(String themeFile, String backFacePath) {
    this.themeFile = themeFile;
    this.backFace = new Image(backFacePath);
    parseTheme();
  }

  /**
   * Constructs an empty <code>CardTheme</code> object.
   * You need to set the theme file and back face {@link Image}, and call the
   * <code>parseTheme()</code> method before using.
   */
  public CardTheme() {
  }

  /**
   * Returns the path to the theme file.
   *
   * @return The path to the theme file.
   */
  public String getThemeFile() {
    return themeFile;
  }

  /**
   * Sets the path for the theme file.
   *
   * @param themeFile The path for the theme file to be set.
   */
  public void setThemeFile(String themeFile) {
    this.themeFile = themeFile;
    parseTheme();
  }

  /**
   * Returns the {@link Image} object that holds the back face of the theme.
   *
   * @return The {@link Image} object that holds the back face of the theme.
   */
  public Image getBackFace() {
    return backFace;
  }

  /**
   * Sets the {@link Image} object that holds the back face of the theme.
   *
   * @param backFace The {@link Image} object to be set.
   */
  public void setBackFace(Image backFace) {
    this.backFace = backFace;
  }

  /**
   * Returns the {@link Image} object that holds the front face
   * for the specific Id.
   *
   * @param key The Id of the card.
   * @return The {@link Image} object that holds the front face of the card.
   */
  public Image getFrontFace(String key) {
    return frontFaces.get(key);
  }

  /**
   * Adds an {@link Image} object to the theme with the specified key.
   *
   * @param key   The Id as a key.
   * @param value The {@link Image} object for the key.
   */
  public void addFrontFace(String key, Image value) {
    frontFaces.put(key, value);
  }

  /**
   * Removes an {@link Image} object from the theme with the specified key.
   *
   * @param key The Id of the {@link Image} object to be removed.
   */
  public void removeFrontFace(String key) {
    frontFaces.remove(key);
  }

  /**
   * Returns the {@link Map} that holds the {@link Image} objects of the theme.
   *
   * @return The {@link Map} holding the {@link Image} objects.
   */
  public Map<String, Image> getImages() {
    return frontFaces;
  }

  /**
   * Parses the json file and creates the {@link Image} objects.
   */
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
