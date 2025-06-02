# 📘 Resumo: Arquitetura Monolítica

## ✨ Contexto Atual

Acessamos aplicações online via navegadores ou apps móveis que se comunicam com APIs (ex: Facebook, Twitter).

Essas APIs utilizam protocolos como HTTP para requisitar dados e funcionalidades dos servidores.

Por exemplo uma aplicação de ecommerce que competirá com Amazon, eBay e AliExpress, utilizando Java.

---

## 🧱 Estrutura Monolítica da Aplicação

A arquitetura atual da aplicação é **monolítica**, ou seja, composta por um único sistema que inclui todos os módulos (clientes, pedidos, produtos, autenticação etc.).

---

## ✅ Vantagens da Arquitetura Monolítica

- **Fácil de projetar:** visão completa do sistema.
- **Fácil de desenvolver:** ferramentas e IDEs modernas favorecem esse modelo.
- **Fácil de implantar:** um único arquivo WAR é suficiente.
- **Fácil de escalar (horizontalmente):** múltiplas instâncias por trás de um load balancer.

---

## ❌ Desvantagens da Arquitetura Monolítica

- **Alta complexidade com o crescimento:** código difícil de entender/refatorar.
- **Queda na produtividade.**
- **Sobrecarga nas máquinas de desenvolvimento:** grandes projetos exigem mais recursos.
- **Dificuldade de implantação contínua:** qualquer mudança exige o redeploy de toda a aplicação.
- **Alto risco nas atualizações.**
- **Escalabilidade limitada:** escalável apenas como um todo.
- **Consumo excessivo de memória/I/O.**
- **Componentes com requisitos diferentes (CPU, memória) não podem ser escalados separadamente.**
- **Barreira ao desenvolvimento em larga escala:** equipes precisam sincronizar todo o ciclo de desenvolvimento.
- **Dificuldade em realizar entregas independentes.**


| Característica                | Arquitetura Monolítica                     | Arquitetura de Microsserviços                    |
| ----------------------------- | ------------------------------------------ | ------------------------------------------------ |
| **Estrutura**                 | Código único e centralizado                | Serviços independentes e distribuídos            |
| **Implantação**               | Um único WAR/JAR para todo o sistema       | Cada serviço pode ser implantado individualmente |
| **Escalabilidade**            | Escalável apenas como um todo              | Escalável por serviço, conforme necessidade      |
| **Desenvolvimento em Equipe** | Difícil paralelizar entre times            | Equipes autônomas por domínio funcional          |
| **Ciclo de Deploy**           | Lento e complexo                           | Ágil e independente por serviço                  |
| **Manutenção**                | Refatorações impactam toda a base          | Refatorações isoladas em serviços específicos    |
| **Tecnologias**               | Uniformidade imposta                       | Flexibilidade para usar diferentes tecnologias   |
| **Tolerância a falhas**       | Falha em um módulo pode derrubar o sistema | Falhas isoladas por serviço                      |
| **Tempo de Inicialização**    | Pode ser alto                              | Inicialização rápida por serviço                 |
| **Complexidade Operacional**  | Menor complexidade inicial                 | Exige orquestração, observabilidade e DevOps     |
---
## 🤔 Desafios e Reflexões

- Como adotar agilidade neste contexto?
- Como garantir alta disponibilidade em datas críticas (Black Friday, Natal etc.)?
- A arquitetura monolítica suporta essas exigências com eficiência?