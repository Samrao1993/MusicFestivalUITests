package testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtilities_UI {
	WebDriver driver;

	public CommonUtilities_UI(WebDriver driver) {
		this.driver = driver;
	}

	//utility to wait for any element to appear
	public void waitForElementToAppear(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));

	}

	// utility to get the count of total bands from property class
	public String getBandCountProperty() {
		Properties prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/Resources/GlobalData.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String totalBandCount = prop.getProperty("totalBands");
		return totalBandCount;
	}

	// Utility to get the count of total festivals from property class
	public String getFestivalCountProperty() {
		Properties prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/Resources/GlobalData.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String totalFestCount = prop.getProperty("totalFestival");
		return totalFestCount;
	}

	// Utility to return an array list which contains all the bands played in more than one festival
	 
	public ArrayList<String> listofbands() {
		ArrayList<String> arrayListOfBands = new ArrayList<String>();
		arrayListOfBands.add("Squint-281");
		arrayListOfBands.add("Wild Antelope");
		return arrayListOfBands;

	}
}
