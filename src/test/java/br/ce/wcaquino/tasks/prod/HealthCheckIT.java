package br.ce.wcaquino.tasks.prod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.wcaquino.tasks.webdriver.WebDriver;

public class HealthCheckIT {

	@Test
	public void healthCheck() throws IOException {
		try (WebDriver webDriver = new WebDriver()) {
			webDriver.getWebDriver().navigate().to("http://192.168.0.24:9999/tasks");
			webDriver.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			String version = webDriver.getWebDriver().findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
		}
	}
	
}
