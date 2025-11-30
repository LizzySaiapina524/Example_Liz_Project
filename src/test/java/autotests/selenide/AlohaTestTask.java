package autotests.selenide;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;


public class AlohaTestTask {

    @Test
    public void closeActiveTabWithX() {
        // Открываем первую вкладку
        open("https://example.com");

        // Открываем вторую вкладку через WebDriver API
        webdriver().driver().getWebDriver()
                .switchTo()
                .newWindow(org.openqa.selenium.WindowType.TAB)
                .get("https://google.com");

        // Проверяем, что вкладок стало 2
        assertThat(webdriver().driver()
                .getWebDriver()
                .getWindowHandles()
                .size()).isEqualTo(2);

        // Получаем хэндлы
        List<String> handles = new ArrayList<>(webdriver().driver()
                .getWebDriver()
                .getWindowHandles());

        // Переключаемся на первую вкладку
        webdriver().driver()
                .getWebDriver()
                .switchTo()
                .window(handles.get(0));

        // Закрываем первую вкладку
        webdriver().driver().getWebDriver().close();

        // Проверяем, что осталась 1 вкладка
        assertThat(webdriver().driver()
                .getWebDriver()
                .getWindowHandles()
                .size()).isEqualTo(1);
    }

}
