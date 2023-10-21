package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage {

    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage typeUser(String user){
        navegador.findElement(By.id("login-sign-in")).sendKeys(user);

        return new LoginFormPage(navegador);
    }
    public LoginFormPage typePassword(String password){
        navegador.findElement(By.id("password-sign-in")).sendKeys(password);

        return new LoginFormPage(navegador);
    }

    public SecretaPage clickSignIn(){
        navegador.findElement(By.id("btn-submit-sign-in")).click();

        return new SecretaPage(navegador);
    }

    //podemos realizar esses passos de modo funcional ao juntar todos
    public SecretaPage clickLogIn(String user, String password){
        typeUser(user);
        typePassword(password);

        return clickSignIn();
    }


}
