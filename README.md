\# Agência de Viagens API



API REST desenvolvida em Java com Spring Boot para gerenciamento de destinos turísticos e avaliações.



\## Tecnologias



\- Java 25

\- Spring Boot

\- Spring Data JPA

\- Hibernate

\- Spring Security

\- PostgreSQL

\- Maven



\## Funcionalidades



\- Cadastro de destinos

\- Listagem de destinos

\- Atualização de destinos

\- Exclusão de destinos

\- Cadastro de avaliações

\- Cálculo da média das avaliações

\- Autenticação de usuários

\- Autorização por perfil



\## Perfis de acesso



\### ADMIN



Pode:



\- Listar destinos

\- Cadastrar destinos

\- Atualizar destinos

\- Excluir destinos

\- Avaliar destinos



\### USER



Pode:



\- Listar destinos

\- Avaliar destinos



Usuários USER não podem cadastrar, alterar ou excluir destinos.



\## Usuários para teste



\### Administrador



Usuário:



`admin`



Senha:



`admin123`



Perfil:



`ADMIN`



\### Usuário comum



Usuário:



`usuario`



Senha:



`user123`



Perfil:



`USER`



\## Banco de dados



O projeto utiliza PostgreSQL.



Banco utilizado:



`agencia\_viagens`



A senha do banco não é armazenada no GitHub. Ela é configurada localmente através da variável de ambiente `DB\_PASSWORD`.



\## Configuração



Defina a variável de ambiente:



```text

DB\_PASSWORD=sua\_senha\_do\_postgresql

