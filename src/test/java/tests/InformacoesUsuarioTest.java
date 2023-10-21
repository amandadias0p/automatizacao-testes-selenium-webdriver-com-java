package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Web;

import java.time.Duration;

//biblioteca do EasyTest para realizar DDT
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv")

public class InformacoesUsuarioTest {

    //expondo a variável 'navegador' a todos os métodos da classe para utilizar
    private WebDriver navegador;

    //rule para conseguir usar o método getMethodName
    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        navegador = Web.createChrome();

        //realizar o login, teste feito na classe LoginTest.java
        navegador.findElement(By.linkText("Sign in")).click();
        navegador.findElement(By.id("login-sign-in")).sendKeys("amandadp");
        navegador.findElement(By.id("password-sign-in")).sendKeys("testeteste");
        navegador.findElement(By.id("btn-submit-sign-in")).click();

        //clicar no link com texto 'Hi, Amanda' que possui a class 'me'
        navegador.findElement(By.className("me")).click();

        //clicar no link com texto 'MORE DATA ABOUT YOU'
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    /**
     * Teste para validar a adição de um contato no perfil do usuário.
     * O contato é adicionado com sucesso
     */

    @Test
    public void testAdicionarUmaInformacaoDoUsuario_CamposPreenchidos_InformacaoAdicionada(@Param(name="tipo")String tipo, @Param(name="contato") String contato, @Param(name="mensagem") String mensagem) {
        //clicar no botão 'Add more data' através do xpath
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //identificar o popUp com div 'addmoredata'
        WebElement popUpAddMoreData = navegador.findElement(By.id("addmoredata"));

        //selecionar a opção com value 'phone' do combobox com name 'type' dentro do popUp
        WebElement campoType = popUpAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByValue(tipo);

        //digitar no campo com name 'contact' o texto '+554412344321'
        popUpAddMoreData.findElement(By.xpath("//div[@id=\"addmoredata\"]//input[@name=\"contact\"]")).sendKeys(contato);

        //clicar no link com texto 'Save' que está dentro da div com id 'addmoredata'
        popUpAddMoreData.findElement(By.linkText("SAVE")).click();

        //validar se toast com id 'toast-container' possui o texto 'You contact has been added!'
        WebElement toastPopAdicionar = navegador.findElement(By.id("toast-container"));
        String textoNoToastPopAdicionar = toastPopAdicionar.getText();
        assertEquals(mensagem, textoNoToastPopAdicionar);

        //remover o contato
        navegador.findElement(By.xpath("//span[text()=\""+contato+"\"]/following-sibling::a")).click();
        navegador.switchTo().alert().accept();
        navegador.findElement(By.linkText("Logout")).click();
    }

    /**
     * Teste para testar a remoção de um contato no perfil de um usuário.
     * O contato é inserido manualmente para evitar a dependência do teste anterior (adicionar contato).
     * O contato é removido com sucesso
     */

    //@Test
    public void testRemoverUmContatoDeUmUsuario_ContatoOk_ContatoRemovido() {
        //clicar no elemento pelo seu xpath //span[text()="+554449784978"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+554449784978\"]/following-sibling::a")).click();

        //confirmar a ação na janela javascript
        navegador.switchTo().alert().accept();

        //tirar print da tela após aceitar o javascript
//        String screenshotArquivo = "C:\\Users\\Analise\\prints-teste\\taskit\\" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
//        Screenshot.tirar(navegador,screenshotArquivo);


        //confirmar que o toast apresenta a mensagem 'Rest in peace, dear phone!'
        WebElement toastPopExclusao = navegador.findElement(By.id("toast-container"));
        String textoNoToastPopExclusao = toastPopExclusao.getText();
        assertEquals("Rest in peace, dear phone!", textoNoToastPopExclusao);

        //aguardar até 10s para o toast desaparecer - timeout explicito
        WebDriverWait aguardar = new WebDriverWait(navegador, Duration.ofSeconds(10));

        //script aguarda até que o elemento desapareça
        aguardar.until(ExpectedConditions.stalenessOf(toastPopExclusao));

        //clicar no link com texto 'Logout'
        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() {
        //fechando o navegador
        navegador.quit();
    }
}
