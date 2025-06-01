## 📱 Sobre o Projeto

O MovieDB é um app de listagem de filmes populares, consumindo a API pública do TMDb (The Movie Database). A aplicação permite navegar entre uma lista paginada de filmes e uma tela de detalhes com título, imagem e descrição do filme selecionado.

## 🧩 Tecnologias e Arquitetura

- **Kotlin**
- **Jetpack Compose**
- **Paging 3**
- **Hilt (DI)**
- **Clean Architecture** (camadas: data, domain, presentation)
- **Coroutines / Flow**
- **Coil** para carregamento de imagens

## 🔄 Fluxo de Navegação

- `HomeScreen`: lista os filmes populares de forma paginada
- `DetailScreen`: exibe informações completas do filme selecionado

## 📂 Organização do Projeto

```text
com.jardimtech.movie_db
│
├── data              # Implementação da API e do repositório
├── domain            # Modelos e casos de uso
├── presentation      # UI com Jetpack Compose e ViewModels
└── di                # Módulos de injeção de dependência (Hilt)
