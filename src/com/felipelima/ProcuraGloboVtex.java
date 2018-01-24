package com.felipelima;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProcuraGloboVtex {
	
	private WebDriver driver;
	
	// 0 - Firefox browser
	// 1 - IE browser
	// 2 - Chrome browser	
	int type = 0;
	
	// código de retorno HTTP
	int code;
	
	//Site que vai abrir
	String stringURL = "http://globo.com.br/";
	
	//Lista de resultados dos links
	List<String> resultadoVtex = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		
		//setup para o teste
		URL url = new URL(stringURL);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		code = connection.getResponseCode();
		
	}

	@After
	public void tearDown() throws Exception {
		
		driver.close();
		
	}

	@Test
	public void test() throws IOException {	
		
		//assert para a verificação do código
		assertTrue("Verificação do código 200", code == 200);
		
		//pesquisa pelo site e captura do retorno da pesquisa
		driver = Util.startBrowser(driver,type);
		driver.get(stringURL);		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Vtex");
		driver.findElement(By.xpath("//button[@type='submit' and contains(text(),'Buscar')]")).click();		
		List<WebElement> linksVtex = driver.findElements(By.xpath("//ul[@class='results__list']/li/div[1]/a"));
        
		//output dos resultados em txt
        PrintWriter writer = new PrintWriter(".//Resultado//resultados.txt", "UTF-8");
		
		for(int i=0; i< linksVtex.size(); i++){
			
			writer.println("Texto do weblement -" + i + "- > " + linksVtex.get(i).getAttribute("href"));		 
			
		}
		
		writer.close();
		
	}

}
