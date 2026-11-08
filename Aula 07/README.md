# Aula 07 — Edição, Exclusão, Busca Dinâmica e Validações

**Módulo:** 3. CRUD de Produtos
**Carga horária:** 4h
**Professor(a):** Karize Viecelli — [@karizeviecelli]
**Entrega do dia:** Entrega 6 — Interface de cadastro de produto 🏁 *(checkpoint)*

---

## 🎯 Objetivos da aula

- Completar o CRUD de produtos com Update (editar) e Delete (excluir).
- Implementar busca dinâmica por termo digitado.
- Validar dados de entrada e exibir alertas apropriados.
- Entregar a **Interface de cadastro de produto** completa (Entrega 6).

---

## 🖼️ Analogia — reorganizando e removendo ferramentas

Uma ferramenta pode trocar de lugar (editar), sair de circulação de vez (excluir), ou você pode
simplesmente querer achá-la rápido no meio de dezenas de outras (busca). E antes de qualquer uma dessas
ações, o sistema precisa garantir que a informação que está sendo salva faz sentido — é isso que a
validação faz.

---

## 📚 Conteúdo teórico

### 1. Update — editando um produto (req. 6.1.4)

```sql
UPDATE produto
SET nome=?, tamanho=?, peso=?, estoque_atual=?, estoque_minimo=?
WHERE id_produto = ?;
```

Fluxo típico: usuário clica em "Editar" na linha do produto → formulário abre pré-preenchido com os
dados atuais → usuário altera → confirma → `UPDATE` executado → tabela recarregada.

### 2. Delete — excluindo um produto (req. 6.1.5)

```sql
DELETE FROM produto WHERE id_produto = ?;
```

> ⚠️ Cuidado: se um produto já tiver movimentações associadas (tabela `movimentacao`, criada na Aula 03),
> o banco pode recusar a exclusão por violação de chave estrangeira. Isso é esperado — trate esse caso
> exibindo uma mensagem clara ("não é possível excluir: produto possui movimentações"), em vez de deixar
> o sistema travar com um erro técnico.

### 3. Busca dinâmica (req. 6.1.2)

Diferente da listagem automática (Aula 06), a busca é **acionada pelo usuário**, que digita um termo e
confirma:

```sql
SELECT * FROM produto WHERE nome LIKE '%termo%';
```

```
usuário digita "chave" e confirma:
    resultado = SELECT * FROM produto WHERE nome LIKE '%chave%'
    atualizar tabela com resultado
```

### 4. Validações (req. 6.1.6)

Toda inserção ou edição precisa validar os dados antes de salvar:

| Campo | Regra | Mensagem de exemplo |
|---|---|---|
| nome | obrigatório, não vazio | "Informe o nome do produto." |
| tamanho | obrigatório | "Informe o tamanho." |
| peso | número positivo | "Peso deve ser um número maior que zero." |
| estoque_atual | número inteiro ≥ 0 | "Estoque atual não pode ser negativo." |
| estoque_minimo | número inteiro ≥ 0 | "Estoque mínimo não pode ser negativo." |

```
se nome vazio OU tamanho vazio:
    exibir "Preencha todos os campos obrigatórios."
senão se peso <= 0:
    exibir "Peso deve ser maior que zero."
senão:
    salvar (INSERT ou UPDATE)
```

### 5. Voltar à interface principal (req. 6.1.7)

Não esqueça o botão/link de retorno à interface principal (Aula 05) — o CRUD de produto não pode ser um
beco sem saída.

---

<a id="atividade"></a>
## 💻 Atividade Prática (aprox. 2h30)

1. Adicione a ação "Editar" em cada linha da tabela, abrindo o formulário pré-preenchido e implementando
   o `UPDATE`.
2. Adicione a ação "Excluir" em cada linha, com confirmação antes de executar o `DELETE`.
3. Implemente o campo de busca, filtrando a tabela pelo termo digitado.
4. Implemente as validações de campo obrigatório e formato numérico, tanto na inserção (Aula 06) quanto
   na edição.
5. Adicione o botão de retorno à interface principal.
6. Teste o CRUD completo: inserir, listar, buscar, editar, excluir — nessa ordem, com os mesmos produtos.

**Perguntas de fixação:**

- O que deveria acontecer se o usuário tentar excluir um produto que já tem movimentações registradas?
- Por que a validação deveria acontecer **antes** de enviar os dados ao banco, e não depois?

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito completo

**Pseudocódigo consolidado do CRUD completo:**

```
Create:  INSERT INTO produto (...) VALUES (...)         [validado antes]
Read:    SELECT * FROM produto                          [automático]
         SELECT * FROM produto WHERE nome LIKE '%x%'    [busca]
Update:  UPDATE produto SET ... WHERE id_produto=?       [validado antes]
Delete:  DELETE FROM produto WHERE id_produto=?          [com confirmação]
```

**Perguntas de fixação — respostas:**

- O banco recusaria a exclusão por violação de chave estrangeira (a FK em `movimentacao` aponta para
  aquele `id_produto`). O sistema deveria capturar esse erro e exibir uma mensagem clara ao usuário, em
  vez de mostrar um erro técnico de banco de dados.
- Validar antes de enviar evita uma ida desnecessária ao banco com dados inválidos, dá um retorno mais
  rápido ao usuário (sem esperar resposta do servidor) e evita que dados inconsistentes cheguem a ser
  gravados, mesmo que só temporariamente.

[« Voltar para a Atividade](#atividade)
