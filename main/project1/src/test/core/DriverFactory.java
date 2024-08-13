package test.core;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    
    // Instância estática do WebDriver
    private static WebDriver webDriver;

    /**
     * Obtém a instância do WebDriver. Se ainda não existir, cria uma nova instância.
     * @return a instância do WebDriver.
     */
    public static WebDriver getDriver() {
        if (webDriver == null) {
            createDriver();
        }
        return webDriver;
    }

    /**
     * Cria uma nova instância do WebDriver configurado com ChromeOptions.
     */
    private static void createDriver() {
        // Define o caminho para o driver do Chrome
        System.setProperty("webdriver.chrome.driver", "/Users/guilhermetenorio/Downloads/drivers/chromedriver114");

        // Configurações do Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Permite origens remotas para o WebDriver

        // Inicializa o WebDriver com as opções configuradas
        webDriver = new ChromeDriver(options);

        // Configura o tempo de espera implícito
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    /**
     * Encerra a instância do WebDriver, se existir, e a define como null.
     */
    public static void killDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
