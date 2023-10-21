package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//página base, todas as páginas tem acesso
public class BasePage {
    protected WebDriver navegador;

    public BasePage(WebDriver navegador){
        this.navegador = navegador;
    }

    public String capturarTextoToast() {
        return navegador.findElement(By.id("toast-container")).getText();
    }
}
