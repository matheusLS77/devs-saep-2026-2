# Aula 01 — Contextualização do Projeto + Engenharia de Requisitos

**Módulo:** 1. Levantamento e Modelagem
**Carga horária:** 4h
**Professor(a):** Karize Viecelli — [@karizeviecelli]
**Entrega do dia:** Entrega 1 — Lista de requisitos funcionais 🏁 *(checkpoint)*

---

## 🎯 Objetivos da aula

Ao final desta aula, você será capaz de:

- Explicar o problema de negócio do almoxarifado e traduzi-lo em necessidades de sistema.
- Diferenciar requisito funcional de requisito não funcional.
- Redigir requisitos funcionais claros, testáveis e numerados, usando um modelo padronizado.
- Entregar a **Lista de requisitos funcionais** do projeto no formato exigido pelo SAEP.

---

## 🖼️ Analogia inicial — a caixa de ferramentas

Imagine que você acabou de ser contratado(a) como almoxarife de uma fábrica de ferramentas manuais.
Antes de organizar qualquer prateleira, você precisa saber **o que exatamente vai guardar ali**: quantos
tipos de martelo, que variações de chave de fenda, o que entra, o que sai, quem mexe em quê.

É exatamente isso que um requisito funcional faz por um sistema: ele é a etiqueta que você cola na
prateleira antes de guardar a ferramenta — "aqui vai o login", "aqui vai o cadastro de produto", "aqui vai
o alerta de estoque mínimo". Sem essa etiqueta, qualquer ferramenta pode ir parar em qualquer lugar, e o
sistema (assim como o almoxarifado) vira uma bagunça.

Essa é a primeira ferramenta que você guarda na sua caixa: **a capacidade de descrever, com precisão, o
que um sistema deve fazer antes de escrever a primeira linha de código.**

---

## 📚 Conteúdo teórico

### 1. O problema de negócio

Releia o contexto do Caderno de Prova SAEP: uma fabricante de ferramentas manuais não tem sistema
informatizado de estoque. Isso gera dois problemas opostos e igualmente caros:

- **Falta de produto** no momento da produção (ruptura de estoque).
- **Excesso de produto** parado, gerando custo de armazenagem e risco de obsolescência.

Repare que o próprio enunciado já entrega pistas de requisitos escondidas no texto:

> *"cada item possui especificidades de tamanho e peso que precisam ser adequadamente classificadas e
> controladas"* → o sistema precisa armazenar atributos como tamanho e peso do produto.

> *"emite alertas automáticos quando o nível de estoque de qualquer produto fica abaixo do valor mínimo"*
> → existe a necessidade de um campo de estoque mínimo configurável e uma regra de alerta.

> *"registrar um histórico completo de cada movimentação, identificando o responsável e a data da
> operação"* → existe a necessidade de um log de movimentações com usuário e data/hora.

**Ler o enunciado como um cliente descrevendo seu problema — não como uma lista de tarefas — é a
primeira habilidade de quem levanta requisitos.**

### 2. Requisito funcional x requisito não funcional

| | Requisito Funcional (RF) | Requisito Não Funcional (RNF) |
|---|---|---|
| Responde a | "O que o sistema **faz**?" | "**Como** o sistema se comporta?" |
| Exemplo | "O sistema deve permitir cadastrar um produto." | "O sistema deve responder em até 2 segundos." |
| Onde aparece no projeto | Entrega 1 (Lista de requisitos funcionais) | Entrega 9 (Lista de requisitos de infraestrutura) toca nesse tema |
| Testável como | Caso de uso / caso de teste | Métrica de desempenho, segurança, usabilidade |

Nesta aula, focamos 100% em **requisitos funcionais** — são eles que compõem a Entrega 1.

### 3. Anatomia de um bom requisito funcional

Um requisito funcional bem escrito segue a estrutura:

```
RF-XX — [Verbo no infinitivo] + [o que] + [condição/regra, se houver]
```

Exemplos ruins x bons:

- ❌ *"Sistema de login."* (não é uma frase, não diz o que o sistema faz)
- ✅ *"RF-04 — O sistema deve permitir que o usuário se autentique informando login e senha."*
- ❌ *"Estoque baixo."*
- ✅ *"RF-15 — O sistema deve emitir um alerta quando a quantidade em estoque de um produto for menor
  que o estoque mínimo configurado para aquele produto."*

Um requisito funcional bom é:

1. **Atômico** — descreve uma única capacidade, não várias amarradas com "e".
2. **Verificável** — dá para escrever um caso de teste a partir dele.
3. **Rastreável** — tem um identificador (RF-01, RF-02...) que pode ser referenciado depois nos casos de
   teste (Entrega 8).
4. **Escrito na voz do sistema** — "o sistema deve...", não "o usuário vai..." nem "eu vou fazer...".

### 4. Do desafio às categorias de requisitos

Vamos organizar os requisitos do projeto por **área funcional**, já adiantando a estrutura que o sistema
vai ter (e que vamos construir aula a aula):

- **Autenticação** (Aula 4) — login, logout, tratamento de erro de autenticação.
- **Estrutura/Navegação** (Aula 5) — tela principal, nome do usuário logado, acesso às demais telas.
- **Cadastro de produto** (Aulas 6–7) — listar, buscar, inserir, editar, excluir, validar.
- **Gestão de estoque** (Aulas 8–9) — listar ordenado, registrar movimentação (entrada/saída), alerta de
  estoque mínimo.

Cada uma dessas áreas vai virar um bloco de requisitos funcionais na sua lista — e, mais adiante no curso,
uma tela do sistema.

### 5. O modelo de documentação (ANEXO III)

O Caderno de Prova pede que a lista de requisitos siga o modelo do documento em anexo (ANEXO III —
documentacao.docx) e seja entregue em `.pdf`. Estrutura mínima que esse tipo de documento de SAEP
costuma cobrar:

- Capa/identificação (nome do projeto, autor, data).
- Introdução breve (1 parágrafo com o objetivo do sistema).
- Tabela de requisitos funcionais: **ID | Descrição | Prioridade** (Alta/Média/Baixa).
- Observações/premissas, se houver.

> 💡 Se o seu SAEP fornecer o ANEXO III oficial, use-o como modelo exato de formatação. O conteúdo (os
> requisitos em si) é o que vamos construir hoje.

---

<a id="atividade"></a>
## 💻 Atividade Prática (aprox. 2h30)

**Formato:** individual, com apoio do professor.

**Passo a passo:**

1. Releia a Contextualização e o Desafio do Caderno de Prova (página 1) e sublinhe toda frase que
   descreva um comportamento esperado do sistema.
2. Assista/releia o briefing do cliente (vídeo mencionado no Caderno) e anote qualquer requisito adicional
   que apareça só na fala do cliente, não no texto escrito.
3. Organize os requisitos encontrados em 4 blocos: **Autenticação**, **Estrutura/Navegação**, **Cadastro
   de produto**, **Gestão de estoque**.
4. Redija cada requisito no formato `RF-XX — [frase no padrão "o sistema deve..."]`, com pelo menos:
   - 2 requisitos de Autenticação
   - 2 requisitos de Estrutura/Navegação
   - 6 requisitos de Cadastro de produto
   - 6 requisitos de Gestão de estoque
5. Monte a tabela final (ID | Descrição | Prioridade) no modelo do ANEXO III e exporte como `.pdf`.

**Perguntas de fixação:**

- Qual a diferença entre "o sistema deve permitir cadastrar produto" e "o usuário cadastra produto"? Por
  que a primeira forma é preferível num requisito funcional?
- Cite um requisito não funcional (RNF) que você percebeu no enunciado, mesmo sem precisar escrevê-lo
  na Entrega 1. Por que ele não entra nesta lista?

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito completo

**Exemplo de lista de requisitos funcionais (referência — sua redação pode variar):**

**Autenticação**
- RF-01 — O sistema deve permitir que o usuário se autentique informando login e senha.
- RF-02 — O sistema deve informar ao usuário o motivo da falha de autenticação e redirecioná-lo
  novamente à tela de login.

**Estrutura/Navegação**
- RF-03 — O sistema deve exibir o nome do usuário logado na interface principal.
- RF-04 — O sistema deve permitir que o usuário realize logout, retornando à tela de login.

**Cadastro de produto**
- RF-05 — O sistema deve listar os produtos cadastrados automaticamente ao abrir a interface de cadastro.
- RF-06 — O sistema deve permitir buscar produtos por termo digitado pelo usuário.
- RF-07 — O sistema deve permitir inserir um novo produto, incluindo tamanho e peso.
- RF-08 — O sistema deve permitir editar um produto existente.
- RF-09 — O sistema deve permitir excluir um produto existente.
- RF-10 — O sistema deve validar os dados inseridos e exibir alerta em caso de campo obrigatório vazio ou
  inválido.

**Gestão de estoque**
- RF-11 — O sistema deve listar os produtos em ordem alfabética.
- RF-12 — O sistema deve permitir selecionar um produto para movimentação de estoque.
- RF-13 — O sistema deve permitir registrar movimentação de entrada ou saída, com data da operação.
- RF-14 — O sistema deve registrar o responsável por cada movimentação.
- RF-15 — O sistema deve emitir alerta quando o estoque de um produto ficar abaixo do mínimo configurado.
- RF-16 — O sistema deve manter um histórico completo de todas as movimentações realizadas.

**Perguntas de fixação — respostas:**

- "O sistema deve permitir cadastrar produto" descreve uma **capacidade do sistema**, testável e
  independente de quem a aciona; "o usuário cadastra produto" descreve uma ação humana, não uma
  especificação de sistema — não dá para escrever um caso de teste direto a partir dela.
- Exemplo de RNF perceptível no enunciado: tempo de resposta do alerta de estoque, ou disponibilidade do
  sistema durante o horário de produção. Ele não entra na Entrega 1 porque essa entrega é
  exclusivamente de requisitos **funcionais** — RNFs relacionados a infraestrutura aparecem mais à frente,
  na Entrega 9.

[« Voltar para a Atividade](#atividade)
