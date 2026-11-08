# Aula 06 — Listagem Automática e Inserção de Produtos

**Módulo:** 3. CRUD de Produtos
**Carga horária:** 4h
**Professor(a):** Karize Viecelli — [@karizeviecelli]
**Entrega do dia:** Preparação da Entrega 6 (sem checkpoint fechado ainda — a entrega fecha na Aula 07)

---

## 🎯 Objetivos da aula

- Implementar a listagem automática de produtos ao abrir a interface de cadastro.
- Implementar a inserção de novos produtos no banco `saep_db`.
- Entender o "C" e o "R" do CRUD (Create, Read) como primeira metade do cadastro de produto.

---

## 🖼️ Analogia — enxergando e enchendo a prateleira

Antes de organizar (editar) ou tirar (excluir) qualquer ferramenta da prateleira, você precisa conseguir
**ver tudo o que já está lá** e **colocar coisas novas**. É isso que fazemos hoje: a prateleira fica
visível (listagem automática) e ganha a capacidade de receber novos itens (inserção).

---

## 📚 Conteúdo teórico

### 1. CRUD: as 4 operações básicas

| Letra | Operação | SQL | Nesta aula? |
|---|---|---|---|
| C | Create (criar) | `INSERT` | ✅ |
| R | Read (ler) | `SELECT` | ✅ |
| U | Update (atualizar) | `UPDATE` | ⏭️ Aula 07 |
| D | Delete (excluir) | `DELETE` | ⏭️ Aula 07 |

O cadastro de produto completo (Entrega 6) é o CRUD inteiro. Hoje construímos a metade que **mostra** e
**adiciona** dados; na Aula 07 completamos com editar e excluir.

### 2. Listagem automática (req. 6.1.1)

O requisito pede que a listagem aconteça **automaticamente** ao abrir a tela — sem precisar de um clique
extra em "buscar" ou "carregar":

```
ao abrir tela de Cadastro de Produto:
    produtos = SELECT * FROM produto
    exibir produtos em uma tabela
```

Isso é diferente da busca (que veremos na Aula 07) — a busca é acionada pelo usuário; a listagem inicial,
não.

### 3. Inserção de produto (req. 6.1.3)

A inserção precisa capturar todos os atributos definidos no seu DER (Aula 02) e script (Aula 03):

```sql
INSERT INTO produto (nome, tamanho, peso, estoque_atual, estoque_minimo)
VALUES (?, ?, ?, ?, ?);
```

Depois de inserir, a boa prática é **atualizar a listagem automaticamente** — o novo produto deve
aparecer na tabela sem que o usuário precise recarregar a tela manualmente.

### 4. Estrutura da tela (visão geral)

```
┌─────────────────────────────────────────┐
│  Cadastro de Produto                     │
│  [+ Novo Produto]                        │
│  ┌─────────────────────────────────────┐ │
│  │ Nome        │ Tamanho │ Peso │ Est. │ │
│  ├─────────────────────────────────────┤ │
│  │ Martelo     │ 30cm    │ 0.65 │ 12   │ │
│  │ Chave fenda │ 15cm    │ 0.10 │ 25   │ │
│  │ ...         │         │      │      │ │
│  └─────────────────────────────────────┘ │
└─────────────────────────────────────────┘
```

O botão "+ Novo Produto" abre um formulário (modal ou tela separada) com os campos de inserção.

### 5. Pensando à frente

Deixe a estrutura da tabela já preparada para receber, na próxima aula, colunas de ação (editar/excluir)
e o campo de busca — isso evita retrabalho.

---

<a id="atividade"></a>
## 💻 Atividade Prática (aprox. 2h30)

1. Crie a interface de Cadastro de Produto (acessível a partir da tela principal, Aula 05).
2. Implemente a listagem automática: ao abrir a tela, todos os produtos do `saep_db` aparecem numa
   tabela.
3. Implemente o formulário de inserção com todos os campos de produto (nome, tamanho, peso, estoque
   atual, estoque mínimo).
4. Após inserir, atualize a tabela automaticamente para mostrar o novo produto.
5. Teste inserindo pelo menos 2 produtos novos e confirme que aparecem na listagem.

**Perguntas de fixação:**

- Por que a listagem precisa acontecer automaticamente, sem exigir uma ação do usuário?
- O que deveria acontecer visualmente na tabela logo após um produto ser inserido com sucesso?

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito completo

**Pseudocódigo de referência:**

```
ao abrir tela:
    produtos = SELECT * FROM produto
    renderizar tabela(produtos)

ao clicar "+ Novo Produto" e confirmar:
    INSERT INTO produto (...) VALUES (...)
    produtos = SELECT * FROM produto   // recarrega
    renderizar tabela(produtos)
```

**Perguntas de fixação — respostas:**

- O requisito 6.1.1 exige que os dados "sejam carregados automaticamente ao acessar a interface" — isso
  melhora a experiência do usuário, que não precisa saber que precisa clicar em algo para ver os dados que
  já existem.
- A tabela deveria se atualizar automaticamente, exibindo o novo produto inserido, sem que o usuário
  precise recarregar a página/tela manualmente — essa é a expectativa implícita de qualquer sistema de
  cadastro funcional.

[« Voltar para a Atividade](#atividade)
