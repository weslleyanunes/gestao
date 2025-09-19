# Sistema de Gestão de Projetos

Aplicação desktop em Java (Swing) para gestão de usuários, projetos e tarefas. O sistema utiliza persistência em arquivo, autenticação segura, recuperação de senha por token e interface com suporte a temas claro/escuro.

## 1. Visão Geral

O objetivo é prover uma solução leve e independente de servidor para acompanhamento de projetos e tarefas, com controle de acesso por cargos e experiência de uso consistente.

Principais características:
- Persistência local via arquivo serializado (`database/data.ser`).
- Autenticação com hash de senha (BCrypt) e fluxo de recuperação de senha por token.
- Interface Swing com FlatLaf (temas Claro/Escuro) e navegação moderna com painel lateral.
- Preferências persistentes (tema padrão e modo debug) em `database/settings.properties`.
- Logs gravados em `database/app.log` com visualização integrada.

## 2. Arquitetura e Componentes

O projeto adota uma separação por camadas:
- Modelos (`com.gestao.model`): entidades de domínio (Usuario, Projeto, Tarefa, etc.).
- Repositórios (`com.gestao.repository`): acesso ao armazenamento via `DataStore` (serialização) e `DatabaseManager` (load/save).
- Serviços (`com.gestao.service`): regras de negócio (criação e validação), hashing de senha, preferências e logging.
- Interface (`com.gestao.ui`): formulários e painéis em Swing, com navegação por `MainDashboard`.

Persistência:
- `database/data.ser`: armazena entidades serializadas (usuários, projetos, tarefas, tokens de recuperação, etc.).
- `DatabaseManager` gerencia o ciclo de vida do `DataStore` (carregamento e persistência).

Segurança:
- Senhas com hash usando BCrypt (`PasswordUtils`).
- Controle por cargos no `Usuario` (Administrador, Gerente, Membro, Desenvolvedor).

Interface:
- Temas Claro/Escuro com FlatLaf.
- Preferências em `PreferencesDialog` (tema padrão e modo debug).
- Navegação por painel lateral (Projetos, Tarefas, Usuários, Logs) e ações no canto inferior direito.

## 3. Estrutura do Repositório

```
src/
	main/java/com/gestao/
		main/App.java
		model/ (entidades)
		repository/ (DataStore, DatabaseManager, repositórios)
		service/ (serviços de negócio, senha, preferências, logging)
		ui/ (telas e painéis Swing)
database/
	data.ser (dados)
	app.log (logs)
	settings.properties (preferências)
pom.xml (build Maven, assembly com dependências)
```

## 4. Requisitos e Execução

Pré-requisitos: Java 11 e Maven.

Build (gera JAR com dependências):
```
mvn -DskipTests package
```

Execução:
```
java -jar target/sistema-gestao-projetos.jar
```

Credenciais iniciais: usuário `admin` e senha `admin123` (criadas automaticamente na primeira execução).

## 5. Configuração e Dados

- `database/settings.properties`: preferências do usuário (tema padrão e modo debug).
- `database/app.log`: arquivo de logs da aplicação.
- `database/data.ser`: armazenamento de dados serializados.

## 6. Papéis e Permissões

- Administrador: acesso total.
- Desenvolvedor: acesso total e ferramentas de logs/depuração.
- Gerente: gestão de projetos.
- Membro: visualização/execução de tarefas dos projetos em que participa.

## 7. Dependências e Referências de Terceiros

O sistema utiliza bibliotecas de terceiros, creditadas e referenciadas abaixo:

- FlatLaf — tema para Swing
	- Versão: 3.2.5
	- Site/Repositório: https://www.formdev.com/flatlaf/ e https://github.com/JFormDesigner/FlatLaf
	- Finalidade: look-and-feel Claro/Escuro e estilos modernos para Swing.
	- Licença: Apache License 2.0 (ver repositório oficial para confirmação)

- jBCrypt (org.mindrot:jbcrypt)
	- Versão: 0.4
	- Repositório: https://github.com/jeremyh/jBCrypt
	- Finalidade: hashing de senhas com BCrypt.
	- Licença: ISC (ver repositório oficial para confirmação)

Ferramentas de build:
- Maven Assembly Plugin — empacotamento do JAR com dependências.

Observação sobre autoria do código:
- A varredura dos fontes não encontrou cabeçalhos de copyright de terceiros incorporados
	nem trechos copiados de outros autores. O código do aplicativo é original e integra apenas
	as bibliotecas acima por dependência declarada no Maven.

## 8. Identificação de Changelog (Repositório Git)

- O arquivo `CHANGELOG.md` documenta alterações relevantes em formato “Keep a Changelog”.
- Na aplicação, o painel de Logs do `MainDashboard` dispõe do botão “Changelog” para leitura direta do `CHANGELOG.md`.
- Alternativamente, é possível listar mudanças via Git:
```
git --no-pager log --pretty=format:'%h %ad %an %s' --date=short
```

## 9. Histórico de Alterações — Resumo desta Entrega

Esta entrega consolidou as seguintes melhorias funcionais e técnicas:
- Inclusão de preferências persistentes (tema padrão Claro/Escuro e modo Debug) e aplicação em tempo real.
- Ampliação do painel de Usuários para atribuição e remoção de projetos e tarefas por usuário.
- Diálogo “Sobre” com descrição do sistema, autores e links para perfis GitHub.
- Diálogo “Changelog” integrado ao painel de Logs.
- Padronização de margens na interface e títulos com prefixo “Sistema de Gestão”.
- Ajustes de comparação por ID nas listas de atribuições para garantir consistência.

Detalhes completos no arquivo `CHANGELOG.md`.

## 10. Autores

- [@weslleyanunes](https://github.com/weslleyanunes)
- [@marcvcci](https://github.com/marcvcci)
- [@TidezTi](https://github.com/TidezTi)

## 11. Licença

Distribuído sob a licença GPLv3. Consulte o arquivo `LICENSE` na raiz do projeto.
Recomenda-se garantir que o arquivo contenha o texto completo e atualizado da licença.

## 12. Licenças de Terceiros

- FlatLaf: Apache License 2.0 — https://www.apache.org/licenses/LICENSE-2.0
- jBCrypt: ISC License — https://opensource.org/license/isc-license-txt
