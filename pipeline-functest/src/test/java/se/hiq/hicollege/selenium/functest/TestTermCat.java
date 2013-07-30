/**
 * 
 */
package se.hiq.hicollege.selenium.functest;

import static com.jayway.restassured.path.json.JsonPath.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.List;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import se.hiq.hicollege.restassured.common.RuleSetUpRestAssured;
import se.hiq.hicollege.selenium.common.BaseConnection;
import se.hiq.hicollege.selenium.common.RuleSetUpDriver;
//import se.hiq.hicollege.selenium.pageobjects.SearchTermCatPage;
import se.hiq.hicollege.selenium.pageobjects.UploadTermCatPage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.jayway.restassured.RestAssured;



/**
 * @author bu00006
 *
 */
@Ignore
public class TestTermCat extends BaseConnection{
	
	public TestTermCat()
			throws Exception {
		super();
		}
	
	@Rule
	public TestRule chain = RuleChain.outerRule(
			new RuleSetUpDriver(this, TARGET_SERVER_URL + "uploadtermcatalog.html")).around(new RuleSetUpRestAssured());
	
	@Test
	public void loadTermCatAndVeritfyClick() throws Exception{
		if(!BaseConnection.excludeBrowser(DesiredCapabilities.chrome())){
			openUploadTermcatPageAndVerify();
			EnterFilePathToTermCatAndSubmitVerifySerachTermCatPage();
			VerifyThatTermCatIsLoadedByRestAssured();
		}
		else{
			assertTrue(true);
		}
	}

	/**
	 *  Reads from json-api that termcat is loaded.
	 */
	private void VerifyThatTermCatIsLoadedByRestAssured() throws Exception{
		
		String json = RestAssured.given().expect().
		body("manufacturers.text",hasItem("FORD")).
		body("positions.text", hasItem("AVGASER DIESELDRIVEN")).
		body("vehicleKinds.text",hasItem("PERSONBIL")).
		body("locations.text",hasItem("BAK")).
		body("words.text",hasItem("UNDER")).
		body("fuels.text", hasItem("ETANOL")).
		body("colors.text", hasItem("GUL")).
		body("defectTypes.text", hasItem("ANLIGGNING")).
		//body("counties.text", hasItem("VÄSTERNORRLANDS LÄN")).
		get("/termcatalog").asString();		
		
		//workaround
		List<String> categories = with(json).get("counties.text");
		assertThat(categories, hasItem("VÄSTERNORRLANDS LÄN"));
	}

	/**
	 * 
	 */
	private void EnterFilePathToTermCatAndSubmitVerifySerachTermCatPage() throws Exception{
		if(BaseConnection.excludeBrowser(DesiredCapabilities.chrome())){
			throw new NotImplementedException();
		}
		String filePath = getTermCatFilePathAsString();
		UploadTermCatPage utcp = PageFactory.initElements(driver, UploadTermCatPage.class);
		utcp.filenameAsSendKeyCommand(filePath);
		utcp.clickSubmit();	
		//assertTrue(waitSiteLoad.until(ExpectedConditions.titleContains(SearchTermCatPage.TITLE)));
	}


	/**
	 * 
	 */
	private void openUploadTermcatPageAndVerify() throws Exception{
		driver.get(BaseConnection.TARGET_SERVER_URL+UploadTermCatPage.URL);
		assertTrue(waitSiteLoad.until(ExpectedConditions.titleContains(UploadTermCatPage.TITLE)));
		}
	
	private String getTermCatFilePathAsString(){
		URL url = getClass().getClassLoader().getResource("Termkatalog fordonsbesiktning_TSID-27-318.xlsx");
		return url.toExternalForm();
	}
	
	
}

