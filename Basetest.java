package testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Basetest {
	public WebDriver driver;
	public LandingPage landingpage;
	public WebDriver initializeDriver() throws IOException
	{
	Properties prop=new Properties();
	FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Resources/GlobalData.properties");
	prop.load(fis);
	String browserName= prop.getProperty("browser");
	if(browserName.equalsIgnoreCase("Firefox"))
	{
	
	System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/main/java/Resources/geckodriver.exe");
	driver=new FirefoxDriver();
	
	}
	else if(browserName.equalsIgnoreCase("chrome"))
	{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/java/Resources/chromedriver.exe");
		driver = new ChromeDriver();
	}
	else if(browserName.equalsIgnoreCase("edge"))
	{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/java/Resources/edge.exe");
		driver = new EdgeDriver();
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.manage().window().maximize();
	return driver;
}
	@BeforeClass
	public LandingPage launchApplication() throws IOException, InterruptedException
	{
		driver= initializeDriver();
		landingpage=new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	
}

