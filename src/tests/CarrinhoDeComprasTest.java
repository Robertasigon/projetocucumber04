package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class CarrinhoDeComprasTest {
	
	WebDriver driver;
	
	@Dado("^Acessar a loja virtual$")
	public void acessar_a_loja_virtual() {
	    
		//referenciar o driver do navegador
		System.setProperty("Webdriver.chrome.driver", "c:\\chromedriver.exe");
		
		//abrir o google chrome e acessar a página do projeto
		driver = new ChromeDriver();
		driver.get("http://www.lojaexemplodelivros.com.br/");
		 
		//maximinizar o navegador
		driver.manage().window().maximize();
	}

	@Dado("^Selecionar um produto \"([^\"]*)\"$")
	public void selecionar_um_produto(String arg1) {
	    
		//selecionar o produto que será adicionado no carrinho
		driver.findElement(By.xpath("//a[@title='"+ arg1 +"']")).click();
	}

	@Dado("^Informar a quantidade desejada (\\d+)$")
	public void informar_a_quantidade_desejada(int arg1) {
	   
		//digitar a quantidade desejada do produto
		driver.findElement(By.cssSelector("#qty")).sendKeys(String.valueOf(arg1));
	}

	@Quando("^Confirmar a compra do produto$")
	public void confirmar_a_compra_do_produto() {
	   
		//clicar no botão comprar
		driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.add-to-box > div > button")).click();
	}

	@Então("^O produto é adicionado no carrinho de compras$")
	public void o_produto_é_adicionado_no_carrinho_de_compras() {
	    
		//obtendo a mensagem exibida na página
		//comparando resultado esperado x resultado obtido
		String mensagem = driver.findElement(By.cssSelector("body > div > div > div.main-container.col1-layout > div > div > div > ul > li > ul > li > span")).getText();
		assertTrue(mensagem.contains("adicionado ao carrinho de compras"));
	
		//obtido o endereço da página para onde o usuário foi direcionado
		//comparando resultado esperado xresultaod obtido 
		String pagina = driver.getCurrentUrl();
		assertEquals(pagina,"http://www.lojaexemplodelivros.com.br/checkout/cart/");
		
		//fechar navegador
		driver.close();
		driver.quit();		
	
	}



}
