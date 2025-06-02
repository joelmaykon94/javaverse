# 🛍️ Coding a Monolithic Application

---

## 🌐 Apresentando o Domínio

Nesta aplicação de loja online, os clientes cadastrados poderão:

- Comprar produtos do catálogo
- Ler e escrever avaliações dos produtos adquiridos

Para simplificar o fluxo de pagamento, será utilizado um **gateway de pagamento** como **Stripe** ou **PayPal**.

> ⚠️ **Nota:** As transações de pagamento **não** serão abordadas neste livro.

---

## 🧭 Diagrama de Caso de Uso

A aplicação oferece as seguintes funcionalidades:

### 👤 Para o Cliente:

- 🔍 Buscar e navegar por produtos
- 📝 Ler e escrever avaliações
- 🛒 Criar, visualizar e atualizar o carrinho
- 🧑 Atualizar perfil
- 💳 Finalizar compra e pagar

---

### 🛒 Para o Gerente da Loja:

- ➕ Adicionar produto
- ✏️ Atualizar produto
- ❌ Remover produto


![Diagrama de Caso de Uso](./assets/use_case_loja_online.png)

![](./assets/diagram_loja_online.jpg)

![](./assets/atividade_loja_online.png)
---

## 📌 Observações

Este módulo é implementado como uma **aplicação monolítica**, concentrando todas as funcionalidades descritas em um único sistema integrado.

## 🏗️ Estrutura de Camadas no Quarkus

- **Entity → Camada de Persistência:** Contém as entidades, repositórios JPA e classes relacionadas.
- **Control → Camada de Serviços:** Inclui serviços, configurações, batches, etc.
- **Boundary → Camada Web:** Responsável pelos endpoints dos serviços web.

![](./assets/arquitetura.png)
---

## 🚀 Gerando o Projeto com Quarkus

O Quarkus facilita a criação de novos projetos por meio do **Code Quarkus** (code.quarkus.io), uma ferramenta online semelhante ao Spring Initializr. Nela, você escolhe o gerenciador de build (Maven ou Gradle) e as extensões desejadas.

**Extensões Quarkus** funcionam como dependências que integram e configuram tecnologias no seu projeto, além de preparar tudo para compilação nativa com GraalVM.

Também é possível gerar um projeto via linha de comando:

```sh
mvn io.quarkus:quarkus-maven-plugin:1.13.2.Final:create \
    -DprojectGroupId=org.exemplo \
    -DprojectArtifactId=meu-projeto \
    -DclassName="org.exemplo.ExemploResource" \
    -Dpath="/exemplo"
```
## 🗄️ Criando a Camada de Persistência

Ao analisar o diagrama de classes, identificamos as seguintes entidades principais do domínio:

- **Address**
- **Cart**
- **Category**
- **Customer**
- **Order**
- **OrderItem**
- **Payment**
- **Product**
- **Review**

Além das entidades, o sistema utiliza algumas enumerações para representar estados e status importantes:

- **CartStatus**
- **OrderStatus**
- **ProductStatus**
- **PaymentStatus**

O diagrama a seguir ilustra as relações entre essas classes, evidenciando como os objetos do domínio interagem na camada de persistência.

![](./assets/relacionamentos.jpg)