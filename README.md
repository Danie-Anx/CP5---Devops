# 🚀 API DimDim

A **API DimDim** é uma aplicação Java construída com **Spring Boot** que expõe um endpoint simples de teste e um endpoint de saúde da aplicação.  
O projeto foi desenvolvido como parte da atividade prática de **DevOps / Azure**, envolvendo:

- **Fase 1** → Deploy da aplicação Java no **Azure App Service**, usando o plugin Maven.  
- **Fase 2** → Criação de imagem Docker, publicação no **Azure Container Registry** em três versões (`v1`, `v2`, `v3`) e execução no **Azure Container Instances**.

---

## 📌 O que o projeto faz

- Ao iniciar, a aplicação sobe um servidor **Spring Boot** na porta **8080**.  
- Endpoints disponíveis:  
  - `GET /` → retorna a mensagem **"🚀 API DimDim rodando com sucesso (Spring Boot 3 + Java 17)!"**  
  - `GET /actuator/health` → retorna informações de saúde da aplicação (status `UP` quando ativo).

Isso garante que a aplicação pode ser usada tanto para **teste local** quanto para **deploy em nuvem**.

---

## 🖥️ Como rodar localmente (VS Code + Maven)

Durante o desenvolvimento e testes utilizamos o **Visual Studio Code** com terminal integrado.  
Os passos foram os seguintes:

1. Certifique-se de ter instalado:
   - Java 17+
   - Maven 3.9+
   - Docker (opcional, apenas se quiser rodar em container)

2. No **VS Code**, abra um terminal na pasta do projeto (`api-dimdim`) e rode o build:
   ```bash
   mvn clean package -DskipTests
   ```

   ⚡ Importante: o `pom.xml` já está configurado para gerar um **JAR executável** automaticamente.

3. Execute a aplicação:
   ```bash
   java -jar target/api-dimdim-1.0.0.jar
   ```

4. Teste no navegador ou pelo terminal:
   - [http://localhost:8080/](http://localhost:8080/)
   - [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

   Ou no terminal do VS Code com `curl`:
   ```bash
   curl http://localhost:8080/
   curl http://localhost:8080/actuator/health
   ```

---

## 🐳 Rodando com Docker

1. Crie a imagem:
   ```bash
   docker build -t api-dimdim:local .
   ```

2. Suba o container:
   ```bash
   docker run --rm -p 8080:8080 api-dimdim:local
   ```

3. Teste:
   ```bash
   curl http://localhost:8080/
   curl http://localhost:8080/actuator/health
   ```

---

## ☁️ Fase 1 — Deploy no Azure App Service

1. Gerar o `.jar`:
   ```bash
   mvn clean package -DskipTests
   ```

2. Deploy com o plugin Maven:
   ```bash
   mvn azure-webapp:deploy
   ```

O plugin cria o **App Service** no Azure e sobe a aplicação.

---

## ☁️ Fase 2 — Deploy com Docker + Azure Container Registry + ACI

1. Build da imagem:
   ```bash
   docker build -t api-dimdim:latest .
   ```

2. Criar 3 versões e enviar ao ACR:
   ```bash
   docker tag api-dimdim:latest moneyhubrpf1776.azurecr.io/api-dimdim:v1
   docker tag api-dimdim:latest moneyhubrpf1776.azurecr.io/api-dimdim:v2
   docker tag api-dimdim:latest moneyhubrpf1776.azurecr.io/api-dimdim:v3

   docker push moneyhubrpf1776.azurecr.io/api-dimdim:v1
   docker push moneyhubrpf1776.azurecr.io/api-dimdim:v2
   docker push moneyhubrpf1776.azurecr.io/api-dimdim:v3
   ```

3. Criar instâncias no Azure Container Instances:
   ```bash
   az container create -g rg-moneyhub -n aci-api-dimdim-v1      --image moneyhubrpf1776.azurecr.io/api-dimdim:v1      --registry-login-server moneyhubrpf1776.azurecr.io      --registry-username <USERNAME> --registry-password <PASSWORD>      --cpu 1 --memory 1 --ports 8080 --ip-address Public
   ```

Repita o mesmo processo para as versões `v2` e `v3`.

---

Aluno: Robert Daniel Da Silva Coimbra
RM: 555881
Turma: 2TDSPW

✅ **Resumo:** Testei e rodei a API com sucesso via **Visual Studio Code**, utilizando Maven para build e execução local. A aplicação respondeu corretamente em `http://localhost:8080/` e `http://localhost:8080/actuator/health`.
