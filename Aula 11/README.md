# Aula 11 — Requisitos de Infraestrutura (SGBD, Linguagem, SO)

**Módulo:** 5. Qualidade e Infraestrutura
**Carga horária:** 4h
**Professor(a):** Karize Viecelli — [@karizeviecelli]
**Entrega do dia:** Entrega 9 — Lista de requisitos de infraestrutura 🏁 *(checkpoint)*

---

## 🎯 Objetivos da aula

- Entender por que documentar a infraestrutura é parte da entrega de um sistema, não um detalhe extra.
- Identificar e documentar o SGBD, a linguagem de programação e o sistema operacional usados, com suas
  respectivas versões.
- Entregar a **Lista de requisitos de infraestrutura**.

---

## 🖼️ Analogia — a planta elétrica e hidráulica do prédio

Depois que o almoxarifado está organizado e funcionando, alguém ainda precisa documentar a planta
elétrica e hidráulica do prédio — o que sustenta tudo por trás das prateleiras. Se o prédio for
reconstruído em outro lugar, essa planta é o que garante que tudo volte a funcionar exatamente igual.

---

## 📚 Conteúdo teórico

### 1. Por que documentar infraestrutura?

Um sistema não roda sozinho — ele depende de um conjunto específico de tecnologias por trás dele. Se
outra pessoa (ou você, daqui a um ano) precisar reinstalar ou migrar o sistema, essa documentação é o que
evita "na minha máquina funciona" — o problema mais clássico do desenvolvimento de software.

### 2. Os três pilares pedidos (req. 9.1)

| Item | O que documentar | Exemplo |
|---|---|---|
| 9.1.1 SGBD | Sistema Gerenciador de Banco de Dados e versão | MySQL 8.0, PostgreSQL 16, SQLite 3.45 |
| 9.1.2 Linguagem | Linguagem de programação e versão usada no desenvolvimento | Python 3.12, Java 21, JavaScript (Node.js 20) |
| 9.1.3 Sistema Operacional | SO e versão usados no desenvolvimento | Windows 11, Ubuntu 22.04, macOS 14 |

### 3. Como descobrir a versão exata que você usou

Alguns comandos úteis, dependendo da sua stack:

```bash
# SGBD (exemplo MySQL)
mysql --version

# Linguagem (exemplo Python)
python --version

# Linguagem (exemplo Node.js)
node --version

# Sistema Operacional (Linux)
lsb_release -a

# Sistema Operacional (Windows)
winver
```

> ⚠️ Documente a versão **real** que você usou — não a versão "mais recente disponível hoje". Se você
> instalou MySQL 8.0.36 em outubro, é essa a versão que entra no documento, mesmo que uma versão mais
> nova já tenha sido lançada.

### 4. Exemplo de documento final

```
Requisitos de Infraestrutura — Sistema de Gestão de Estoque para Almoxarifado

1. SGBD: MySQL, versão 8.0.36
2. Linguagem de programação: Python, versão 3.12.1 (framework Flask 3.0)
3. Sistema Operacional: Windows 11 (build 23H2)
```

### 5. Ligação com o resto do projeto

Esse documento fecha o ciclo iniciado na Aula 03 (quando você escolheu o SGBD para criar o `saep_db`) e
na Aula 04 (quando escolheu a linguagem/framework da interface). Hoje você só está **formalizando** o que
já vem sendo usado desde então.

---

<a id="atividade"></a>
## 💻 Atividade Prática (aprox. 2h30)

1. Verifique a versão exata do SGBD utilizado no seu projeto, usando o comando apropriado.
2. Verifique a versão exata da linguagem de programação (e framework, se houver) utilizada.
3. Verifique a versão do sistema operacional em que você desenvolveu o projeto.
4. Monte o documento seguindo o modelo do ANEXO III (documentacao.docx), com os três itens claramente
   identificados.
5. Exporte como `documentacao.pdf` (ou formato acordado com o avaliador).

**Perguntas de fixação:**

- Por que é importante documentar a versão exata, e não apenas o nome da tecnologia (ex.: "MySQL", sem
  versão)?
- O que pode dar errado se um sistema for migrado para outra máquina sem essa documentação?

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito completo

**Documento de referência (exemplo — ajuste às tecnologias reais do seu projeto):**

```
Requisitos de Infraestrutura — Sistema de Gestão de Estoque para Almoxarifado

1. SGBD: MySQL, versão 8.0.36
2. Linguagem de programação: Python, versão 3.12.1 (Flask 3.0)
3. Sistema Operacional: Windows 11 (build 23H2)
```

**Perguntas de fixação — respostas:**

- Versões diferentes da mesma tecnologia podem ter comportamentos, sintaxes ou recursos diferentes entre
  si — um script SQL escrito para MySQL 8 pode não rodar sem ajustes em MySQL 5.7, por exemplo. A versão
  exata é o que garante reprodutibilidade.
- Sem essa documentação, quem for reinstalar o sistema pode escolher uma versão incompatível de SGBD,
  linguagem ou SO, resultando em erros difíceis de diagnosticar — exatamente o cenário de "na minha
  máquina funciona" que a documentação de infraestrutura existe para evitar.

[« Voltar para a Atividade](#atividade)
