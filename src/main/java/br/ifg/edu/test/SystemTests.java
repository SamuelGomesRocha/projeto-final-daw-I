package br.ifg.edu.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SystemTests {

	private WebDriver browser;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\PICHAU\\Desktop\\chromedriver.exe");
		browser = new ChromeDriver();
	}
	
	@After
	public void end() {
		browser.close();
	}
	
	@Test
	public void deveLogarNoSistema() {
		browser.get("http://localhost:8080/daw-i-leilao/port?action=/listar");
		WebElement user_name = browser.findElement(By.name("input-user"));
		user_name.sendKeys("samuka");
		WebElement password = browser.findElement(By.name("input-senha"));
		password.sendKeys("senha");
		WebElement buttonSubmit = browser.findElement(By.id("input-button"));
		buttonSubmit.submit();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void deveCadastrarUmLeilao() {
		deveLogarNoSistema();
		WebElement btnNewLeilao = browser.findElement(By.className("btn"));
		btnNewLeilao.click();
		
		WebElement nomeLeilao = browser.findElement(By.name("input-item"));
		nomeLeilao.sendKeys("Phone");
		WebElement lanceMinimo = browser.findElement(By.name("input-lanceMinimo"));
		lanceMinimo.sendKeys("5000");
		WebElement dataExpiracao = browser.findElement(By.name("input-dataExpiracao"));
		dataExpiracao.sendKeys("2021-12-31");
		
		WebElement salvaLeilao = browser.findElement(By.id("input-button"));
		salvaLeilao.submit();
		
		WebElement retornarALista = browser.findElement(By.tagName("h1"));
		retornarALista.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@Test
	public void realizaLogout() {
		deveLogarNoSistema();
		WebElement btnSair = browser.findElement(By.tagName("h2"));
		btnSair.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void deveAcessarOLeilaoDaPrimeiraTuplaDaTable() {
		Actions act = new Actions(browser);
		deveLogarNoSistema();
		WebElement tableRow = browser.findElement(By.id("trLeilas"));
		act.doubleClick(tableRow).perform();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void deveEfetuarUmLanceNoLeilaoContidoNaPrimeiraTupla() {
		//Actions act = new Actions(browser);
		deveAcessarOLeilaoDaPrimeiraTuplaDaTable();
		WebElement submitLance = browser.findElement(By.name("input-valorLance"));
		submitLance.sendKeys("9000");
		submitLance.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void excluirPrimeiraLinhaDaTabela() {
		deveLogarNoSistema();
		WebElement deleteOpt = browser.findElement(By.tagName("a"));		
		deleteOpt.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
