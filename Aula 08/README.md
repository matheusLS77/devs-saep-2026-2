# Aula 08 — Algoritmos de Ordenação e Registro de Movimentação

**Módulo:** 4. Gestão de Estoque
**Carga horária:** 4h
**Professor(a):** Karize Viecelli — [@karizeviecelli]
**Entrega do dia:** Preparação da Entrega 7 (sem checkpoint fechado ainda — a entrega fecha na Aula 09)

---

## 🎯 Objetivos da aula

- Implementar a listagem de produtos em ordem alfabética usando um algoritmo de ordenação.
- Implementar a seleção de produto e o registro de movimentação de entrada/saída com data.
- Entender por que a gestão de estoque começa pela organização e pelo registro do movimento.

---

## 🖼️ Analogia — organizando por ordem e anotando quem tirou o quê

Um almoxarifado bem cuidado tem as ferramentas em ordem alfabética nas prateleiras — facilita achar
qualquer coisa rapidamente. E toda vez que alguém tira ou devolve uma ferramenta, isso fica anotado numa
prancheta pendurada na entrada. Hoje construímos essas duas peças: a ordem e o registro do movimento.

---

## 📚 Conteúdo teórico

### 1. Por que "algoritmo de ordenação" e não só `ORDER BY`?

O Caderno de Prova (req. 7.1.1) pede explicitamente que você **utilize um algoritmo de ordenação**,
deixando o critério a seu critério. Isso significa implementar a lógica de ordenação você mesmo(a) — não
apenas usar `ORDER BY` do SQL — para demonstrar o domínio do conceito de algoritmos.

### 2. Bubble Sort (opção simples e didática)

```
função bubbleSort(lista):
    para i de 0 até tamanho(lista):
        para j de 0 até tamanho(lista) - i - 1:
            se lista[j] > lista[j+1]:
                trocar lista[j] e lista[j+1]
    retornar lista
```

Aplicado a nomes de produto (ordem alfabética), a cada passagem o "maior" valor (mais avançado no
alfabeto) vai sendo empurrado para o final da lista — como bolhas subindo.

### 3. Insertion Sort (alternativa igualmente válida)

```
função insertionSort(lista):
    para i de 1 até tamanho(lista):
        atual = lista[i]
        j = i - 1
        enquanto j >= 0 e lista[j] > atual:
            lista[j+1] = lista[j]
            j = j - 1
        lista[j+1] = atual
    retornar lista
```

Qualquer um dos dois (ou outro algoritmo clássico) atende ao requisito — o importante é que a lógica de
comparação e troca esteja explícita no seu código, não delegada inteiramente ao banco.

### 4. Selecionar produto para movimentação (req. 7.1.2)

```
tela Gestão de Estoque:
    dropdown/lista: selecionar produto (vindo de SELECT * FROM produto)
    opção: entrada ou saída
```

### 5. Registrando a movimentação com data (req. 7.1.3)

```sql
INSERT INTO movimentacao (id_produto, id_usuario, tipo, data)
VALUES (?, ?, ?, ?);
```

- `id_usuario` vem da sessão (usuário logado, Aula 04/05) — é assim que se cumpre o RF-14 (registrar o
  responsável).
- `tipo` é `'entrada'` ou `'saida'`, conforme selecionado.
- `data` pode ser preenchida automaticamente (data atual) ou informada pelo usuário, conforme a
  interpretação do seu projeto.

### 6. O que ainda falta (próxima aula)

Hoje não implementamos ainda: atualização do `estoque_atual` do produto a cada movimentação, nem o
alerta de estoque mínimo, nem o histórico completo — isso é o foco da Aula 09.

---

<a id="atividade"></a>
## 💻 Atividade Prática (aprox. 2h30)

1. Implemente um algoritmo de ordenação (bubble sort, insertion sort, ou outro) que organize a lista de
   produtos em ordem alfabética pelo nome.
2. Exiba essa lista ordenada na interface de Gestão de Estoque.
3. Implemente a seleção de um produto e a opção de tipo de movimentação (entrada/saída).
4. Implemente o registro da movimentação no banco, com data e usuário responsável (vindo da sessão).
5. Teste registrando pelo menos 3 movimentações diferentes (misturando entrada e saída).

**Perguntas de fixação:**

- Por que o Caderno de Prova pede um algoritmo de ordenação implementado, em vez de simplesmente usar
  `ORDER BY` no SQL?
- De onde vem o `id_usuario` que é salvo junto com cada movimentação?

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito completo

**Bubble sort aplicado a nomes de produto (referência):**

```
lista = ['Martelo', 'Chave de fenda', 'Alicate']
após bubbleSort(lista):
lista = ['Alicate', 'Chave de fenda', 'Martelo']
```

**Perguntas de fixação — respostas:**

- Porque o objetivo pedagógico do requisito é demonstrar que o aluno entende a **lógica** de um algoritmo
  de ordenação — comparação e troca de elementos — e não apenas sabe delegar essa tarefa ao SGBD. O
  `ORDER BY` resolveria o problema, mas não demonstraria esse domínio.
- Vem da sessão do usuário autenticado, criada durante o login (Aula 04) e mantida ao longo da navegação
  (Aula 05) — é assim que o sistema sabe automaticamente "quem" está registrando aquela movimentação, sem
  precisar perguntar de novo.

[« Voltar para a Atividade](#atividade)
