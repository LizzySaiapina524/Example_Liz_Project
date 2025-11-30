package cucumbersteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import technicalclasses.Selenide.UICredentials;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginSteps {   // <- public здесь
    private WebDriver driver;

    @Given("user login to JUT account")
    public void user_login_to_JUT_account() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://jut.su/");
    }

    @When("user enters NOT valid credentials")
    public void user_enters_not_valid_credentials() {
        driver.findElement(By.xpath("//a[@title='Форма авторизации']//span[contains(text(),'Войти')]")).click();
        driver.findElement(By.cssSelector("#login_input1")).sendKeys(UICredentials.TestAccount2.getLogin());
        driver.findElement(By.cssSelector("#login_input2")).sendKeys(UICredentials.TestAccount2.getPassword());
        driver.findElement(By.cssSelector("#login_submit")).click();
        driver.quit();
    }

    @Then("user authorization failed")
    public void user_authorized_successfully() {
        $(By.className("top_logo"))
                .shouldBe(visible);
        driver.quit();
    }
}


