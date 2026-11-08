# Aula 03 — SQL: DDL/DML, Chaves Primárias e Estrangeiras

**Módulo:** 1. Levantamento e Modelagem
**Carga horária:** 4h
**Professor(a):** Karize Viecelli — [@karizeviecelli]
**Entrega do dia:** Entrega 3 — Script de criação e população do banco (`saep_db`) 🏁 *(checkpoint)*

---

## 🎯 Objetivos da aula

- Diferenciar comandos DDL (estrutura) de comandos DML (dados).
- Traduzir o DER da Aula 02 em comandos `CREATE TABLE` com tipos de dados corretos.
- Definir chaves primárias e estrangeiras em SQL.
- Popular cada tabela com pelo menos 3 registros coerentes, usando `INSERT`.
- Entregar o script `.sql` do banco `saep_db`.

---

## 🖼️ Analogia — construindo e estocando as prateleiras

Seu DER é a planta baixa do almoxarifado. Hoje você vai **construir as prateleiras de verdade** (`CREATE
TABLE`) e **colocar as primeiras ferramentas nelas** (`INSERT`). Sem esse passo, o almoxarifado continua
sendo só um desenho no papel — hoje ele ganha existência física (ou melhor, existência em banco de
dados).

---

## 📚 Conteúdo teórico

### 1. DDL x DML

| | DDL (Data Definition Language) | DML (Data Manipulation Language) |
|---|---|---|
| Função | Define a **estrutura** (tabelas, colunas, chaves) | Manipula os **dados** dentro da estrutura |
| Comandos | `CREATE`, `ALTER`, `DROP` | `INSERT`, `UPDATE`, `DELETE`, `SELECT` |
| Analogia | Construir a prateleira | Colocar/tirar ferramenta da prateleira |

### 2. Criando o banco

```sql
CREATE DATABASE saep_db;
USE saep_db;
```

> ⚠️ O nome do banco é uma exigência da Entrega 3 (item 3.1 do Caderno de Prova): precisa ser
> exatamente `saep_db`.

### 3. Tipos de dados comuns

| Tipo | Uso | Exemplo |
|---|---|---|
| `INT` | números inteiros, IDs | `id_produto INT` |
| `VARCHAR(n)` | texto de tamanho variável | `nome VARCHAR(100)` |
| `DECIMAL(p,s)` | números com casas decimais | `peso DECIMAL(6,2)` |
| `DATE` / `DATETIME` | datas e horários | `data DATE` |
| `ENUM(...)` | valores fixos pré-definidos | `tipo ENUM('entrada','saida')` |

### 4. Criando as tabelas (DDL)

```sql
CREATE TABLE usuario (
  id_usuario INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  login VARCHAR(50) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL
);

CREATE TABLE produto (
  id_produto INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  tamanho VARCHAR(30),
  peso DECIMAL(6,2),
  estoque_atual INT NOT NULL DEFAULT 0,
  estoque_minimo INT NOT NULL DEFAULT 0
);

CREATE TABLE movimentacao (
  id_movimentacao INT PRIMARY KEY AUTO_INCREMENT,
  id_produto INT NOT NULL,
  id_usuario INT NOT NULL,
  tipo ENUM('entrada','saida') NOT NULL,
  data DATE NOT NULL,
  FOREIGN KEY (id_produto) REFERENCES produto(id_produto),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
```

Note que a ordem importa: `produto` e `usuario` precisam existir **antes** de `movimentacao`, porque ela
referencia as duas via `FOREIGN KEY`.

### 5. Populando as tabelas (DML)

O Caderno de Prova exige **pelo menos 3 registros por tabela** (item 3.2). Exemplo:

```sql
INSERT INTO usuario (nome, login, senha) VALUES
  ('Ana Souza', 'ana.souza', 'senha123'),
  ('Bruno Lima', 'bruno.lima', 'senha456'),
  ('Carla Nunes', 'carla.nunes', 'senha789');

INSERT INTO produto (nome, tamanho, peso, estoque_atual, estoque_minimo) VALUES
  ('Martelo cabo de madeira', '30cm', 0.65, 12, 5),
  ('Chave de fenda ponta imantada', '15cm', 0.10, 25, 10),
  ('Alicate universal', '20cm', 0.35, 8, 5);

INSERT INTO movimentacao (id_produto, id_usuario, tipo, data) VALUES
  (1, 1, 'entrada', '2026-01-05'),
  (2, 2, 'saida', '2026-01-06'),
  (3, 1, 'entrada', '2026-01-07');
```

### 6. Boas práticas para o script final

- Salve tudo em um único arquivo `.sql`, na ordem: `CREATE DATABASE` → `CREATE TABLE` (respeitando
  dependência de FK) → `INSERT`.
- Teste o script do zero (`DROP DATABASE IF EXISTS saep_db;` no topo, se for reexecutar em testes) para
  garantir que ele roda sem erro em uma máquina limpa.
- Use `NOT NULL` nos campos que, segundo os requisitos, sempre precisam de valor.

---

<a id="atividade"></a>
## 💻 Atividade Prática (aprox. 2h30)

1. Abra seu DER da Aula 02 e traduza cada entidade em um `CREATE TABLE`, escolhendo o tipo de dado
   correto para cada atributo.
2. Defina as chaves primárias (`PRIMARY KEY`) e estrangeiras (`FOREIGN KEY`) exatamente como no DER.
3. Escreva os `INSERT` de cada tabela, respeitando a ordem de dependência (tabelas referenciadas
   primeiro) e com pelo menos 3 registros cada.
4. Execute o script do início ao fim num SGBD à sua escolha e corrija qualquer erro de sintaxe ou de
   ordem.
5. Salve o arquivo final como `.sql` (ou formato acordado com o avaliador).

**Perguntas de fixação:**

- Por que a tabela `movimentacao` precisa ser criada **depois** de `produto` e `usuario`?
- O que aconteceria se você tentasse inserir uma movimentação com um `id_produto` que não existe na
  tabela `produto`?

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito completo

O script de referência está no conteúdo teórico acima (seções 4 e 5) — ele já contempla `CREATE
DATABASE saep_db`, as 3 tabelas com PK/FK corretas, e 3 registros por tabela.

**Perguntas de fixação — respostas:**

- Porque `movimentacao` tem chaves estrangeiras que **referenciam** `produto` e `usuario`. O SGBD precisa
  que a tabela referenciada já exista para poder validar a referência — criar na ordem errada gera erro
  de "tabela não encontrada" ou "constraint inválida".
- O SGBD rejeitaria o `INSERT`, retornando um erro de violação de chave estrangeira (foreign key
  constraint), porque não é permitido referenciar um `id_produto` que não existe na tabela `produto` —
  essa é justamente a garantia de integridade que a FK oferece.

[« Voltar para a Atividade](#atividade)
