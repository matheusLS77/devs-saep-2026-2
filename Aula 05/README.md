# Aula 05 — Arquitetura da Tela Principal e Navegação

**Módulo:** 2. Autenticação e Estrutura
**Carga horária:** 4h
**Professor(a):** Karize Viecelli — [@karizeviecelli]
**Entrega do dia:** Entrega 5 — Interface principal do sistema 🏁 *(checkpoint)*

---

## 🎯 Objetivos da aula

- Projetar a tela principal como o "hub" de navegação do sistema.
- Exibir o nome do usuário logado, vindo da autenticação da Aula 04.
- Implementar logout, retornando à tela de login.
- Criar os acessos às interfaces de Cadastro de Produto (Aula 7) e Gestão de Estoque (Aula 9).
- Entregar a **Interface principal do sistema**.

---

## 🖼️ Analogia — o hall de entrada

Depois que o crachá é validado na porta, a pessoa entra no **hall principal** do almoxarifado: dali ela
vê para onde pode ir (estoque, cadastro), sabe quem está logado (o crachá fica visível) e tem uma saída
clara. A tela principal cumpre exatamente esse papel — é o ponto central que conecta todas as outras
áreas do sistema.

---

## 📚 Conteúdo teórico

### 1. Por que a tela principal é um "hub"

Releia os requisitos 5.1.1 a 5.1.4 do Caderno de Prova — todos eles descrevem a tela principal como um
**ponto de passagem**, não como uma tela de conteúdo. Ela não guarda dados por si só; ela **conecta** o
usuário autenticado às telas que guardam dados (cadastro de produto, gestão de estoque).

```
                 ┌────────────────────┐
   Login  ────►  │  INTERFACE         │
                 │  PRINCIPAL         │ ───► Cadastro de Produto (Aula 7)
                 │  (nome do usuário) │
                 │                    │ ───► Gestão de Estoque (Aula 9)
                 └─────────┬──────────┘
                           │ logout
                           ▼
                        Login
```

### 2. Exibindo o usuário logado (RF-03 / req. 5.1.1)

Na Aula 04, ao autenticar com sucesso, você guardou o `id_usuario` e `nome` em algum tipo de sessão ou
estado da aplicação. A tela principal só precisa **ler** essa informação e exibi-la:

```
exibir: "Bem-vindo(a), " + sessao.usuario.nome
```

Se a tela principal for aberta sem uma sessão ativa, o comportamento correto é redirecionar de volta ao
login — isso evita que alguém acesse a tela principal sem antes ter passado pela autenticação.

### 3. Logout (RF-04 / req. 5.1.2)

Logout é o inverso do login: encerra a sessão e volta à tela de autenticação.

```
função logout():
    limpar sessao.usuario
    navegar para tela de Login
```

### 4. Navegação para Cadastro e Estoque (req. 5.1.3 / 5.1.4)

A tela principal precisa de, no mínimo, dois pontos de acesso claros:

- Um botão/link para a **Interface de Cadastro de Produto** (será construída na Aula 7).
- Um botão/link para a **Interface de Gestão de Estoque** (será construída na Aula 9).

Como essas telas ainda não existem completamente, hoje você pode criar as **rotas/telas vazias** (um
placeholder) que já recebem o clique corretamente — elas serão preenchidas de conteúdo nas próximas
aulas.

### 5. Design livre (req. 5.1.5)

Assim como no login, o layout é livre — o que conta é que os 4 comportamentos (exibir nome, logout,
acessar cadastro, acessar estoque) estejam implementados e funcionando.

---

<a id="atividade"></a>
## 💻 Atividade Prática (aprox. 2h30)

1. Crie a tela principal, acessível apenas após login bem-sucedido (na Aula 04).
2. Exiba o nome do usuário logado, lido da sessão criada no login.
3. Implemente o botão de logout, que limpa a sessão e retorna à tela de login.
4. Crie dois botões/links: "Cadastro de Produto" e "Gestão de Estoque", apontando para telas (mesmo que
   ainda vazias/placeholder) que serão desenvolvidas nas próximas aulas.
5. Teste o fluxo completo: login → tela principal com nome exibido → clique nos dois acessos → logout →
   volta ao login.

**Perguntas de fixação:**

- O que deveria acontecer se alguém tentasse abrir a URL/tela principal diretamente, sem antes fazer
  login?
- Por que faz sentido criar as telas de Cadastro e Estoque como "placeholder" nesta aula, mesmo sem
  conteúdo?

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito completo

**Fluxograma de referência:**

```
Login (sucesso) → guarda sessão → Interface Principal
Interface Principal:
  exibe sessao.usuario.nome
  botão [Cadastro de Produto] → tela (placeholder por enquanto)
  botão [Gestão de Estoque]   → tela (placeholder por enquanto)
  botão [Sair] → limpa sessão → volta ao Login
```

**Perguntas de fixação — respostas:**

- Sem sessão ativa, o correto é redirecionar automaticamente para a tela de login — permitir acesso
  direto à tela principal sem autenticação quebraria o requisito de segurança implícito no fluxo de
  login (RF-01/RF-02).
- Criar os placeholders agora garante que a **navegação** (o requisito desta aula) já funciona de ponta a
  ponta, mesmo que o **conteúdo** dessas telas ainda não exista. Isso evita retrabalho estrutural depois —
  nas Aulas 7 e 9 você só precisa preencher o que já está conectado.

[« Voltar para a Atividade](#atividade)
