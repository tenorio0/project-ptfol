package test.core;

import static test.core.DriverFactory.getDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

    // Formatação para data e hora para nomeação de arquivos e pastas
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");  
    private static final Date CURRENT_DATE = new Date();
    private static final String REPORT_PATH = "test-output/ReportTesteWeb_" + DATE_FORMATTER.format(CURRENT_DATE) + "/report.html";

    /**
     * Método para finalizar o teste, fechando o WebDriver.
     * Este método é anotado com @After para garantir que seja executado após cada teste.
     */
    @After
    public void tearDown() {	
        DriverFactory.killDriver();	
    }
	
    /**
     * Abre o site na URL fornecida e maximiza a janela do navegador.
     * @param url a URL do site a ser aberto.
     */
    @Test
    public void shouldOpenSite(String url) {
        getDriver().get(url);
        maximizeWindow();
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

    /**
     * Gera um screenshot da tela atual e salva no diretório especificado.
     * @param folderName o nome da pasta onde o screenshot será salvo.
     * @param fileName o nome do arquivo de screenshot.
     * @return o caminho absoluto do arquivo de screenshot salvo.
     * @throws IOException se ocorrer um erro durante a gravação do arquivo.
     */
    public String takeScreenshot(String folderName, String fileName) throws IOException {
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String formattedDate = DATE_FORMATTER.format(new Date());
        File destination = new File("target/screenshots/" + folderName + "/" + fileName + "__" + formattedDate + ".png");
        String path = destination.getAbsolutePath();
        FileUtils.copyFile(screenshot, destination);
        System.out.println("Screenshot saved at: " + path);
        return path;		
    }
}
