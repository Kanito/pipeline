package se.hiq.hicollege.inttest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class ArquillianSmokeIT {
	
	@ArquillianResource
	URL deploymentUrl;
	
	@Deployment
	public static WebArchive createDeployment() throws URISyntaxException {
		URL dir_url = ClassLoader.getSystemResource("pipeline-web.war");
		File warFile = new File(dir_url.toURI());
		WebArchive war = ShrinkWrap.createFromZipFile(WebArchive.class, warFile);
		System.out.println(war.toString(true));
		return war;
	}

	@Test @RunAsClient
	public void should_create_greeting() throws IOException {
		System.out.println(deploymentUrl);
		Document document = Jsoup.parse(deploymentUrl, 1000);
		System.out.println(document.getAllElements().toString());
		
		assertThat("Element from page",document.getElementById("titleId").attr("name"), is("title"));
	}
}