# Aula 14 — Apresentação e Encerramento

**Módulo:** 6. Integração e Entrega
**Carga horária:** 3h
**Professor(a):** Karize Viecelli — [@karizeviecelli]
**Entrega do dia:** Avaliação da apresentação final

---

## 🎯 Objetivos da aula

- Apresentar o Sistema de Gestão de Estoque para Almoxarifado de forma clara e estruturada.
- Demonstrar o sistema funcionando ao vivo, cobrindo as áreas-chave (autenticação, cadastro, estoque).
- Explicar decisões técnicas de forma acessível, mesmo para quem não acompanhou o processo.
- Receber e dar feedback construtivo entre colegas.

---

## 🖼️ Analogia — abrindo as portas do almoxarifado para visita

Depois de organizar tudo, chega o dia de mostrar o almoxarifado pronto para quem nunca entrou ali. A boa
visita guiada não é uma lista de tudo o que existe — é um passeio com começo, meio e fim, que destaca o
que há de mais importante e deixa a pessoa entendendo por que aquele espaço foi organizado daquele jeito.

Essa é a última ferramenta que você guarda na caixa: **a capacidade de comunicar, com clareza, o que você
construiu.**

---

## 📚 Conteúdo teórico

### 1. Estrutura de uma boa apresentação técnica (10-12 min sugeridos)

| Tempo | Bloco | Conteúdo |
|---|---|---|
| ~1,5 min | Abertura | O problema do cliente (contextualização) e o que o sistema resolve |
| ~1 min | Arquitetura | DER, banco de dados e tecnologias usadas (visão geral, sem entrar em detalhe demais) |
| ~6 min | Demonstração ao vivo | Login → Cadastro de Produto → Gestão de Estoque → alerta de mínimo → histórico |
| ~2 min | Qualidade e infraestrutura | Como o sistema foi testado; SGBD/linguagem/SO usados |
| ~1,5 min | Fechamento | Principais aprendizados e o que faria diferente com mais tempo |

### 2. Como demonstrar sem travar

- **Prepare os dados de antemão**: tenha um produto já quase no limite do estoque mínimo, pronto para
  disparar o alerta durante a demonstração — não confie em digitar tudo na hora.
- **Narre enquanto mostra**: diga o que está fazendo e por quê, não apenas clique em silêncio.
- **Tenha um plano B**: se algo travar ao vivo, tenha prints/vídeo de backup do fluxo funcionando.

### 3. Explicando decisões técnicas para quem não é técnico

Evite jargão sem contexto. Em vez de "implementei uma FK com ON DELETE RESTRICT", diga algo como "o
sistema impede excluir um produto que já tem movimentações registradas, para não perder o histórico".

### 4. Recebendo feedback

Durante a rodada de perguntas, ouça a pergunta inteira antes de responder, e trate qualquer crítica como
informação útil, não como ataque — mesmo apresentações muito boas têm pontos de melhoria, e é isso que a
banca está avaliando: sua capacidade de refletir sobre o próprio trabalho, não só de "acertar tudo".

---

<a id="atividade"></a>
## 💻 Preparação para a Apresentação (aprox. 1h30)

1. Escreva um roteiro curto (pode ser um rascunho de tópicos, não precisa decorar frases) seguindo a
   estrutura da seção 1.
2. Prepare os dados de teste que vai usar na demonstração ao vivo (incluindo um produto próximo do
   estoque mínimo).
3. Ensaie a demonstração ao menos uma vez, cronometrando o tempo.
4. Prepare um plano B (print ou vídeo curto) para caso algo trave durante a apresentação ao vivo.

**Perguntas para reflexão:**

- Qual parte do seu sistema você tem mais orgulho de mostrar, e por quê?
- Se travar algo durante a demo ao vivo, qual é o seu plano B?
