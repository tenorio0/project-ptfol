package test.core;

import static test.core.DriverFactory.getDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {
	

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    static Date Data = new Date();  
	static String REPORTPATH =	"test-output/ReportTesteWeb_"+Data+"/report.html";
	
//	@After
	public  void tearDown() {	
		DriverFactory.killDRiver();	
	}
	
	
	@Test
	public void deveAbritSite(String url ) {
		getDriver().get(url);
		deveMaximizarAPagina();

	}
	public void deveMaximizarAPagina() {
		getDriver().manage().window().maximize();
	}
	
	public void deveFecharGuiaAtual() {
		getDriver().close();
	}
	public void deveDarRefreshNaPagina() {
		getDriver().navigate().refresh();
	}
	public void deveMudarHandleDaPagina(String Handle) {
		getDriver().switchTo().window(Handle);
	}
	public String deveGuardarOHandleDaPagina() {
		String x = getDriver().getWindowHandle();
		return x;	
	}
	public void deveAbrirNovaGuia() {
		getDriver().switchTo().newWindow(org.openqa.selenium.WindowType.TAB);		
	}
	
	public void esperar(long tempo) {
		try {
			long x = (tempo * 1000);
			Thread.sleep(x);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	
	public String gerarScreenShot( String NomePasta, String NomeArquivo) throws IOException  {
		File imagem = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		File destino = new File ("target/screenshots/"+NomePasta+"/"+NomeArquivo+"__"+Data+".png");
		String path = destino.getAbsolutePath();
		FileUtils.copyFile(imagem, destino);
		System.out.println(path);	
		return path;		
	}
	
}
