package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import suporte.Web;
import pages.LoginPage;
import static org.junit.Assert.*;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioPageObjectsTestData.csv")
public class InformacoesUsuarioPageObjectsTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createChrome();
    }

    @Test
    public void testAdicionarUmaInformacaoDoUsuario_CamposPreenchidos_InformacaoAdicionada(@Param(name="user") String user, @Param(name="password") String password, @Param(name="value") String value, @Param(name="contact") String contact, @Param(name="message") String message){
        String textoNoToast = new LoginPage(navegador)
                .clickSingIn()
                .typeUser(user)
                .typePassword(password)
                .clickSignIn()
                .clicarMe()
                .clickMoreDataAboutYou()
                .clickAddMoreData()
                .addContact(value, contact)
                .capturarTextoToast();

        assertEquals(message, textoNoToast);
    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
