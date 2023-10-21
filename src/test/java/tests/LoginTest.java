package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    //expondo a variável 'navegador' a todos os métodos da classe para utilizar
    private WebDriver navegador;

    @Before
    public void setUp() {
        //instanciando o chromedriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Analise\\drivers\\chromedriver.exe");

        //janela maximizada
        navegador.manage().window().maximize();

        //colocar um intervalo de tempo para dar tempo do navegador carregar
        //o selenium vai esperar um tempo definido para o elemento estar disponível para interação
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //navegando para a página do TaskIt!
        navegador.get("http://www.juliodelima.com.br/taskit/");
    }

    @Test
    public void testRealizarLogin_CredenciaisCorretas_LoginRealizado() {

        //clicar no link que possui o texto 'Sign in'
        navegador.findElement(By.linkText("Sign in")).click();

        //definindo o elemento com id singinbox em uma variável pois iremos utilizar ele mais de uma vez
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //digitar no campo com id 'login-sign-in' o usuário 'amandadp'
        formularioSignInBox.findElement(By.xpath("//*[@id=\"login-sign-in\"]")).sendKeys("amandadp");

        //digitar no campo com id 'password-sign-in' a senha 'testeteste'*/
        formularioSignInBox.findElement(By.id("password-sign-in")).sendKeys("testeteste");

        //clicar no link com o texto com id 'btn-submit-sign-in'
        navegador.findElement(By.id("btn-submit-sign-in")).click();

        //validar se o login foi realizado corretamente verificando se o texto 'Hi, Amanda' aparece no texto com class 'me'
        WebElement me = navegador.findElement(By.className("me"));
        String textoNoElementoMe = me.getText();
        assertEquals("Hi, Amanda", textoNoElementoMe);

    }

    @Test
    public void testAdicionarUmaInformacaoDoUsuario_CamposPreenchidos_InformacaoAdicionada() {
        //repetir o login
        navegador.findElement(By.linkText("Sign in")).click();
        navegador.findElement(By.id("login-sign-in")).sendKeys("amandadp");
        navegador.findElement(By.id("password-sign-in")).sendKeys("testeteste");
        navegador.findElement(By.id("btn-submit-sign-in")).click();

        //clicar no link com texto 'Hi, Amanda' que possui a class 'me'
        navegador.findElement(By.className("me")).click();

        //clicar no link com texto 'MORE DATA ABOUT YOU'
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        //clicar no botão 'Add more data' através do xpath
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //identificar o popUp com div 'addmoredata'
        WebElement popUpAddMoreData = navegador.findElement(By.id("addmoredata"));

        //selecionar a opção com value 'phone' do combobox com name 'type' dentro do popUp
        WebElement campoType = popUpAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByValue("phone");

        //digitar no campo com name 'contact' o texto '+554412344321'
        popUpAddMoreData.findElement(By.xpath("//div[@id=\"addmoredata\"]//input[@name=\"contact\"]")).sendKeys("+554412344321");

        //clicar no link com texto 'Save' que está dentro da div com id 'addmoredata'
        popUpAddMoreData.findElement(By.linkText("SAVE")).click();

        //validar se toast com id 'toast-container' possui o texto 'You contact has been added!'
        WebElement toastPopUp = navegador.findElement(By.id("toast-container"));
        String textoNoToastPopUp = toastPopUp.getText();
        assertEquals("Your contact has been added!", textoNoToastPopUp);
    }

    @After
    public void tearDown() {
        navegador.quit();
    }
}
