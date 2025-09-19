# Changelog

Todas as mudanças notáveis deste projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.1.0/), e este projeto adere a [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2025-09-19
### Adicionado
- Preferências: tema padrão (Claro/Escuro) e modo Debug (persistidos em `database/settings.properties`).
- Painel Usuários ampliado: atribuir/remover projetos e tarefas por usuário.
- Diálogo Sobre com autores e links para GitHub.
- Diálogo Changelog que lê `CHANGELOG.md` ou orienta uso de `git log`.
- Margens padronizadas e títulos com prefixo "Sistema de Gestão".

### Corrigido
- Inclusões na UI para evitar comparação por referência ao salvar atribuições (usa IDs).

