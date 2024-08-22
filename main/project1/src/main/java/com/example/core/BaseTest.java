package com.example.core;

import io.qameta.allure.Attachment;
import org.junit.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.example.utils.DriverFactory;

import static com.example.utils.DriverFactory.getDriver;

public class BaseTest {

    /**
     * Método para finalizar o teste, fechando o WebDriver.
     * Este método é anotado com @After para garantir que seja executado após cada teste.
     */
    @After
    public void tearDown() {
        DriverFactory.killDriver();
    }

    /**
     * Captura uma screenshot da tela atual e anexa ao relatório Allure.
     * @param screenshotName o nome da captura de tela a ser anexada.
     * @return A captura de tela em formato de array de bytes.
     */
    @Attachment(value = "{screenshotName}", type = "image/png")
    public byte[] takeScreenshot(String screenshotName) {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Maximiza a janela do navegador.
     */
    public void maximizeWindow() {
        getDriver().manage().window().maximize();
    }

    /**
     * Fecha a guia (aba) atual do navegador.
     */
    public void closeCurrentTab() {
        getDriver().close();
    }

    /**
     * Atualiza (recarrega) a página atual do navegador.
     */
    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    /**
     * Muda o controle do WebDriver para a janela com o handle fornecido.
     * @param handle o identificador da janela para onde mudar.
     */
    public void switchToWindowHandle(String handle) {
        getDriver().switchTo().window(handle);
    }

    /**
     * Retorna o identificador (handle) da janela atual.
     * @return o identificador da janela atual.
     */
    public String getCurrentWindowHandle() {
        return getDriver().getWindowHandle();
    }

    /**
     * Abre uma nova guia (aba) no navegador.
     */
    public void openNewTab() {
        getDriver().switchTo().newWindow(org.openqa.selenium.WindowType.TAB);
    }

    /**
     * Espera por um tempo determinado (em segundos).
     * @param timeInSeconds o tempo em segundos para esperar.
     */
    public void waitFor(long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
