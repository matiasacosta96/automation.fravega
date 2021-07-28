package automation.fravega.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	WebDriver driver;
	private String url = "https://www.fravega.com";
	
	@FindBy(xpath = "//input[@placeholder='Buscar productos']")
	private WebElement keyword;
	
	@FindBy(xpath = "//div[@id='react-aria-modal-dialog']//button//*//*")
	private WebElement closeModal;
	
	@FindBy(xpath = "//h4[contains(text(),'Heladeras')]")
	private WebElement filterHeladera;
	
	@FindBy(xpath = "//a[@name='viewAllBrands']")
	private WebElement viewAllBrands;
	
	@FindBy(xpath = "//label[@for='filterItemsamsung']")
	private WebElement filterSamsung;
	
	@FindBy(xpath = "//button[@id='apply']")
	private WebElement bApply;
	
	@FindBy(xpath = "//li[@name = 'totalResult']//span")
	private WebElement totalResult;
	
	private List<WebElement> grid;
	
	private List<WebElement> breadcrumbs;

	public String getUrl() {
		return url;
	}
	
	public void buscarTexto(String texto) {
		keyword.click();
		keyword.sendKeys(texto);
		keyword.sendKeys(Keys.RETURN);
	}
	
	public void closeModal() {
		closeModal.click();
	}
	
	public void clickFilterHeladera() {
		filterHeladera.click();
	}
	
	public void clickFilterSamsung() {
		viewAllBrands.click();
		filterSamsung.click();
		bApply.click();
		grid = driver.findElements(By.xpath("//section//ul[@name = 'itemsGrid']//h3[contains(@class, 'PieceTitle')]"));
	}
	
	public boolean validarTitulos(String text) {
		for(int i=0; i<grid.size(); i++) {
			if(!grid.get(i).getText().toLowerCase().contains(text.toLowerCase())) return false;
		}
		return true;
	}
	
	public boolean validarCantidadElementos() {
		int total1 = Integer.parseInt(totalResult.getText());
		int total2 = grid.size();
		return total1==total2;
	}
	
	public boolean validarBreadcrumb(String breadcrumb) {
		breadcrumbs = driver.findElements(By.xpath("//li[contains(@class, 'BreadCrumb')]"));
		for(int i=0; i<breadcrumbs.size(); i++) {
			if(breadcrumbs.get(i).getText().equals(breadcrumb)) return true;
		}
		return false;
	}
	
	public Home(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
}
