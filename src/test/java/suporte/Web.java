package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

//abriga um ou mais métodos relacionados a Web
public class Web {
    public static WebDriver createChrome() {

        //instanciando o chromedriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Analise\\drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        //abre o navegador
        //navegador = new ChromeDriver();

        //janela maximizada
        navegador.manage().window().maximize();

        //colocar um intervalo de tempo para dar tempo do navegador carregar
        //o selenium vai esperar um tempo definido para o elemento estar disponível para interação
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //navegando para a página do TaskIt!
        navegador.get("http://www.juliodelima.com.br/taskit/");

        return navegador;
    }
}
