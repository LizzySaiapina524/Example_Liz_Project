package techmock.selenide;
import technicalclasses.Selenide.Links;
import technicalclasses.Selenide.UICredentials;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static technicalclasses.Selenide.Links.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TechmockSelenide {

    @Test
    public void Test() throws InterruptedException {
        open("https://www.youtube.com");

        //$(By.cssSelector("div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > ytd-guide-renderer:nth-child(1) > div:nth-child(1) > ytd-guide-section-renderer:nth-child(1) > div:nth-child(2) > ytd-guide-entry-renderer:nth-child(1) > a:nth-child(1)"))
        // .shouldBe(visible);
        //$(By.className("ytd-guide-entry-renderer"))
        // .click();
        //$(By.xpath("//yt-formatted-string[contains(text(),'Главная')]"))
        // .shouldBe(clickable);
        $(By.className("yt-searchbox-input"))
                .setValue("Test")
                .pressEnter();
    }

}


