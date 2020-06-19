package br.ce.wcaquino.tasks.functional;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.wcaquino.tasks.webdriver.WebDriver;

public class TasksTest {
	
	private WebDriver acessarAplicacao() {
		WebDriver webDriver = new WebDriver();
		webDriver.getWebDriver().navigate().to("http://localhost:8001/tasks");
		webDriver.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return webDriver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws IOException {
		
		try (WebDriver webDriver = acessarAplicacao()) {
			webDriver.getWebDriver().findElement(By.id("addTodo")).click();
			
			webDriver.getWebDriver().findElement(By.id("task")).sendKeys("Teste via selenium");
			webDriver.getWebDriver().findElement(By.id("dueDate")).sendKeys("10/10/2030");
			webDriver.getWebDriver().findElement(By.id("saveButton")).click();
			
			String mensagem = webDriver.getWebDriver().findElement(By.id("message")).getText();
			assertEquals("Success!", mensagem);
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws IOException {
		
		try (WebDriver webDriver = acessarAplicacao()) {
			
			webDriver.getWebDriver().findElement(By.id("addTodo")).click();
			
			webDriver.getWebDriver().findElement(By.id("dueDate")).sendKeys("10/10/2030");
			webDriver.getWebDriver().findElement(By.id("saveButton")).click();
			
			String mensagem = webDriver.getWebDriver().findElement(By.id("message")).getText();
			assertEquals("Fill the task description", mensagem);
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemData() throws IOException {
		
		try (WebDriver webDriver = acessarAplicacao()) {
			webDriver.getWebDriver().findElement(By.id("addTodo")).click();
			
			webDriver.getWebDriver().findElement(By.id("task")).sendKeys("Teste via selenium");
			webDriver.getWebDriver().findElement(By.id("saveButton")).click();
			
			String mensagem = webDriver.getWebDriver().findElement(By.id("message")).getText();
			assertEquals("Fill the due date", mensagem);
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws IOException {
		
		try (WebDriver webDriver = acessarAplicacao()) {
			webDriver.getWebDriver().findElement(By.id("addTodo")).click();
			
			webDriver.getWebDriver().findElement(By.id("task")).sendKeys("Teste via selenium");
			webDriver.getWebDriver().findElement(By.id("dueDate")).sendKeys("10/10/2010");
			webDriver.getWebDriver().findElement(By.id("saveButton")).click();
			
			String mensagem = webDriver.getWebDriver().findElement(By.id("message")).getText();
			assertEquals("Due date must not be in past", mensagem);
		}
	}
}
