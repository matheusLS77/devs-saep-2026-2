# Aula 02 — Modelagem Relacional de Dados

**Módulo:** 1. Levantamento e Modelagem
**Carga horária:** 4h
**Professor(a):** Karize Viecelli — [@karizeviecelli]
**Entrega do dia:** Entrega 2 — Diagrama Entidade-Relacionamento (DER) 🏁 *(checkpoint)*

---

## 🎯 Objetivos da aula

- Identificar entidades, atributos e relacionamentos a partir de uma lista de requisitos funcionais.
- Definir chaves primárias e estrangeiras e explicar para que servem.
- Representar cardinalidade (1:1, 1:N, N:N) num diagrama.
- Entregar o **DER** do projeto em formato de imagem.

---

## 🖼️ Analogia — organizando as prateleiras

Na Aula 01 você etiquetou as ferramentas que vão existir no seu sistema (os requisitos). Agora é hora de
decidir **em que prateleira cada uma mora** e **como as prateleiras se conectam entre si**. Um produto
pertence a uma categoria; uma movimentação pertence a um produto e a um usuário. É essa organização —
prateleira por prateleira, com etiquetas de que se conecta a quê — que chamamos de **modelo de dados**.

Essa é a segunda ferramenta na sua caixa: **a capacidade de transformar uma lista de "o que o sistema
faz" em "onde e como os dados vivem".**

---

## 📚 Conteúdo teórico

### 1. De requisito a entidade

Releia seus RFs da Aula 01 e sublinhe os **substantivos**: produto, usuário, movimentação. Cada
substantivo que se repete e carrega atributos próprios vira uma **entidade** (futura tabela).

| RF | Substantivo | Vira entidade? |
|---|---|---|
| RF-07 — inserir produto (tamanho, peso) | produto | ✅ `PRODUTO` |
| RF-01 — autenticar usuário | usuário | ✅ `USUARIO` |
| RF-13 — registrar movimentação de entrada/saída | movimentação | ✅ `MOVIMENTACAO` |

### 2. Atributos e chave primária (PK)

Cada entidade tem atributos (colunas) e **uma** chave primária — o identificador único de cada linha.

```
PRODUTO
 PK  id_produto
     nome
     tamanho
     peso
     estoque_atual
     estoque_minimo
```

### 3. Relacionamentos e chave estrangeira (FK)

Quando uma entidade referencia outra, usamos uma **chave estrangeira**: um atributo que aponta para a
PK de outra tabela.

```
MOVIMENTACAO
 PK  id_movimentacao
 FK  id_produto   → PRODUTO.id_produto
 FK  id_usuario   → USUARIO.id_usuario
     tipo (entrada/saída)
     data
```

### 4. Cardinalidade

- **1:1** — um para um (raro no nosso projeto).
- **1:N** — um produto tem N movimentações; um usuário registra N movimentações. **É o padrão do nosso
  projeto.**
- **N:N** — N para N (exigiria tabela associativa; não é necessário neste desafio).

### 5. O DER do projeto (referência)

```
USUARIO (1) ────< (N) MOVIMENTACAO (N) >──── (1) PRODUTO
   PK id_usuario        PK id_movimentacao        PK id_produto
      nome               FK id_usuario                nome
      login               FK id_produto                tamanho
      senha                tipo                        peso
                           data                        estoque_atual
                                                        estoque_minimo
```

Note como cada FK da tabela `MOVIMENTACAO` responde diretamente a um requisito da Aula 01: RF-14
(responsável) → FK id_usuario; RF-13 (produto movimentado) → FK id_produto.

---

<a id="atividade"></a>
## 💻 Atividade Prática (aprox. 2h30)

1. A partir da sua lista de RFs, liste todas as entidades candidatas (substantivos que se repetem).
2. Para cada entidade, liste os atributos e marque a chave primária.
3. Trace as relações entre entidades e defina a cardinalidade de cada uma.
4. Desenhe o DER (à mão, em uma ferramenta de diagramação, ou em ASCII) representando entidades,
   atributos, PK, FK e cardinalidade.
5. Exporte o diagrama como imagem (`.png` ou `.jpeg`).

**Perguntas de fixação:**

- Por que `MOVIMENTACAO` precisa de duas chaves estrangeiras, e não de uma só?
- O que aconteceria se `PRODUTO` não tivesse uma chave primária?

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito completo

**Entidades e atributos (referência):**

- `USUARIO`: id_usuario (PK), nome, login, senha
- `PRODUTO`: id_produto (PK), nome, tamanho, peso, estoque_atual, estoque_minimo
- `MOVIMENTACAO`: id_movimentacao (PK), id_usuario (FK), id_produto (FK), tipo, data

**Relacionamentos:** USUARIO 1:N MOVIMENTACAO · PRODUTO 1:N MOVIMENTACAO.

**Perguntas de fixação — respostas:**

- `MOVIMENTACAO` representa um evento que sempre envolve **duas** outras entidades ao mesmo tempo:
  quem fez (usuário) e o que foi movimentado (produto). Cada relação é independente, por isso são duas
  FKs distintas — sem elas seria impossível responder "quem" ou "o quê" de uma movimentação.
- Sem chave primária em `PRODUTO`, não haveria como referenciar um produto específico de forma única a
  partir de `MOVIMENTACAO` — a FK não teria para onde apontar, e duas linhas de produto poderiam ser
  confundidas entre si.

[« Voltar para a Atividade](#atividade)
