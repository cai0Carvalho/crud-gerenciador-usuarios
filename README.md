## Projeto - CRUD Gerenciador de Usuários
Este é um projeto de um gerenciador de usuários, desenvolvido em Java com JDBC para interafir o banco de dados PostgreSQL.

## Funcionalidades
- Adicionar novo usuário no banco de dados 
- Buscar usuário por ID no banco de dados
- Atualizar usuário por ID no banco de dados
- Deletar usuário por ID no banco de dados
- Listar todos usuários existentes no banco de dados

## Tecnologias Utilizadas
- Java (versão recomendada: 17+)
- JDBC (Java Database Connectivity)
- PostgreSQL

## Pré-requisitos
Antes de rodar o projeto, certifique-se de ter instalado:

- Java (JDK 17 ou superior)
- PostgreSQL (configurado e rodando)

## Executando
Para executar a aplicação, siga os passos abaixo:

1. Clone o repositório para sua máquina local:
    ```bash 
    git clone https://github.com/cai0Carvalho/crud-gerenciador-usuarios.git
    ```
2. Após clonar o repositório, vá até o package `app` e selecione a classe que deseja executar. (AddUser, DeleteUse, GetAllUsers, GetByIdUser ou UpdateUser)

3. Você pode executar a aplicação a partir de sua IDE de desenvolvimento Java clicando em Run.

## Estrutuar do Projeto
### package app
 - `AddUser`: Classe com o método para adicionar novo usuário no banco de dados.

 - `DeleteUser`: Classe como o método para deletar usuário no banco de dados por ID.

 - `GetAllUsers`: Classe com o método para listar todos usuários existentes no banco de dados.

 - `GetByUser`: Classe como método para buscar usuário por ID no banco de dados.

 - `UpdateUser`: Classe com o método apra atualizar usuário existente no banco de dados por ID.

### package dao
 - `UserDao.java`: Classe DAO que contém os métodos para manipular dadaos de usuário no banco de dados.
 ### package model
 - `User.java`: Classe modelo que representa um usuário com ID, nome e email.
### package util
 - `ConnectionUtil.java`: Classe utilitária para obter a conexão com o banco de dados.

Autor:
Caio Carvalho

caiocamorim123@gmail.com
(https://www.linkedin.com/in/caio-c%C3%A9sar-8791772b8/).
