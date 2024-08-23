Feature: Validação da página inicial

  Scenario: Verificar se a página inicial carrega corretamente
    Given que estou na página inicial
    Then eu deveria ver o título da página como "Automation Exercise"
