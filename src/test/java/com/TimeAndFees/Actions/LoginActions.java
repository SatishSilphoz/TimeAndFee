package com.TimeAndFees.Actions;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Capium.Utilites.HelperClass;
import com.Capium.Utilites.Log;
import com.TimeAndFees.Locators.LoginLocators;



public class LoginActions {

	LoginLocators loginLocators = null;
	
	@FindBy(xpath="//div[contains(@class,'outer-circle')]")
	private WebElement OldEcoCircleContainer;
	
	@FindBy(xpath="//a[@href='/accounts/']/div[contains(text(),'Accounts Production')]")
	private WebElement AccountsProduction_Module;
	
	@FindBy(xpath="//a[@href='/selfassessment/']/div[contains(text(),'Self Assessment')]")
	private WebElement SelfAssessment_Module;
	
	@FindBy(xpath="//a[@href='/corporationtax/']/div[contains(text(),'Corporation Tax')]")
	private WebElement CorporationTax_Module;
	
	@FindBy(xpath="//a[@href='/bookkeeping/']/div[contains(text(),'Bookkeeping')]")
	private WebElement Bookkeeping_Module;
	

	public LoginActions() {
		this.loginLocators = new LoginLocators();
		PageFactory.initElements(HelperClass.getDriver(), loginLocators);
	}

	public void EnterUsername(String username) {
		loginLocators.inputUsername.clear();
		loginLocators.inputUsername.sendKeys(username);

	}
	
	public void EnterPassword(String password) {
		loginLocators.inputPassword.sendKeys(password);
	}
	
	public void ClickOnLogin() {
		loginLocators.btnLogin.click();
	}

	
	public boolean isHomePage() {
		try {
			return loginLocators.Homepage_Element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

//	public void navigateToTimeAndFeesModule() {
//	    WebDriverWait wait = new WebDriverWait(HelperClass.getDriver(), Duration.ofSeconds(2));
//
//	    try {
//	        if (isElementVisible(loginLocators.TimeAndFees_Module, wait)) {
//	            loginLocators.TimeAndFees_Module.click();
//	            System.out.println("Navigated to TimeAndFees module from Homepage.");
//	            return;
//	        }
//
//	        if (isElementVisible(loginLocators.C_icon_inside_modules, wait)) {
//	            loginLocators.C_icon_inside_modules.click();
//	            wait.until(ExpectedConditions.visibilityOf(loginLocators.InsideCiconHome));
//
//	            if (isElementVisible(loginLocators.InsideCiconHome, wait)) {
//	                loginLocators.InsideCiconHome.click();
//	                System.out.println("Navigated to TimeAndFees module from inside 5.0 module.");
//	                wait.until(ExpectedConditions.visibilityOf(loginLocators.TimeAndFees_Module));
//	            }
//
//	            if (isElementVisible(loginLocators.TimeAndFees_Module, wait)) {
//	                loginLocators.TimeAndFees_Module.click();
//	                System.out.println("Navigated to TimeAndFees module from Homepage after C icon.");
//	                return;
//	            }
//	        }
//
//	        if (isElementVisible(loginLocators.OldEcoTimeAndFees, wait)) {
//	            loginLocators.OldEcoTimeAndFees.click();
//	            System.out.println("Navigated to TimeAndFees module via Old EcoSpace.");
//	            return;
//	        }
//
//	        System.out.println("CorporationTax module could not be found in any known location.");
//
//	    } catch (Exception e) {
//	        System.out.println("Error while navigating to CorporationTax module: " + e.getMessage());
//	        Log.info("Error while navigating to CorporationTax module.", "Anwar", "Redirection To CorporationTax Module");
//	    }
//	}	
	public void navigateToTimeAndFeesModule() {
	    WebDriverWait wait = new WebDriverWait(HelperClass.getDriver(), Duration.ofSeconds(3));

	    try {
	        if (isElementVisible(loginLocators.TimeAndFees_Module, wait)) {
	            loginLocators.TimeAndFees_Module.click();
	            System.out.println("Navigated to TimeAndFees module from Homepage.");
	            return;
	        }
	        List<WebElement> oldEcoModules = HelperClass.getDriver().findElements(
	            By.xpath("//a/div[contains(@class,'circle') and " +
	                     "(normalize-space()='Accounts Production' " +
	                     "or normalize-space()='Self Assessment' " +
	                     "or normalize-space()='Corporation Tax' " +
	                     "or normalize-space()='Bookkeeping')]")
	        );

	        if (!oldEcoModules.isEmpty()) {
	            System.out.println("Old Eco space detected. Found " + oldEcoModules.size() + " modules.");

	            boolean clickedModule = false;

	            for (WebElement module : oldEcoModules) {
	                String classAttr = module.getAttribute("class");
	                if (classAttr != null && (classAttr.contains("disabled") || classAttr.contains("lock"))) {
	                    System.out.println("Skipping locked module: " + module.getText());
	                    continue;
	                }

	                if (module.isDisplayed() && module.isEnabled()) {
	                    System.out.println("Clicking Old Eco module: " + module.getText());
	                    module.click();
	                    clickedModule = true;
	                    break;
	                }
	            }

	            if (clickedModule) {
	                if (isElementVisible(loginLocators.C_icon_inside_modules, wait)) {
	                    loginLocators.C_icon_inside_modules.click();
	                    wait.until(ExpectedConditions.visibilityOf(loginLocators.InsideCiconHome));

	                    if (isElementVisible(loginLocators.InsideCiconHome, wait)) {
	                        loginLocators.InsideCiconHome.click();
	                        System.out.println("Navigated to Home from C icon after Old Eco.");
	                    }

	                    if (isElementVisible(loginLocators.TimeAndFees_Module, wait)) {
	                        loginLocators.TimeAndFees_Module.click();
	                        System.out.println("Navigated to TimeAndFees module after Old Eco redirection.");
	                        return;
	                    }
	                }
	            }
	        }

	        if (isElementVisible(loginLocators.C_icon_inside_modules, wait)) {
	            loginLocators.C_icon_inside_modules.click();
	            wait.until(ExpectedConditions.visibilityOf(loginLocators.InsideCiconHome));

	            if (isElementVisible(loginLocators.InsideCiconHome, wait)) {
	                loginLocators.InsideCiconHome.click();
	                System.out.println("Navigated to TimeAndFees module from inside 5.0 module.");
	                wait.until(ExpectedConditions.visibilityOf(loginLocators.TimeAndFees_Module));
	            }

	            if (isElementVisible(loginLocators.TimeAndFees_Module, wait)) {
	                loginLocators.TimeAndFees_Module.click();
	                System.out.println("Navigated to TimeAndFees module from Homepage after C icon.");
	                return;
	            }
	        }

	        if (isElementVisible(loginLocators.OldEcoTimeAndFees, wait)) {
	            loginLocators.OldEcoTimeAndFees.click();
	            System.out.println("Navigated to TimeAndFees module via Old EcoSpace direct link.");
	            return;
	        }

	        System.out.println("TimeAndFees module could not be found in any known location.");

	    } catch (Exception e) {
	        System.out.println("Error while navigating to TimeAndFees module: " + e.getMessage());
	        Log.info("Error while navigating to TimeAndFees module.", "Anwar", "Redirection To TimeAndFees Module");
	    }
	}





	public void Logout() throws IOException {
		WebDriver driver = HelperClass.getDriver();
		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
		shortWait.pollingEvery(Duration.ofMillis(250));

		try {
			boolean logoutClicked = false;

			if (isElementVisible(loginLocators.FivePointProfileIcon, shortWait)) {
				HelperClass.clickWithRetry(loginLocators.FivePointProfileIcon, driver, shortWait);
				Log.info("Clicked 5.0 Profile Icon.");

				if (isElementVisible(loginLocators.SignoutFivePointZero, shortWait)) {
					HelperClass.clickWithRetry(loginLocators.SignoutFivePointZero, driver, shortWait);
					Log.info("Clicked 5.0 Sign Out.");
					logoutClicked = true;
				}
			}
			else if (isElementVisible(loginLocators.Logo_in_homepage, shortWait)) {
				HelperClass.clickWithRetry(loginLocators.Logo_in_homepage, driver, shortWait);
				Log.info("Clicked homepage logo.");

				if (isElementVisible(loginLocators.Logout_inside_logo, shortWait)) {
					HelperClass.clickWithRetry(loginLocators.Logout_inside_logo, driver, shortWait);
					Log.info("Clicked logout inside logo.");
					logoutClicked = true;
				}
			}

			else if (isElementVisible(loginLocators.Logout_element, shortWait)) {
				HelperClass.clickWithRetry(loginLocators.Logout_element, driver, shortWait);
				Log.info("Clicked direct logout element.");
				logoutClicked = true;
			}

			if (logoutClicked) {
				WebDriverWait loginWait = new WebDriverWait(driver, Duration.ofSeconds(8));
				loginWait.pollingEvery(Duration.ofMillis(200));
				loginWait.until(ExpectedConditions.or(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='365']")),
						ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//p[normalize-space()='Receipts, Invoices & Bankfeeds']"))));
				Log.info("Successfully redirected to login page after logout.");
			} else {
				Log.warn("No logout element was clicked.");

			}

		} catch (TimeoutException e) {
			Log.error("Timeout: Login page not detected after logout.");

			e.printStackTrace();
			HelperClass.captureScreenshot("LogoutTimeout");
		} catch (Exception e) {
			Log.error("Logout failed: " + e.getMessage());

			e.printStackTrace();
			HelperClass.captureScreenshot("LogoutFailure");
		}
	}

	public boolean isElementVisible(WebElement element, WebDriverWait wait) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
