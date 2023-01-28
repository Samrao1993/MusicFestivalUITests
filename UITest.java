package testCases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testComponents.Basetest;
import testComponents.CommonUtilities_UI;

public class UITest extends Basetest {
	
	//Fetch List of all the bands using absolute Xpath 
	List<WebElement> bands = driver.findElements(By.xpath("//html/body/app-root/app-festivals/ol/li"));
	// Fetch the expected bandCount from the utility in CommonComponent Class
	CommonUtilities_UI obj = new CommonUtilities_UI(driver);
	String expectedBand_str = obj.getBandCountProperty();
	int expectedBandCount = Integer.parseInt(expectedBand_str);
	// Fetch the expected festivalCount from the utility in CommonComponent Class
	String expectedFestival_str = obj.getFestivalCountProperty();
	// Convert response received in string to integer
	int expectedFestivalCount = Integer.parseInt(expectedFestival_str);
	// Fetch list of bands which are played in more than 1 festival from CommonComponent Class
	ArrayList<String> expectedArrayListOfBands = obj.listofbands();
	
	//TC_01 To verify the total number of unique bands in all festivals
	@Test(priority = 1)
	public void countTotalBands() {
		// Convert List into set to find the unique bands and avoid duplicates
		Set<WebElement> hSet = new HashSet<WebElement>();
		// Add all band webElements into HashSet
		hSet.addAll(bands);
		// Check the number of elements in set to find unique bands
		int actualBandCount = hSet.size();
		// Compare this bandCount with Actual number of bands
		Assert.assertEquals(actualBandCount, expectedBandCount);
	}

	//TC_02, TC_3, TC_04(Positive and Negative test cases as per data)
	//To verify the total number of bands for a particular festival
	@Test(dataProvider = "bandCountForFestival_data", priority = 2)
	public void bandCountForAFestival(String festivalPassed, int numberOfBands) {
		// initialize counter variable
		int bandCount = 0;
		// traverse in the list of bands
		for (WebElement element : bands) {
			// fetch the Festival for every parent band
			WebElement festival = element.findElement(By.xpath("//html/body/app-root/app-festivals/ol/li/p"));
			String festivalName = festival.getText();
			//Compare the festival fetched with the actual festival passed as an argument
			if (festivalName.equalsIgnoreCase(festivalPassed)) {
				bandCount++;
			}
		}
		//Compare the expected band count with actual band count for a festival
		Assert.assertEquals(bandCount, numberOfBands);
	}

	//TC_05 To verify the total Count of festivals
	@Test(priority = 3)
	public void countTotalFestivals() {
		// Create set collection to avoid duplicate festivals, and show the unique count of total festivals
		Set<WebElement> hSet = new HashSet<WebElement>();
		for (WebElement element : bands) {
			//List<WebElement> allChildElements = element.findElements(By.xpath("*"));
			WebElement festival = element.findElement(By.xpath("//html/body/app-root/app-festivals/ol/li/p"));
			hSet.add(festival);
		}
		int actualFestivalCount=hSet.size();
		Assert.assertEquals(actualFestivalCount, expectedFestivalCount);

	}
    
	//TC_06,TC_07,TC_08(Positive and Negative test cases as per data)
	//To verify for how many festivals a particular band is played
	@Test(dataProvider = "bandInFestivals_data", priority = 4)
	public void bandInFestivals(String bandpassed, int numberOfInstances) {
		int bandCounter = 0;
		for (int i = 0; i < bands.size(); i++) {
			String bandName = bands.get(i).getText();
			if (bandName.equalsIgnoreCase(bandpassed)) {
				bandCounter++;
			}
		}
		Assert.assertEquals(bandCounter, numberOfInstances);
	}

	//TC_09 To verify the list of bands which are played in more than one festival
	@Test(priority = 5)
	public void bandList() {
		WebElement[] arrayOfBands = new WebElement[bands.size()];
		bands.toArray(arrayOfBands);
		ArrayList<String> actualArrayListOfBands = new ArrayList<String>();
		for (int i = 0; i < arrayOfBands.length; i++) {
			for (int j = i + 1; j < arrayOfBands.length; j++) {
				if (arrayOfBands[i].equals(arrayOfBands[j])) {
					String band = arrayOfBands[i].getText();

					actualArrayListOfBands.add(band);
				}
			}
		}
		//Compare the list retrieved through above function with the actual list fetched from Common utility class
		Assert.assertTrue(actualArrayListOfBands.equals(expectedArrayListOfBands));
	}

	//TC_10,TC_11,TC_12. Validate Positive and Negative scenarios
	//To verify the music festival for a particular band is correct or not
	@Test(dataProvider = "bandforFestival_data", priority = 6)
	public void bandforFestival(String bandpassed, String festivalPassed) {
		Boolean flag = false;
		for (WebElement element : bands) {
			String bandName = element.getText();
			if (bandName.equalsIgnoreCase(bandpassed)) {
				WebElement festival = element.findElement(By.xpath("//html/body/app-root/app-festivals/ol/li/p"));
				String festivalName = festival.getText();
				if (festivalName.equalsIgnoreCase(festivalPassed)) {
					flag = true;
					break;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	// Data for all functions using Dataprovider annotation of TestNG

	@DataProvider
	public Object[][] bandforFestival_data() {
		Object[][] data = new Object[3][2];
		// TC_10 Verify LOL-palooza is the music festival for Frank Jupiter band
		data[0][0] = "Frank Jupiter";
		data[0][1] = "LOL-palooza";
		// TC_11 Verify LOL-palooza is not the music festival for Manish Ditch band
		//Test Negative scenario.This test case should fail.
		//Trainerella is the actual music festival for Manish Ditch band
		data[1][0] = "Manish Ditch";
		data[1][1] = "LOL-palooza";
		// TC_12 : Verify there is no music festival for Critter Girls band
		data[2][0] = "Critter Girls";
		data[2][1] = "";
		return data;
	}

	@DataProvider
	public Object[][] bandInFestivals_data() {
		Object[][] data = new Object[3][2];
		//TC_06 To verify Squint-281 is played in 3 different festivals
		data[0][0] = "Squint-281";
		data[0][1] = 3;
		//TC_07 To verify Wild Antelope is played in 2 different festivals
		data[1][0] = "Wild Antelope";
		data[1][1] = 2;
		//TC_08 To verify Frank Jupiter is not played in 3 festival
		//Test Negative test scenario.Frank Jupiter is played in only 1 festival. This test case should fail	
		data[2][0] = "Frank Jupiter";
		data[2][1] = 3;
		return data;
	}

	@DataProvider
	public Object[][] bandCountForFestival_data() {
		Object[][] data = new Object[3][2];
		//TC_02 To verify LOL-palooza festival has 4 bands
		data[0][0] = "LOL-palooza";
		data[0][1] = 4;
		//TC_03 To  verify Twisted Tour festival has 3 bands
		data[1][0] = "Twisted Tour";
		data[1][1] = 3;
		//TC_04 To verify Small Night In festival has not 1 band
		//Test Negative scenario. As Small Night In festival has 5 bands. This test case should fail
		data[2][0] = "Small Night In";
		data[2][1] = 1;
		return data;
	}
}
