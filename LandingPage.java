package testComponents;

import org.openqa.selenium.WebDriver;

public class LandingPage extends CommonUtilities_UI {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public void goTo() {
		driver.get("http://localhost:4200/festivals");
	}

}
