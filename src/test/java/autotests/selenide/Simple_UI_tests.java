package autotests.selenide;

import technicalclasses.Selenide.UI_Credentials;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static technicalclasses.Selenide.Links.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Simple_UI_tests {

    //Тестируем гугл поиск
//    @Test
//    public void UserCanSearchAnyKeywords(){
//
//        open(GOOGLE_URL);
//        $(By.name("q")).setValue("Selenide").pressEnter();
//
//        // получили массив с результатами
//        $$("#rcnt").shouldHave(CollectionCondition.size(0));
//
//        $(By.xpath("//div[@id='rcnt']")).shouldHave(text("Selenide: concise UI Tests in Java"));
//    }

    //Проверяем что 8 серия Ванпанчмана вышла и её можно посмотреть по клику
    @Test
    public void OpportunityToWatchSeries() {
        open(JUT_URL);

        $(By.xpath("//body/div[5]/div[1]/div[1]/div[2]/div[3]/a[1]/div[1]/div[2]"))
                .click();
        $(By.xpath("/html[1]/body[1]/div[5]/div[1]/div[1]/div[1]/div[1]/h1[1]"))
                .shouldHave(Condition.text("Смотреть Ванпанчмен все серии и сезоны"));
        $(By.xpath("/html[1]/body[1]/div[5]/div[1]/div[1]/div[1]/div[2]"))
                .shouldHave(Condition.text("8 серия"));
        $(By.xpath("//body/div[5]/div[1]/div[1]/div[1]/div[2]/a[8]"))
                .shouldHave(Condition.clickable);
    }

    // Заходим на сайт музыкального дистрибьютера, логинимся и проверяем виджет с количеством релизов
    @Test
    public void Forms() {
        open(SONG_DELIVERY_URL);

        // Проверки видимого контента на странице
        $(By.className("login-main"))
                .shouldBe(visible);
        $(By.className("login-main"))
                .shouldHave(text("Авторизация"));
        $(By.className("btn-primary"))
                .shouldHave(Condition.text("Войти"))
                .shouldBe(clickable);

        //Авторизация
        $(By.id("login"))
                .setValue(UI_Credentials.TestAccount1.getLogin());

        $(By.id("pass"))
                .setValue(UI_Credentials.TestAccount1.getPassword());

        $(By.className("btn-primary"))
                .click();

        $(By.className("card-body"))
                .shouldHave(text("Релизов"))
                .shouldHave(text("12"));
    }

    @Test
    public void Dropdown (){
        open(JUT_URL);

        //Действие
        $(By.xpath("//ul[@class='top_nav']//a[@title='Классификации техник'][contains(text(),'Типы')]"))
                .shouldBe(clickable)
                .click();

        //Проверка
        $(By.xpath("//div[@class='header z_fix_header']//li[1]//ul[1]"))
                .shouldBe(visible)
                .shouldHave(text("Ниндзюцу"))
                .shouldHave(text("Сендзюцу"));

        //Действие
        $(By.cssSelector("ul[class='downer_nav clear'] a[title='Ниндзюцу']"))
                .shouldBe(clickable)
                .click();

        //Проверка
        $(By.xpath("//img[@title='Водяные выстрелы']"))
                .shouldBe(visible)
                .shouldBe(clickable);

        //Действие
        $(By.cssSelector("body > div:nth-child(15) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > span:nth-child(2)"))
                .click();

    }

    @Test
    public void Hover(){
        open(JUT_URL);

        //Проверим  отображение информации при наведении курсора


        $(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[6]/a[1]"))
                .hover();

        $(By.xpath("//div[@class='header z_fix_header']//li[6]//ul[1]//li[1]//a[1][1]//b[1]"))
                .shouldBe(visible)
                .shouldBe(clickable);

        $(By.xpath("(//b[@class='s_dust'])[6]"))
                .shouldBe(visible)
                .shouldBe(clickable);

    }

    @Test
    public void Authorization(){
        open(JUT_URL);

        //Форма соответствует требованиям
        $(By.xpath("//a[@title='Форма авторизации']//span[contains(text(),'Войти')]"))
                .click();
        $(By.cssSelector("#vk_auth"))
                .shouldBe(visible);

        $(By.cssSelector("#login_input1"))
                .shouldBe(visible);
        $(By.cssSelector("#login_input2"))
                .shouldBe(visible);
        $(By.cssSelector("#login_submit"))
                .shouldBe(visible)
                .shouldBe(clickable);
        $(By.cssSelector("a[href='/lostpassword.html']"))
                .shouldBe(visible)
                .shouldBe(clickable);
        $(By.cssSelector("a[href='/register.html']"))
                .shouldBe(visible)
                .shouldBe(clickable);
        $(By.cssSelector("div[id='auth_via_vk'] div span"))
                .shouldBe(visible)
                .shouldBe(clickable);
        $(By.cssSelector(".tg-login-btn.tg_link"))
                .shouldBe(visible)
                .shouldBe(clickable);

        //Сработала ли маршрутизация
        $(By.xpath("//a[contains(text(),'Регистрация')]"))
                .click();
        $(By.xpath("//div[@class='newsTitle']"))
                .shouldBe(visible);

        $(By.xpath("//input[@id='name']"))
                .setValue(UI_Credentials.TestAccount3.getLogin());
        $(By.xpath("//input[@name='password1']"))
                .setValue(UI_Credentials.TestAccount3.getPassword());
        $(By.xpath("//input[@name='email']"))
                .setValue(UI_Credentials.TestAccount3.getEmail());
        $(By.xpath("//input[@name='question_answer']"))
                .setValue(UI_Credentials.TestAccount3.getCodephrase());
        $(By.xpath("//span[contains(text(),'Отправить')]"))
                .click();

        //Авторизация
        open(JUT_URL);

        $(By.xpath("//a[@title='Форма авторизации']//span[contains(text(),'Войти')]"))
                .click();
        $(By.cssSelector("#vk_auth"))
                .shouldBe(visible);
        $(By.cssSelector("#login_input1"))
                .setValue(UI_Credentials.TestAccount2.getLogin());
        $(By.cssSelector("#login_input2"))
                .setValue(UI_Credentials.TestAccount2.getPassword());
        $(By.xpath("//input[@id='login_submit']"))
                .click();
    }
}
