package com.example.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static com.example.utils.DriverFactory.getDriver;

public class BasePage {
    // Método para abrir o site na URL fornecida e maximizar a janela
    public void shouldOpenSite(String url) {
        getDriver().get(url);
        maximizeWindow();
    }

    // Maximiza a janela do navegador
    public void maximizeWindow() {
        getDriver().manage().window().maximize();
    }

    // Outros métodos comuns para interação com a página...

    public void clickByXpath(String xpath) {
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public void clickIfExists(By by, String message) {
        if (getDriver().findElements(by).size() > 0) {
            getDriver().findElement(by).click();
        } else {
            System.out.println(message);
        }
    }

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

    private static void smallScrollInElement(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).clickAndHold().moveByOffset(0, 20).release().perform();
    }

    public void write(By by, String text) {
        getDriver().findElement(by).sendKeys(text);
    }

    public String getText(By by) {
        return getDriver().findElement(by).getText();
    }

    public String[] getTextSplit(By by, String separator) {
        return getDriver().findElement(by).getText().split(separator);
    }

    public boolean exists(By by, int maxWaitTime) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(maxWaitTime));
        boolean found = getDriver().findElements(by).size() > 0;
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        return found;
    }

    public WebElement findByXpath(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    public WebElement findByText(String text) {
        return getDriver().findElement(By.xpath("//*[@text = '" + text + "']"));
    }

    public WebElement findById(String id) {
        return getDriver().findElement(By.id(id));
    }

    public void checkIfVisible(String xpath) {
        assert findByXpath(xpath).getAttribute("enabled").equals("true");
    }

    public void checkIfDisabled(String xpath) {
        assert !findByXpath(xpath).getAttribute("enabled").equals("true");
    }

    public void checkIfClickable(String xpath) {
        assert findByXpath(xpath).getAttribute("clickable").equals("true");
    }

    public void checkIfNotClickable(String xpath) {
        assert !findByXpath(xpath).getAttribute("clickable").equals("true");
    }

    public void waitUntilClickable(By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitUntilPresent(By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitUntilInvisible(By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitUntilVisible(By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.attributeContains(by, "displayed", "true"));
    }
}