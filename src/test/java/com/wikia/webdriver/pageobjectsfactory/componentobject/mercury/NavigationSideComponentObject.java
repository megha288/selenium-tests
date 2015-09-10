package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership: Content X-Wing
 */
public class NavigationSideComponentObject extends BasePageObject {

  @FindBy(css = ".ember-text-field")
  private WebElement searchInput;
  @FindBy(css = ".cancel")
  private WebElement cancelSearchCaption;
  @FindBy(css = ".local-wikia-search a")
  private WebElement searchSuggestion;
  @FindBy(css = ".local-nav-menu > li")
  private List<WebElement> navList;
  @FindBy(css = ".back")
  private WebElement backChevron;
  @FindBy(css = ".random-article-link")
  private WebElement randomPageButton;
  @FindBy(css = ".overlay")
  private WebElement overlay;
  @FindBy(css = ".local-wikia-search")
  private WebElement resultField;
  @FindBy(css = ".local-nav-menu")
  private WebElement menuField;
  @FindBy(css = ".nav")
  private WebElement searchButton;
  @FindBy(css = "nav.side-nav")
  private WebElement menuView;
  @FindBy(xpath = "//span[contains(.,'Sorry, we could')]")
  private WebElement sorrySpan;

  public NavigationSideComponentObject(WebDriver driver) {
    super(driver);
  }

  public void clickSearchField() {
    wait.forElementVisible(searchInput);
    searchInput.click();
  }

  public void clickSearchButton() {
    wait.forElementVisible(searchButton);
    searchButton.click();
  }

  public void clickBackChevron() {
    wait.forElementVisible(backChevron);
    backChevron.click();
  }

  public void clickCancelButton() {
    wait.forElementVisible(cancelSearchCaption);
    cancelSearchCaption.click();
  }

  public void clickSuggestion(int index) {
    wait.forElementVisible(searchSuggestion);
    searchSuggestion.click();
  }

  public void clickNavListElement(int index) {
    wait.forElementVisible(navList.get(index));
    navList.get(index).click();
  }

  public void clickOverlay() {
    wait.forElementVisible(overlay);
    tapOnElement(overlay);
  }

  public void clickRandomPageButton() {
    wait.forElementVisible(randomPageButton);
    randomPageButton.click();
  }

  public boolean isSuggestionListDisplayed() {
    try {
      wait.forElementVisible(searchSuggestion, 5, 1000);
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    }
    return true;
  }

  public boolean isSorryInfoDisplayed() {
    try {
      wait.forElementVisible(sorrySpan, 5, 1000);
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    }
    return true;
  }

  public boolean isNavMenuVisible() throws WebDriverException {
    if (menuView.getAttribute("class") == null) {
      throw new WebDriverException("Expected String but got null");
    }
    return !menuView.getAttribute("class").contains("collapsed");
  }

  public boolean isBackLinkDisplayed() {
    try {
      wait.forElementVisible(backChevron, 5, 1000);
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    }
    return true;
  }

  public boolean isRandomPageButtonDisplayed() {
    try {
      wait.forElementVisible(randomPageButton, 5, 1000);
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    }
    return true;
  }

  public boolean isNavListElementEllipsized(int index) {
    wait.forElementVisible(navList.get(index));
    return navList.get(index).getCssValue("text-overflow").equals("ellipsis");
  }

  public boolean isMenuFieldVisible() {
    return menuField.getCssValue("visibility").equals("visible");
  }

  public boolean isResultFieldVisible() {
    return resultField.getCssValue("visibility").equals("visible");
  }

  public boolean isUserLoggedIn(String username) {
    return driver.findElements(By.cssSelector("figure.avatar img[alt='" + username + "']")).size()
           > 0;
  }

  public void typeInSearchField(String content) {
    wait.forElementVisible(searchInput);
    searchInput.sendKeys(content);
  }

  public NavigationSideComponentObject navigateToArticle(String articleName) {
    clickSearchButton();
    clickSearchField();
    typeInSearchField(articleName);
    clickSuggestion(0);
    waitForLoadingSpinnerToFinish();
    return this;
  }
}
