# Mock de servidor de autenticação/autorização com protocolo Oauth 2

## Description

Este é um servidor de autenticação/autorização, que faz uso do protocolo Oauth 2, para auxiliar nos testes de integração das APIs do Sinapse.

## Requirements
* git
* docker

## Quickstart

* Baixar repositório: 
* Criar imagem do container da aplicação: `docker build -t mock-oauth2 .`
* Executar o container da aplicação: `docker run -dp 8000:8000 mock-oauth2`


Enjoy! 🎉