# Aula 10 — Fundamentos de Teste de Software e Casos de Teste

**Módulo:** 5. Qualidade e Infraestrutura

**Carga horária:** 4h

**Professor(a):** Karize Viecelli — [@karizeviecelli]

**Entrega do dia:** Entrega 8 — Descritivo de Casos de Teste de Software 🏁 *(checkpoint)*

---

## 🎯 Objetivos da aula

- Entender por que testar software é parte do trabalho de quem desenvolve, não uma etapa opcional.
- Escrever casos de teste completos (pré-condição, passos, resultado esperado) para cada requisito
  funcional.
- Descrever ferramentas e ambiente de teste utilizados.
- Entregar o **Descritivo de Casos de Teste de Software**.

---

## 🖼️ Analogia — a inspeção de qualidade antes de estocar

Antes de uma ferramenta ir para a prateleira, alguém confere se ela realmente funciona: o martelo bate
reto, a chave gira sem travar. Software precisa da mesma inspeção — e o caso de teste é exatamente a
ficha de inspeção: o que testar, como testar, e o que se espera encontrar.

---

## 📚 Conteúdo teórico

### 1. Por que testar?

Você já testou seu sistema informalmente em todas as aulas anteriores ("vamos ver se funciona..."). Hoje
formalizamos esse hábito: cada requisito funcional (Aula 01) vira um ou mais **casos de teste**
documentados, rastreáveis e repetíveis por qualquer pessoa — não só por quem escreveu o código.

### 2. Anatomia de um caso de teste

| Campo | O que descreve |
|---|---|
| ID | Identificador único (ex.: CT-01), rastreável ao RF correspondente |
| Requisito relacionado | Qual RF-XX esse teste valida |
| Pré-condição | O que precisa estar pronto antes do teste (ex.: usuário cadastrado) |
| Passos | Sequência numerada de ações para executar o teste |
| Resultado esperado | O que deveria acontecer se tudo estiver correto |
| Resultado obtido | Preenchido durante a execução real do teste |
| Status | Passou / Falhou |

### 3. Exemplo de caso de teste

```
CT-01 — Login com credenciais válidas
Requisito: RF-01
Pré-condição: usuário "ana.souza" cadastrado no saep_db com senha "senha123"
Passos:
  1. Acessar a tela de login
  2. Preencher login: "ana.souza"
  3. Preencher senha: "senha123"
  4. Clicar em "Entrar"
Resultado esperado: usuário é autenticado e redirecionado à interface principal,
                     exibindo "Bem-vindo(a), Ana Souza"
```

```
CT-02 — Login com senha incorreta
Requisito: RF-02
Pré-condição: usuário "ana.souza" cadastrado no saep_db
Passos:
  1. Acessar a tela de login
  2. Preencher login: "ana.souza"
  3. Preencher senha: "senhaerrada"
  4. Clicar em "Entrar"
Resultado esperado: mensagem "Login ou senha inválidos." é exibida;
                     usuário permanece na tela de login
```

### 4. Cobrindo o sistema inteiro

Um bom conjunto de casos de teste cobre, no mínimo, um caso de **sucesso** e um de **falha** para cada
área do sistema:

| Área | Exemplos de caso de teste |
|---|---|
| Autenticação | Login válido (CT-01), login inválido (CT-02) |
| Cadastro de produto | Inserir produto válido, inserir com campo obrigatório vazio |
| Gestão de estoque | Movimentação que não dispara alerta, movimentação que dispara alerta |

### 5. Ferramentas e ambiente de teste (item 8.1)

O descritivo também precisa dizer **com que ferramentas e em que ambiente** os testes foram executados —
por exemplo: navegador utilizado, SGBD e versão, se os testes foram manuais ou automatizados.

---

<a id="atividade"></a>
## 💻 Atividade Prática (aprox. 2h30)

1. Liste todos os RFs da Aula 01 e, para cada bloco (autenticação, cadastro, estoque), identifique pelo
   menos um caso de sucesso e um de falha a documentar.
2. Escreva os casos de teste completos, seguindo o modelo do ANEXO III (documentacao.docx), com ID,
   requisito relacionado, pré-condição, passos e resultado esperado.
3. Execute cada caso de teste no seu próprio sistema e preencha o "resultado obtido" e o "status".
4. Descreva as ferramentas e o ambiente utilizados nos testes.
5. Exporte o descritivo como `.pdf`, seguindo o modelo do ANEXO III.

**Perguntas de fixação:**

- Por que um caso de teste precisa ter uma "pré-condição" explícita?
- O que diferencia um "resultado esperado" de um "resultado obtido"?

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito completo

**Exemplo adicional de referência:**

```
CT-05 — Alerta de estoque mínimo disparado
Requisito: correspondente ao alerta de estoque mínimo (Aula 09)
Pré-condição: produto "Chave allen" com estoque_atual=30 e estoque_minimo=10
Passos:
  1. Acessar Gestão de Estoque
  2. Selecionar produto "Chave allen"
  3. Selecionar tipo "saída" e quantidade 25
  4. Confirmar movimentação
Resultado esperado: estoque_atual atualizado para 5;
                     alerta "Estoque de Chave allen abaixo do mínimo!" exibido
```

**Perguntas de fixação — respostas:**

- Sem uma pré-condição clara, o teste não é repetível de forma confiável — outra pessoa (ou você mesmo,
  meses depois) não saberia em que estado o sistema precisa estar antes de executar os passos, e poderia
  obter um resultado diferente por causa disso.
- O "resultado esperado" é definido **antes** de rodar o teste (é a previsão, baseada no requisito); o
  "resultado obtido" é preenchido **depois**, com o que realmente aconteceu ao executar os passos — a
  comparação entre os dois é o que determina se o teste passou ou falhou.

[« Voltar para a Atividade](#atividade)
