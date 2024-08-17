package com.example.steps;

import com.example.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CompraSteps {

    HomePage homePage = new HomePage();

    @Given("que estou na página inicial")
    public void que_estou_na_pagina_inicial() {
        homePage.shouldOpenSite("https://www.automationexercise.com/");
    }

    @When("eu procuro por um produto chamado {string}")
    public void eu_procuro_por_um_produto_chamado(String produto) {
        homePage.searchProduct(produto);
    }

    @Then("eu deveria ver resultados relacionados ao produto")
    public void eu_deveria_ver_resultados_relacionados_ao_produto() {
        // Validação de que os resultados aparecem (implementação necessária)
    }
}
