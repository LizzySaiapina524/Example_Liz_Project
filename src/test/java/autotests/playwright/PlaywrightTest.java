package autotests.playwright;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.testng.Assert.assertTrue;


public class PlaywrightTest {

    static Playwright playwright;
    static Browser browser;
    Page page;

    @BeforeAll
    static void setupAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void teardownAll() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void setup() {
        BrowserContext context = browser.newContext();
        page = context.newPage();
    }

    @Test
    void hoverTest() {
        page.navigate("https://jut.su/anime");

        // Наводим курсор на элемент
        page.locator("xpath=/html/body/div[2]/div/ul/li[6]/a").hover();

        // Проверяем видимость и кликабельность первого элемента
        Locator firstElement = page.locator("//div[@class='header z_fix_header']//li[6]//ul/li[1]/a/b");
        assertTrue(firstElement.isVisible(), "Элемент должен быть видимым");
        assertTrue(firstElement.isEnabled(), "Элемент должен быть кликабельным");

        // Проверяем видимость и кликабельность второго элемента
        Locator secondElement = page.locator("(//b[@class='s_dust'])[6]");
        assertTrue(secondElement.isVisible(), "Элемент должен быть видимым");
        assertTrue(secondElement.isEnabled(), "Элемент должен быть кликабельным");
    }
}
