package com.example.pages;

import com.example.core.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private By searchBox = By.id("search_product");
    private By searchButton = By.id("submit_search");

    public void searchProduct(String productName) {
        write(searchBox, productName);
        clickByXpath(searchButton.toString());
    }
}

