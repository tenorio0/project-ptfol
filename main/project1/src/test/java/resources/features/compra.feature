Feature: Compra de produtos

  Scenario: Pesquisar um produto
    Given que estou na página inicial
    When eu procuro por um produto chamado "T-shirt"
    Then eu deveria ver resultados relacionados ao produto
