# Requisições Portátil

Ferramenta portátil e com interface gráfica para testar requisições HTTP, feita para ambientes onde não é possível instalar programas.

Na faculdade e no trabalho eu não tenho permissão de administrador, o que impede o uso de ferramentas tradicionais para testar APIs. Este projeto roda direto de uma pasta, sem instalação e sem privilégios administrativos.

## Como usar

Requisito: **Java 17 ou superior** (basta o `java` estar disponível no computador).

1. Baixe o `ReqResPortable.zip` na aba [Releases](../../releases).
2. Extraia o zip em qualquer pasta (a Área de Trabalho, por exemplo).
3. Dê dois cliques em **`ReqResPortable.bat`**.

> **Importante:** o `.bat` e o `.jar` precisam ficar na mesma pasta.

Se preferir pelo terminal:

    java -jar ReqResPortable.jar

## Por que existe um `.bat`?

Em alguns computadores o Windows associa arquivos `.jar` a uma versão antiga do Java (como o Java 8). Nesse caso, o duplo clique direto no `.jar` falha com a mensagem "A Java exception has occurred", porque o programa foi compilado para o Java 17. O `.bat` contorna isso usando o Java que está no PATH do sistema.

## Problemas comuns

- **"Java não encontrado"**: instale o Java 17+ ou peça para o responsável pelo computador.
- **A janela não abre**: abra o terminal na pasta e rode `java -version`. A versão precisa ser 17 ou superior.
- **O Windows avisa que o arquivo é desconhecido**: é o SmartScreen, comum em arquivos baixados. Clique em "Mais informações" e depois em "Executar assim mesmo".

## Funcionalidades

- Métodos GET, POST, PUT, PATCH e DELETE
- Headers e body da requisição editáveis
- Status, tempo de resposta, headers e body da resposta
- Tema escuro
- Mensagens de erro claras (URL inválida, timeout, falha de conexão)
- Sem dependências externas: usa apenas o JDK

## Compilando

Requer JDK 17+ e Maven:

    mvn clean package

O arquivo gerado fica em `target/ReqResPortable.jar`.

## Próximos passos

- Formatação automática de JSON na resposta
- Histórico de requisições
- Autenticação e parâmetros de query