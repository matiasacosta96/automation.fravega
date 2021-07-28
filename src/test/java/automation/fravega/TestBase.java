package automation.fravega;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import automation.fravega.pageobjects.Home;

public class TestBase {
	protected WebDriver driver;
	protected Home home;
	
	private void init() {
		home = new Home(driver);
	}
	
	@Before
	public void openDriver() {
		String os = System.getProperty("os.name");
		if(os.startsWith("Windows"))
			System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/windows/chromedriver.exe");
		else if(os.startsWith("Linux"))
			System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/linux/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		init();
	}
	
	@After
	public void closeDriver() {
		driver.quit();
	}
}
