package br.ce.wcaquino.tasks.webdriver;

import java.io.Closeable;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriver implements Closeable {
	
	private org.openqa.selenium.WebDriver webDriver;
	
	public WebDriver() {
		WebDriverManager.chromedriver().arch64().setup();
	}

	public org.openqa.selenium.WebDriver getWebDriver() {
		if (webDriver == null) {
			webDriver = new ChromeDriver();
		}
		
		return webDriver;
	}

	@Override
	public void close() throws IOException {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

}
