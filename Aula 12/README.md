# Aula 12 — Integração Fim a Fim do Sistema + Revisão Cruzada

**Módulo:** 6. Integração e Entrega
**Carga horária:** 4h
**Professor(a):** Karize Viecelli — [@karizeviecelli]
**Entrega do dia:** Projeto completo (revisão) — sem novo conteúdo técnico

---

## 🎯 Objetivos da aula

- Percorrer o sistema completo, de ponta a ponta, como um usuário real percorreria.
- Identificar e corrigir falhas de integração entre as partes construídas em aulas separadas.
- Realizar revisão cruzada com um colega, encontrando problemas que o próprio autor não percebe.

---

## 🖼️ Analogia — inspecionando o almoxarifado pronto

Depois de montar cada prateleira separadamente, chega o momento de andar pelo almoxarifado inteiro, do
início ao fim, como se você fosse um funcionário novo tentando usá-lo pela primeira vez. É nesse
percurso que aparecem as emendas mal encaixadas entre uma prateleira e outra.

---

## 📚 Conteúdo teórico

### 1. Por que integração é diferente de "cada parte funciona"

Cada aula anterior testou uma parte isoladamente: login (Aula 04), cadastro (Aulas 6-7), estoque (Aulas
8-9). Mas erros de integração só aparecem quando as partes são usadas **em sequência**, exatamente como
o avaliador vai usar no dia da prova:

```
Login → Interface Principal → Cadastro de Produto → Gestão de Estoque → Logout
```

Um erro comum e sutil: a tela de cadastro funciona sozinha, mas quebra quando é aberta a partir do botão
da interface principal (porque o produto selecionado ali não é passado corretamente adiante).

### 2. Roteiro de integração

Percorra o sistema como um usuário real, sem pular etapas:

1. Login com um usuário válido do `saep_db`.
2. Confirmar que a interface principal mostra o nome correto.
3. Ir ao Cadastro de Produto a partir do menu da interface principal (não abrindo a tela direto).
4. Inserir, buscar, editar e excluir um produto.
5. Voltar à interface principal.
6. Ir à Gestão de Estoque a partir do menu.
7. Selecionar um produto, ordenar a lista, registrar uma movimentação.
8. Provocar deliberadamente um alerta de estoque mínimo.
9. Consultar o histórico e confirmar que a movimentação aparece corretamente.
10. Fazer logout e confirmar o retorno ao login.

### 3. Revisão cruzada

Troque de sistema com um colega (ou peça para o professor/monitor testar) e observe:

- Alguém de fora consegue completar o roteiro acima **sem sua ajuda**?
- Alguma mensagem de erro é confusa para quem não conhece o código?
- Algum requisito da Aula 01 ficou esquecido pelo caminho?

> 💡 Revisão cruzada existe porque quem construiu o sistema já sabe "onde clicar" — e por isso não
> percebe as mesmas dificuldades que um usuário novo teria.

### 4. Registro dos problemas encontrados

Anote cada problema encontrado (seu ou do colega) num checklist simples: **Onde** aconteceu, **o que**
era esperado, **o que** aconteceu de fato. Isso vira sua lista de correções para o resto da aula.

---

<a id="atividade"></a>
## 💻 Atividade Prática (aprox. 2h30) — Roteiro de Integração

Esta aula não tem "gabarito" fechado — o objetivo é usar o roteiro acima (seção 2) para testar seu
próprio sistema de ponta a ponta, corrigir o que estiver quebrado, e depois repetir o processo com a
revisão cruzada de um colega.

1. Execute o roteiro de integração completo (10 passos da seção 2) no seu sistema.
2. Anote cada falha encontrada.
3. Corrija as falhas mais críticas (que impedem o roteiro de continuar).
4. Troque com um colega para revisão cruzada.
5. Anote os problemas que só apareceram na revisão do colega.

**Perguntas para reflexão:**

- Qual foi o ponto do roteiro onde seu sistema mais "quebrou a expectativa"?
- O que a revisão cruzada revelou que você mesmo não tinha percebido?
