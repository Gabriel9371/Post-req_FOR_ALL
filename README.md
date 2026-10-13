# Requisições Portátil

Ferramenta portátil e com interface gráfica para testar requisições HTTP, feita para ambientes onde não é possível instalar programas.

Na faculdade e no trabalho eu não tenho permissão de administrador, o que impede o uso de ferramentas tradicionais para testar APIs. Este projeto é um único arquivo `.jar` que roda direto, sem instalação e sem privilégios administrativos.

## Como usar

Requisito: **Java 17 ou superior**.

1. Baixe o `ReqResPortable.jar` na aba [Releases](../../releases).
2. Dê dois cliques no arquivo, ou rode no terminal:
 java -jar ReqResPortable.jar

 
## Funcionalidades

- Métodos GET, POST, PUT, PATCH e DELETE
- Headers e body da requisição editáveis
- Status, tempo de resposta, headers e body da resposta
- Tema escuro
- Mensagens de erro claras (URL inválida, timeout, falha de conexão)
- Sem dependências externas: usa apenas o JDK

## Compilando

Requer JDK 17+ e Maven: mvn clean package


O arquivo gerado fica em `target/ReqResPortable.jar`.

## Futuramente: 

- Formatação automática de JSON na resposta
- Histórico de requisições
- Autenticação e parâmetros de query
