# mediaCataloger-api

A **mediaCataloger-api** é uma API desenvolvida para ajudar os usuários a catalogar suas atividades de entretenimento e aprendizado. O projeto permite que o usuário registre e busque informações sobre o que está fazendo no momento, seja catalogando livros que está lendo, filmes que assistiu, séries, animes, entre outros. Além de oferecer endpoints para acessar e consultar esses registros, a API permite a criação de novos registros, ajudando o usuário a manter um controle completo do seu consumo de mídia.

![Badge de Status do Projeto](https://img.shields.io/badge/status-ativo-brightgreen)

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Funcionalidades](#funcionalidades)
- [Instalação](#instalação)
- [Como Usar](#como-usar)

## Sobre o Projeto

A **mediaCataloger-api** foi criada com o objetivo de facilitar o registro de diferentes tipos de mídia que uma pessoa consome, como livros, filmes, séries, animes e outros. Com isso, a API visa atender usuários que desejam ter um controle de suas atividades de entretenimento e aprendizado. Ao fornecer endpoints para consulta e criação de registros, a API torna-se uma ferramenta útil para quem deseja acompanhar e organizar o que está consumindo em termos de livros, filmes, séries, etc.

A motivação principal por trás desse projeto é a busca por uma maneira simples de registrar e acessar as informações de mídias consumidas de forma intuitiva. Além disso, oferece a possibilidade de expansão para suportar diferentes tipos de mídia conforme a necessidade do usuário.

## Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias:

- **Java** (versão 21)
- **Spring Boot** (versão 3)
- **GitActions** (para integração contínua)
- **Docker** (para containerização e deployment no DockerHub)
- **Render** (para hospedagem do projeto)
- Banco de Dados: ainda está sendo definido.

## Funcionalidades

- [⚠️ *Em desenvolvimento*] **Cadastro de Mídias** - O usuário pode registrar diferentes tipos de mídias que está consumindo, como livros, filmes, séries e animes.
- [⚠️ *Em desenvolvimento*] **Consulta de Registros** - O usuário pode consultar os registros já cadastrados, para ver o que já foi catalogado.
- [⚠️ *Em desenvolvimento*] **Atualização de Registros** - A API permitirá ao usuário atualizar os detalhes de uma mídia já registrada, como o status de leitura ou assistido.
- [⚠️ *Em desenvolvimento*] **Remoção de Registros** - O usuário pode remover um registro de mídia do catálogo, caso não deseje mais mantê-lo.
- [⚠️ *Em desenvolvimento*] **Pesquisa Avançada** - A API permitirá buscar registros por categorias como tipo de mídia, nome, autor, status de consumo, entre outros.
- [⚠️ *Em desenvolvimento*] **Organização por Categoria** - Os registros poderão ser organizados por categorias (livros, filmes, séries, animes) para facilitar a navegação e consulta.

## Instalação

### Pré-requisitos

- **Java 21**: A versão mais recente do Java.
- **Spring Boot 3**: Framework para desenvolvimento da API.
- **Docker**: Para containerizar e enviar para o DockerHub.
- **GitActions**: Para integração contínua (CI/CD).

### Passo a Passo de Instalação

1. Clone o repositório:
    ```bash
    git clone https://github.com/[seu-usuário]/mediaCataloger-api.git
    ```

2. Entre no diretório do projeto:
    ```bash
    cd mediaCataloger-api
    ```

3. Instale as dependências:
    ```bash
    ./mvnw install
    ```

4. Construa a aplicação:
    ```bash
    ./mvnw clean install
    ```

5. Execute o projeto localmente:
    ```bash
    ./mvnw spring-boot:run
    ```

## Como Usar

Após a instalação, siga as instruções abaixo para rodar o projeto:

```bash
# Para rodar a API localmente
./mvnw spring-boot:run
```
A API estará disponível na porta **8080** (ou outra configurada). Você pode então testar os endpoints utilizando ferramentas como **Postman** ou **cURL** para enviar requisições.

### Endpoints disponíveis:

- [⚠️ *Em desenvolvimento*] **POST /register-media**: Para registrar uma nova mídia.
- [⚠️ *Em desenvolvimento*] **GET /catalog**: Para consultar todas as mídias registradas.
- [⚠️ *Em desenvolvimento*] **PUT /update-media/{id}**: Para atualizar um registro de mídia.
- [⚠️ *Em desenvolvimento*] **DELETE /delete-media/{id}**: Para remover um registro de mídia.
- [⚠️ *Em desenvolvimento*] **GET /search**: Para realizar uma busca avançada entre os registros.
