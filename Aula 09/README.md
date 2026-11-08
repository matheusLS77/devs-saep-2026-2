# Aula 09 — Alertas de Estoque Mínimo e Histórico/Rastreabilidade

**Módulo:** 4. Gestão de Estoque
**Carga horária:** 4h
**Professor(a):** Karize Viecelli — [@karizeviecelli]
**Entrega do dia:** Entrega 7 — Interface de gestão de estoque 🏁 *(checkpoint)*

---

## 🎯 Objetivos da aula

- Atualizar o `estoque_atual` do produto a cada movimentação.
- Implementar a verificação automática de estoque mínimo em movimentações de saída.
- Exibir o histórico completo de movimentações, com responsável e data.
- Entregar a **Interface de gestão de estoque** completa (Entrega 7).

---

## 🖼️ Analogia — a luz de aviso e o livro de registro

Um bom almoxarifado tem uma luz de aviso que acende quando uma prateleira está quase vazia, e um livro de
registro (ou prancheta) onde toda movimentação — quem tirou, o quê, quando — fica anotada de forma
permanente. Hoje instalamos a luz de aviso e abrimos o livro de registro.

---

## 📚 Conteúdo teórico

### 1. Atualizando o estoque a cada movimentação

Toda vez que uma movimentação é registrada (Aula 08), o `estoque_atual` do produto precisa refletir essa
mudança:

```sql
-- entrada: soma
UPDATE produto SET estoque_atual = estoque_atual + ? WHERE id_produto = ?;

-- saída: subtrai
UPDATE produto SET estoque_atual = estoque_atual - ? WHERE id_produto = ?;
```

> 💡 Isso deve acontecer **junto** com o `INSERT` em `movimentacao` — as duas operações formam uma
> unidade lógica: se uma falha, a outra não deveria acontecer sozinha (conceito de transação, opcional
> para aprofundar).

### 2. Verificação automática de estoque mínimo (req. 7.1.4)

O requisito pede que, **a cada movimentação de saída**, o sistema verifique automaticamente se o novo
`estoque_atual` ficou abaixo do `estoque_minimo` configurado:

```
ao registrar movimentação de saída:
    atualizar estoque_atual
    se estoque_atual < estoque_minimo:
        exibir alerta "Estoque de [produto] abaixo do mínimo!"
```

Isso responde diretamente ao Desafio do Caderno de Prova: *"emite alertas automáticos quando o nível de
estoque de qualquer produto fica abaixo do valor mínimo previamente configurado."*

### 3. Histórico completo (contextualização do Desafio)

O histórico precisa mostrar, para cada movimentação: o produto, o tipo (entrada/saída), a data e o
responsável. É basicamente uma listagem da tabela `movimentacao`, com um `JOIN` para trazer os nomes em
vez dos IDs:

```sql
SELECT m.data, p.nome AS produto, m.tipo, u.nome AS responsavel
FROM movimentacao m
JOIN produto p ON m.id_produto = p.id_produto
JOIN usuario u ON m.id_usuario = u.id_usuario
ORDER BY m.data DESC;
```

Esse histórico é o que garante **rastreabilidade** — a capacidade de responder "quem fez o quê e quando"
a qualquer momento, exatamente como pedido na Contextualização do Caderno de Prova.

### 4. Juntando tudo — o fluxo completo da Gestão de Estoque

```
1. Lista de produtos ordenada (Aula 08)
2. Seleciona produto + tipo de movimentação (Aula 08)
3. Registra movimentação com data/usuário (Aula 08)
4. Atualiza estoque_atual do produto        ← hoje
5. Verifica estoque mínimo e alerta         ← hoje
6. Exibe histórico completo                 ← hoje
```

---

<a id="atividade"></a>
## 💻 Atividade Prática (aprox. 2h30)

1. Implemente a atualização do `estoque_atual` a cada movimentação (soma na entrada, subtrai na saída).
2. Implemente a verificação automática: após uma saída, se `estoque_atual < estoque_minimo`, exiba um
   alerta visual claro.
3. Implemente a listagem de histórico com produto, tipo, data e responsável.
4. Teste um cenário que **dispare** o alerta: registre saídas suficientes para derrubar o estoque de um
   produto abaixo do mínimo configurado.
5. Confirme que o histórico reflete corretamente todas as movimentações já feitas (incluindo as da Aula
   08).

**Perguntas de fixação:**

- Por que a verificação de estoque mínimo só precisa acontecer em movimentações de **saída**, e não de
  entrada?
- Que informações mínimas o histórico de movimentações precisa exibir para garantir rastreabilidade?

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito completo

**Pseudocódigo consolidado:**

```
função registrarMovimentacao(id_produto, id_usuario, tipo, data):
    INSERT INTO movimentacao (...)
    se tipo == 'entrada':
        UPDATE produto SET estoque_atual = estoque_atual + qtd WHERE id_produto=?
    senão:
        UPDATE produto SET estoque_atual = estoque_atual - qtd WHERE id_produto=?
        produto = SELECT estoque_atual, estoque_minimo FROM produto WHERE id_produto=?
        se produto.estoque_atual < produto.estoque_minimo:
            exibir alerta "Estoque de [produto] abaixo do mínimo!"
```

**Perguntas de fixação — respostas:**

- Porque o risco de ruptura de estoque (o problema central do Desafio) só acontece quando o estoque
  **diminui** — uma entrada nunca deixa o estoque mais baixo, então verificar o mínimo nesse caso seria
  desnecessário.
- No mínimo: qual produto foi movimentado, se foi entrada ou saída, quando aconteceu (data) e quem foi o
  responsável — essas quatro informações juntas são o que permite reconstruir "o que aconteceu" a
  qualquer momento, cumprindo a exigência de rastreabilidade do Desafio.

[« Voltar para a Atividade](#atividade)
