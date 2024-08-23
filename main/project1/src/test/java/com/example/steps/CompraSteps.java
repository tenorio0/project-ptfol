package com.example.steps;

import com.example.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class CompraSteps {

    HomePage homePage = new HomePage();

    @Given("que estou na página inicial")
    public void que_estou_na_pagina_inicial() {
        System.out.println("Teste rodando");
        homePage.shouldOpenSite("https://www.automationexercise.com/");
    }

    @Then("eu deveria ver o título da página como {string}")
    public void eu_deveria_ver_o_titulo_da_pagina_como(String tituloEsperado) {
        String tituloAtual = homePage.getPageTitle();
        Assert.assertEquals(tituloEsperado, tituloAtual);
    }
}
