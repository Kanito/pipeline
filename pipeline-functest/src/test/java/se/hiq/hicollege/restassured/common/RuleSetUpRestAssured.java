package se.hiq.hicollege.restassured.common;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.RestAssured;

public class RuleSetUpRestAssured extends TestWatcher {
	String address;
	Logger log = LoggerFactory.getLogger(RuleSetUpRestAssured.class);
	
	@Override
	protected void starting(Description description) {
		try {
			
			XMLConfiguration config = new XMLConfiguration();
			String path = getClass().getResource("/restassured.xml").getPath();
			config.setFile(new File(path));
			config.load();
			
			log.info(config.getString("config.baseURI"));
			log.info(config.getString("config.basePath"));
			log.info(""+config.getInt("config.port"));
			
			
			} catch (ConfigurationException e) {
			log.error("failed to load xml propertyfile for restassured",e);
		}
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.basePath="/pipeline/rest";
		RestAssured.port = 8081;
		super.starting(description);
	}

	@Override
	protected void finished(Description description) {
		RestAssured.reset();		
		super.finished(description);
	}
}
