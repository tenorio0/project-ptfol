package test.core;

import static test.core.DriverFactory.getDriver;

import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends BaseTest {

    /**
     * Clica em um elemento localizado pelo XPath fornecido.
     * @param xpath o XPath do elemento a ser clicado.
     */
    public void clickByXpath(String xpath) {
        getDriver().findElement(By.xpath(xpath)).click();
    }

    /**
     * Clica em um elemento se ele existir na página. Caso contrário, exibe uma mensagem.
     * @param by o localizador do elemento.
     * @param message a mensagem a ser exibida se o elemento não existir.
     */
    public void clickIfExists(By by, String message) {
        if (getDriver().findElements(by).size() > 0) {
            getDriver().findElement(by).click();
        } else {
            System.out.println(message);
        }
    }

    /**
     * Procura um texto em um elemento e faz scroll se necessário.
     * @param xpath o XPath do elemento para realizar o scroll.
     * @param searchTerms os termos de texto a serem procurados.
     */
    public void searchTextAndScroll(String xpath, String[] searchTerms) {
        WebElement scrollElement = getDriver().findElement(By.xpath(xpath));
        int maxScrolls = 4;

        for (String term : searchTerms) {
            boolean isTextFound = isElementPresent(By.xpath("//*[@text = '" + term + "']"), 1);
            int scrollCount = 0;

            while (!isTextFound && scrollCount < maxScrolls) {
                scrollInElement(scrollElement);
                scrollCount++;
                isTextFound = isElementPresent(By.xpath("//*[@text = '" + term + "']"), 1);
            }

            if (isTextFound) {
                System.out.println("Texto \"" + term + "\" encontrado.");
            } else {
                System.out.println("Texto \"" + term + "\" não encontrado.");
            }
        }
    }

    /**
     * Verifica se um texto está presente em um elemento.
     * @param element o elemento a ser verificado.
     * @param text o texto a ser verificado.
     * @return true se o texto estiver presente, false caso contrário.
     */
    private static boolean isTextPresentInElement(WebElement element, String text) {
        return element.getText().contains(text);
    }

    /**
     * Realiza um pequeno scroll dentro de um elemento.
     * @param element o elemento em que o scroll será realizado.
     */
    private static void scrollInElement(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).clickAndHold().moveByOffset(0, 20).release().perform();
    }

    /**
     * Digita um texto em um campo de entrada localizado pelo seletor fornecido.
     * @param by o localizador do campo de entrada.
     * @param text o texto a ser digitado.
     */
    public void typeText(By by, String text) {
        getDriver().findElement(by).sendKeys(text);
    }

    /**
     * Obtém o texto de um elemento localizado pelo seletor fornecido.
     * @param by o localizador do elemento.
     * @return o texto do elemento.
     */
    public String getText(By by) {
        return getDriver().findElement(by).getText();
    }

    /**
     * Obtém o texto de um elemento e o divide com base no separador fornecido.
     * @param by o localizador do elemento.
     * @param separator o separador utilizado para dividir o texto.
     * @return uma array de strings com os textos separados.
     */
    public String[] getTextSplit(By by, String separator) {
        return getDriver().findElement(by).getText().split(separator);
    }

    /**
     * Verifica se um elemento existe na página.
     * @param by o localizador do elemento.
     * @param maxWaitTime o tempo máximo de espera em segundos.
     * @return true se o elemento existir, false caso contrário.
     */
    public boolean isElementPresent(By by, int maxWaitTime) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(maxWaitTime));
        boolean exists = getDriver().findElements(by).size() > 0;
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60)); // Resetando o tempo de espera implícito
        return exists;
    }

    /**
     * Localiza um elemento pelo XPath fornecido.
     * @param xpath o XPath do elemento.
     * @return o elemento localizado.
     */
    public WebElement findElementByXpath(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    /**
     * Localiza um elemento pelo texto fornecido.
     * @param text o texto do elemento.
     * @return o elemento localizado.
     */
    public WebElement findElementByText(String text) {
        return getDriver().findElement(By.xpath("//*[@text = '" + text + "']"));
    }

    /**
     * Localiza um elemento pelo ID fornecido.
     * @param id o ID do elemento.
     * @return o elemento localizado.
     */
    public WebElement findElementById(String id) {
        return getDriver().findElement(By.id(id));
    }

    /**
     * Verifica se um elemento localizado pelo XPath está visível (enabled).
     * @param xpath o XPath do elemento.
     */
    public void assertElementIsEnabled(String xpath) {
        Assert.assertTrue(findElementByXpath(xpath)
                .getAttribute("enabled").equals("true"));
    }

    /**
     * Verifica se um elemento localizado pelo XPath está desativado (disabled).
     * @param xpath o XPath do elemento.
     */
    public void assertElementIsDisabled(String xpath) {
        Assert.assertFalse(findElementByXpath(xpath)
                .getAttribute("enabled").equals("true"));
    }

    /**
     * Verifica se um botão localizado pelo XPath é clicável.
     * @param xpath o XPath do botão.
     */
    public void assertButtonIsClickable(String xpath) {
        Assert.assertTrue(findElementByXpath(xpath)
                .getAttribute("clickable").equals("true"));
    }

    /**
     * Verifica se um botão localizado pelo XPath não é clicável.
     * @param xpath o XPath do botão.
     */
    public void assertButtonIsNotClickable(String xpath) {
        Assert.assertFalse(findElementByXpath(xpath)
                .getAttribute("clickable").equals("true"));
    }

    /**
     * Espera até que um elemento seja clicável.
     * @param by o localizador do elemento.
     * @param maxWaitTime o tempo máximo de espera em segundos.
     */
    public void waitUntilClickable(By by, int maxWaitTime) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(maxWaitTime));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Espera até que um elemento esteja presente no DOM e ativado.
     * @param by o localizador do elemento.
     * @param maxWaitTime o tempo máximo de espera em segundos.
     */
    public void waitUntilPresent(By by, int maxWaitTime) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(maxWaitTime));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Espera até que um elemento esteja desativado/invisível.
     * @param by o localizador do elemento.
     * @param maxWaitTime o tempo máximo de espera em segundos.
     */
    public void waitUntilInvisible(By by, int maxWaitTime) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(maxWaitTime));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * Espera até que um elemento esteja visível.
     * @param by o localizador do elemento.
     * @param maxWaitTime o tempo máximo de espera em segundos.
     */
    public void waitUntilVisible(By by, int maxWaitTime) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(maxWaitTime));
        wait.until(ExpectedConditions.attributeContains(by, "displayed", "true"));
    }
}
