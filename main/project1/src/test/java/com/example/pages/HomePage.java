package com.example.pages;

import com.example.core.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    public void shouldOpenSite(String url) {
        getDriver().get(url);
    }

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    // Método para pesquisar um produto no site
    public void searchProduct(String productName) {
        // Localiza o campo de busca pelo id e insere o nome do produto
        getDriver().findElement(By.id("search_product")).sendKeys(productName);
        // Clica no botão de submissão da busca
        getDriver().findElement(By.id("submit_search")).click();
    }
}
