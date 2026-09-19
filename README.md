# 🌍 Agência de Viagens API — Desafio 2

API REST para gerenciamento de destinos turísticos, evoluída com PostgreSQL, Spring Data JPA e Spring Security.

## Tecnologias

- Java 25
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- PostgreSQL
- Spring Security
- Maven

## Estrutura

```text
src/main/java/com/agencia/viagens
├── config
├── controller
├── model
├── repository
├── service
└── ViagensApplication.java
```

## Banco de dados

Crie no PostgreSQL:

```sql
CREATE DATABASE agencia_viagens;
```

Depois configure a senha do PostgreSQL em:

`src/main/resources/application.properties`

Não envie senha real ao GitHub.

## Executar

No terminal:

```bash
mvn spring-boot:run
```

Ou execute `ViagensApplication` pelo IntelliJ.

A aplicação será iniciada em:

`http://localhost:8080`

As tabelas são criadas/atualizadas pelo Hibernate com `spring.jpa.hibernate.ddl-auto=update`.

## Usuários de teste

### ADMIN

- Usuário: `admin`
- Senha: `admin123`

Permissões:
- consultar destinos
- cadastrar destinos
- atualizar destinos
- excluir destinos
- adicionar avaliações

### USER

- Usuário: `usuario`
- Senha: `user123`

Permissões:
- consultar destinos
- adicionar avaliações

## Endpoints

| Método | Endpoint | Acesso |
|---|---|---|
| GET | `/api/destinos` | Público |
| GET | `/api/destinos/{id}` | Público |
| GET | `/api/destinos/buscar?nome=Paris` | Público |
| GET | `/api/destinos/buscar?localizacao=Europa` | Público |
| POST | `/api/destinos` | ADMIN |
| PUT | `/api/destinos/{id}` | ADMIN |
| DELETE | `/api/destinos/{id}` | ADMIN |
| POST | `/api/destinos/{id}/avaliacoes` | USER / ADMIN |

## Exemplo — criar destino

Autenticação Basic:

- usuário: `admin`
- senha: `admin123`

Body JSON:

```json
{
  "nome": "Paris",
  "pais": "França",
  "localizacao": "Europa",
  "descricao": "Um dos principais destinos turísticos da Europa.",
  "preco": 5000
}
```

## Exemplo — avaliação

Autenticação Basic:

- usuário: `usuario`
- senha: `user123`

```json
{
  "nota": 5,
  "comentario": "Excelente destino!",
  "autor": "Usuário teste"
}
```

## Segurança

A API utiliza Spring Security com HTTP Basic. As senhas dos usuários são armazenadas no banco utilizando BCrypt.

Os perfis são:

- ADMIN
- USER

Operações sensíveis de cadastro, alteração e exclusão de destinos são restritas ao perfil ADMIN.

## Persistência

Os dados são persistidos no PostgreSQL por meio do Spring Data JPA.

Entidades:

- Destino
- Avaliacao
- Usuario

Repositories:

- DestinoRepository
- AvaliacaoRepository
- UsuarioRepository
