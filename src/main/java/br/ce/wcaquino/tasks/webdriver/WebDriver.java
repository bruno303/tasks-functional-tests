package br.ce.wcaquino.tasks.webdriver;

import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriver implements Closeable {
	
	private org.openqa.selenium.WebDriver webDriver;
	
	public WebDriver() {
		WebDriverManager.chromedriver().arch64().setup();
	}

	public org.openqa.selenium.WebDriver getWebDriver() {
		if (webDriver == null) {
//			webDriver = new ChromeDriver();
			try {
				DesiredCapabilities capabilities= DesiredCapabilities.chrome();
				webDriver= new RemoteWebDriver(new URL("http://172.18.0.1:4444/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
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
