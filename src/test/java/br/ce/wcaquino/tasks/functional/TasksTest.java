package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();

		driver.navigate().to("http://localhost:8088/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();

		try {
			// Clicar em 'Add Todo'
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("31/05/2021");

			// Clicar em 'Save'
			driver.findElement(By.id("saveButton")).click();

			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Success!", message);
		} finally {
			// Fechar o navegador ao concluir os testes
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();

		try {
			// Clicar em 'Add Todo'
			driver.findElement(By.id("addTodo")).click();

			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("29/05/2021");

			// Clicar em 'Save'
			driver.findElement(By.id("saveButton")).click();

			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Fill the task description", message);
		} finally {
			// Fechar o navegador ao concluir os testes
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();

		try {
			// Clicar em 'Add Todo'
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

			// Clicar em 'Save'
			driver.findElement(By.id("saveButton")).click();

			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Fill the due date", message);
		} finally {
			// Fechar o navegador ao concluir os testes
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();

		try {
			// Clicar em 'Add Todo'
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("29/05/2010");

			// Clicar em 'Save'
			driver.findElement(By.id("saveButton")).click();

			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			// Fechar o navegador ao concluir os testes
			driver.quit();
		}
	}

}
