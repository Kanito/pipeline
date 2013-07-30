package se.hiq.hicollege.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UploadTermCatPage{

	protected WebDriver driver;
	public static final String URL = "uploadtermcatalog.html";
	public static final String TITLE = "Ladda upp termkatalog";
	
	public UploadTermCatPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.ID, using="uploadFile")
	private WebElement fileNameInput;
	@FindBy(how = How.ID, using="submitFileRequest")
	private WebElement submitUpload;
	
	@FindBy(how = How.ID, using="menu1")
	protected WebElement menuBokning;
	
	@FindBy(how = How.ID, using="menu2")
	public WebElement menuGrundbokning;
	
	@FindBy(how = How.ID, using="menu3")
	public WebElement menuGrundparametrar;
	
	public void clickSubmit(){
		submitUpload.submit();
	}	
	
	public void filenameAsSendKeyCommand(String filePath){
		fileNameInput.sendKeys(filePath);
	}
}


