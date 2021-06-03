package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() throws MalformedURLException {
		WebDriver driver = new ChromeDriver();
		/*
		 * DesiredCapabilities cap = DesiredCapabilities.chrome();
		 * 
		 * WebDriver driver = new RemoteWebDriver(new
		 * URL("http://172.17.0.1:4444/wd/hub"), cap);
		 */

		driver.navigate().to("http://localhost:8088/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();

		try {
			// Clicar em 'Add Todo'
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("01/06/2022");

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
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
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
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
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
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
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

	@Test
	public void deveRemoverTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();

		try {
			// Incluir uma tarefa para remoção
			driver.findElement(By.id("addTodo")).click();

			driver.findElement(By.id("task")).sendKeys("Teste via selenium para exclusão");

			driver.findElement(By.id("dueDate")).sendKeys("02/06/2022");

			driver.findElement(By.id("saveButton")).click();

			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Success!", message);

			// Testar a remocão da tarefa
			driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();

			message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Success!", message);
		} finally {
			driver.quit();
		}
	}

}
