# Wishlist API

## Que projeto é esse?

É uma API muito simples que faz a gestão de uma lista de desejos 
de um e-commerce. Existem 4 endpoints:
- "POST /v1/wishes" é para adicionar um produto na lista de um cliente
- "GET /v1/wishes/wishlist" é para trazer a lista de desejos de um cliente
- "GET /v1/wishes" é para conferir se um produto está na lista de um cliente
- "DELETE /v1/wishes/{wishId}" é para remover um produto da lista de um cliente

A lista de desejos de um cliente possui um limite de 20 produtos.
Se você tentar adicionar mais algum produto na lista, você vai receber
um erro do endpoint. 

Além disso é importante ressaltar que se o mesmo cliente acabar tentando
adicionar um mesmo produto na lista de desejos, o endpoint retornará o desejo
que já estava cadastrado no banco de dados.

A estrutura de dados de um "desejo" é bem simples:
- id: identificador do desejo
- customerId: código do cliente
- productId: código do produto
- createdAt: data de criação do desejo
- updatedAt: data de atualização do desejo


## O que esse projeto tem de legal (tecnologias \o/)?
- Java 11 com gradle;
- Clean Arch;
- MongoDB;
- Docker Compose;
- Test containers;
- Junit 5;


## Como faço para executar esse projeto na minha máquina?

Fico contente pelo interesse! Primeiro preciso que você confira se tem o seguinte instalado na sua máquina:

- Java 11
- Gradle 7.2
- Docker
- Docker Compose

Depois de clonar o projeto na sua máquina execute esse comando para 
instalar as dependências (e rodar os testes \o/):

`./gradlew clean build`

Agora precisamos prepara a infraestrutura. Execute o seguinte para termos o MongoDB:
`docker-compose up`

Por último, basta executar o servidor com o seguinte comando:

`./gradlew bootRun`

Após rodar esse último comando, o servidor vai iniciar na porta 8080.

Você pode conferir se deu tudo certo chamando o endpoint "http://localhost:8080/actuator/health".

Para testar os endpoints, confira a documentação no seguinte link: 
http://localhost:8080/swagger-ui.html

## Como faço para executar os testes?
Bem simples! Entre na pasta do projeto e execute o comando:
`./gradlew test`

Psiu! Isso também vai gerar um arquivo onde você pode conferir
a cobertura dos testes :)

Confira o arquivo nesse caminho "./build/jacocoHtml/index.html".

A pirâmide de testes está organizada assim:
- Há testes unitários principalmente para usecases e mappers;
- Há testes de integração com o MongoDB;
- E há testes E2E para os controllers (rest assured + bdd);

## Ufa! Quanta coisa né?
Se você tiver algum feedback, ou quiser trocar uma ideia, entra em contato comigo :)
Você consegue me encontrar facilmente no LinkedIn: https://www.linkedin.com/in/aleph-santos-oliveira/
Obrigado!

