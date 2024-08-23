package com.example.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.utils.DriverFactory;

import java.time.Duration;



public class BasePage extends DriverFactory{

    // Abre o site na URL fornecida e maximiza a janela
    public void shouldOpenSite(String url) {
        getDriver().get(url);
        maximizeWindow();
    }

    // Maximiza a janela do navegador
    public void maximizeWindow() {
        getDriver().manage().window().maximize();
    }

    // Clica em um elemento encontrado pelo XPath
    public void clickByXpath(String xpath) {
        getDriver().findElement(By.xpath(xpath)).click();
    }

    // Clica em um elemento se ele existir
    public void clickIfExists(By by, String message) {
        if (getDriver().findElements(by).size() > 0) {
            getDriver().findElement(by).click();
        } else {
            System.out.println(message);
        }
    }

    // Rola e procura texto em um elemento
    public void scrollAndFindText(String xpath, String[] searchTerms) {
        WebElement scrollElement = getDriver().findElement(By.xpath(xpath));
        int maxScrolls = 4;
        for (String text : searchTerms) {
            boolean textFound = exists(By.xpath("//*[@text = '" + text + "']"), 1);
            int scrollCount = 0;
            while (!textFound && scrollCount < maxScrolls) {
                smallScrollInElement(scrollElement);
                scrollCount++;
                textFound = exists(By.xpath("//*[@text = '" + text + "']"), 1);
            }
            if (textFound) {
                System.out.println("Text \"" + text + "\" found.");
            } else {
                System.out.println("Text \"" + text + "\" not found.");
            }
        }
    }

    // Faz um pequeno scroll dentro de um elemento
    private void smallScrollInElement(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).clickAndHold().moveByOffset(0, 20).release().perform();
    }

    // Escreve texto em um campo localizado por By
    public void write(By by, String text) {
        getDriver().findElement(by).sendKeys(text);
    }

    // Obtém o texto de um elemento localizado por By
    public String getText(By by) {
        return getDriver().findElement(by).getText();
    }

    // Obtém o texto de um elemento e o divide com base no separador fornecido
    public String[] getTextSplit(By by, String separator) {
        return getDriver().findElement(by).getText().split(separator);
    }

    // Verifica se um elemento existe
    public boolean exists(By by, int maxWaitTime) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(maxWaitTime));
        boolean found = getDriver().findElements(by).size() > 0;
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));  // Retorna o timeout padrão
        return found;
    }

    // Localiza um elemento pelo XPath
    public WebElement findByXpath(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    // Localiza um elemento pelo texto
    public WebElement findByText(String text) {
        return getDriver().findElement(By.xpath("//*[@text = '" + text + "']"));
    }

    // Localiza um elemento pelo ID
    public WebElement findById(String id) {
        return getDriver().findElement(By.id(id));
    }

    // Verifica se um elemento está visível
    public void checkIfVisible(String xpath) {
        assert findByXpath(xpath).getAttribute("enabled").equals("true");
    }

    // Verifica se um elemento está desativado
    public void checkIfDisabled(String xpath) {
        assert !findByXpath(xpath).getAttribute("enabled").equals("true");
    }

    // Verifica se um botão está clicável
    public void checkIfClickable(String xpath) {
        assert findByXpath(xpath).getAttribute("clickable").equals("true");
    }

    // Verifica se um botão não está clicável
    public void checkIfNotClickable(String xpath) {
        assert !findByXpath(xpath).getAttribute("clickable").equals("true");
    }

    // Espera até que um elemento seja clicável
    public void waitUntilClickable(By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    // Espera até que um elemento esteja presente
    public void waitUntilPresent(By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    // Espera até que um elemento seja invisível
    public void waitUntilInvisible(By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    // Espera até que um elemento esteja visível
    public void waitUntilVisible(By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.attributeContains(by, "displayed", "true"));
    }
}
