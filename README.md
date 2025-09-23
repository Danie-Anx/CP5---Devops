# 🚀 API DimDim

A **API DimDim** é uma aplicação Java construída com **Spring Boot** que expõe um endpoint simples de teste e um endpoint de saúde da aplicação.  
O projeto foi desenvolvido como parte da atividade prática de **DevOps / Azure**, envolvendo:

- **Fase 1** → Deploy da aplicação Java no **Azure App Service** (plano gratuito F1), usando o plugin Maven.  
- **Fase 2** → Criação de imagem Docker, publicação no **Azure Container Registry (ACR)** em três versões (`v1`, `v2`, `v3`) e execução no **Azure Container Instances (ACI)**.

---

## 📌 O que o projeto faz

- Ao iniciar, a aplicação sobe um servidor **Spring Boot** na porta **8080**.  
- Endpoints disponíveis:
  - `GET /` → retorna a mensagem **"🚀 API DimDim rodando com sucesso (Spring Boot 3 + Java 17)!"**  
  - `GET /actuator/health` → retorna informações de saúde da aplicação (status `UP` quando ativo).

Isso garante que a aplicação pode ser usada tanto para **teste local** quanto para **deploy em nuvem**.

---

## 🖥️ Como rodar localmente

1. Certifique-se de ter instalado:
   - Java 17+
   - Maven 3.9+
   - Docker (opcional, apenas se quiser rodar em container)

2. Compile e gere o `.jar`:
   ```bash
   mvn clean package -DskipTests

Acesse no navegador ou via curl:

http://localhost:8080/

http://localhost:8080/actuator/health

🐳 Rodando com Docker (opcional)

Crie a imagem:

docker build -t api-dimdim:local .


Suba o container:

docker run --rm -p 8080:8080 api-dimdim:local


Teste:

curl http://localhost:8080/
curl http://localhost:8080/actuator/health

☁️ Fase 1 — Deploy no Azure App Service

Gerar o .jar:

mvn clean package -DskipTests


Deploy com o plugin Maven:

mvn azure-webapp:deploy


O plugin cria o App Service no Azure (plano F1) e sobe a aplicação.

☁️ Fase 2 — Deploy com Docker + Azure Container Registry + ACI

Build da imagem:

docker build -t api-dimdim:latest .


Criar 3 versões e enviar ao ACR:

docker tag api-dimdim:latest moneyhubrpf1776.azurecr.io/api-dimdim:v1
docker tag api-dimdim:latest moneyhubrpf1776.azurecr.io/api-dimdim:v2
docker tag api-dimdim:latest moneyhubrpf1776.azurecr.io/api-dimdim:v3

docker push moneyhubrpf1776.azurecr.io/api-dimdim:v1
docker push moneyhubrpf1776.azurecr.io/api-dimdim:v2
docker push moneyhubrpf1776.azurecr.io/api-dimdim:v3


Criar instâncias no Azure Container Instances:

az container create -g rg-moneyhub -n aci-api-dimdim-v1 \
  --image moneyhubrpf1776.azurecr.io/api-dimdim:v1 \
  --registry-login-server moneyhubrpf1776.azurecr.io \
  --registry-username <USERNAME> --registry-password <PASSWORD> \
  --cpu 1 --memory 1 --ports 8080 --ip-address Public


Repita o processo para as versões v2 e v3.
