# gestao-tarefas

O projeto **Gestão de Tarefas** é o trabalho final da disciplina de Desenvolvimento Web Back-end do curso de Análise e Desenvolvimento de Sistemas (ADS) da Uninter. O objetivo é implementar uma API RESTful para gerenciamento de tarefas utilizando Java, Spring Boot e PostgreSQL.

A aplicação permite:
- Criar novas tarefas com nome, data de entrega e responsável
- Listar todas as tarefas cadastradas
- Consultar uma tarefa específica pelo ID
- Atualizar os dados de uma tarefa existente
- Remover tarefas do sistema

O foco do trabalho é aplicar conceitos de desenvolvimento web back-end, arquitetura REST, persistência de dados com JPA/Hibernate e boas práticas de organização de código com Spring Boot.

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL

---

## Como Executar

1. **Configure o banco de dados PostgreSQL**  
   Crie um banco chamado `db_tarefas` e ajuste as credenciais no arquivo `application.properties` ou via variáveis de ambiente.

2. **Compile e execute a aplicação**  
   No diretório raiz do projeto, execute:

   ```sh
   ./mvnw spring-boot:run