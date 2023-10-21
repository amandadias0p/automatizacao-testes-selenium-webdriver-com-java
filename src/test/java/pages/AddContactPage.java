package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddContactPage extends BasePage {

    public AddContactPage(WebDriver navegador) {
        super(navegador);
    }

    public AddContactPage chooseContact(String value) {
        //selecionar a opção com value 'phone' do combobox com name 'type' dentro do forms
        WebElement campoType = navegador.findElement(By.id("addmoredata")).findElement(By.name("type"));
        new Select(campoType).selectByValue(value);

        return this;
    }

    public AddContactPage typeContact(String contact) {
        //digitar no campo com name 'contact' o texto '+554412344321'
        navegador.findElement(By.id("addmoredata")).findElement(By.xpath("//div[@id=\"addmoredata\"]//input[@name=\"contact\"]")).sendKeys(contact);

        return this;
    }

    public MePage clickButtonSave() {
        navegador.findElement(By.id("addmoredata")).findElement(By.linkText("SAVE")).click();

        return new MePage(navegador);
    }

    public MePage addContact(String value, String contact) {
        chooseContact(value);
        typeContact(contact);
        clickButtonSave();

        return new MePage(navegador);
    }
}