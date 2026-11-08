# Aula 04 — Fluxo de Autenticação e Tratamento de Erro de Login

**Módulo:** 2. Autenticação e Estrutura
**Carga horária:** 4h
**Professor(a):** Karize Viecelli — [@karizeviecelli]
**Entrega do dia:** Entrega 4 — Interface de login 🏁 *(checkpoint)*

---

## 🎯 Objetivos da aula

- Explicar o fluxo completo de autenticação: entrada de credenciais → validação → sucesso/falha.
- Implementar uma tela de login que consulte a tabela `usuario` do `saep_db`.
- Tratar falha de autenticação informando o motivo ao usuário e redirecionando de volta ao login.
- Entregar a **Interface de login** funcional.

---

## 🖼️ Analogia — a porta do almoxarifado

Todo almoxarifado tem uma porta com controle de acesso: só entra quem está cadastrado, e se o crachá não
bate, o sistema **avisa por quê** — crachá inválido, senha errada — e não deixa a pessoa passar sem
explicação. É exatamente esse comportamento que vamos construir hoje: a porta de entrada do sistema.

---

## 📚 Conteúdo teórico

### 1. O fluxo de autenticação

```
[Tela de Login] → usuário digita login/senha → [Validação no banco]
        ↓ sucesso                                    ↓ falha
[Interface Principal]                    [Mensagem de erro + volta ao Login]
```

Esse fluxo responde diretamente aos requisitos RF-01 e RF-02 da Aula 01:

- RF-01 — autenticar usuário com login e senha.
- RF-02 — informar o motivo da falha e redirecionar ao login.

### 2. Validando contra o banco

A validação consulta a tabela `usuario` (criada na Aula 03) comparando o login e a senha informados:

```sql
SELECT id_usuario, nome FROM usuario
WHERE login = ? AND senha = ?;
```

- Se a consulta retornar **uma linha** → autenticação bem-sucedida, guarda o usuário na sessão e navega
  para a interface principal (Aula 05).
- Se retornar **nenhuma linha** → autenticação falhou.

> 💡 Em um sistema real de produção, a senha nunca é armazenada nem comparada em texto puro — usa-se
> hash (ex.: bcrypt). Para o escopo deste projeto SAEP, mantenha simples, mas comente no código que essa
> seria a evolução natural.

### 3. Tratamento de erro (RF-02)

Não basta dizer "erro" — o requisito pede que o **motivo** seja informado. Dois motivos comuns:

- Campo vazio (login ou senha em branco).
- Credenciais não encontradas no banco (login ou senha incorretos).

```
SE login vazio OU senha vazia:
    exibir "Preencha login e senha."
SENÃO SE não encontrou usuário:
    exibir "Login ou senha inválidos."
SENÃO:
    autenticar e redirecionar
```

> ⚠️ Por segurança, evite dizer especificamente "login não existe" x "senha errada" — isso ajuda um
> invasor a descobrir logins válidos. A mensagem genérica "login ou senha inválidos" é a prática
> recomendada, mesmo sendo menos "amigável".

### 4. Redirecionamento

Após a falha, o requisito 4.1 do Caderno de Prova exige que o usuário seja **redirecionado novamente à
tela de autenticação** — ou seja, a tela de login permanece a mesma, apenas exibindo a mensagem de erro,
sem navegar para nenhuma outra tela.

### 5. Design fica livre (item 4.2)

O Caderno de Prova deixa o design e layout a critério do aluno. Isso significa que a **funcionalidade**
(RF-01 e RF-02) é o que será avaliado — não a estética. Ainda assim, um layout limpo com campos claros de
login/senha, botão de entrar e área de mensagem de erro visível já atende bem ao requisito.

---

<a id="atividade"></a>
## 💻 Atividade Prática (aprox. 2h30)

1. Escolha a tecnologia da sua interface (web ou desktop) e crie a tela de login com campos de login e
   senha.
2. Conecte a tela ao banco `saep_db` e implemente a consulta de validação.
3. Implemente as duas situações de erro (campo vazio / credenciais inválidas) com mensagens claras.
4. Teste o fluxo completo: login correto → avança; login incorreto → mensagem de erro e permanece na
   tela.
5. Teste também com um dos 3 usuários que você inseriu na Aula 03.

**Perguntas de fixação:**

- Por que a mensagem de erro não deveria dizer especificamente "senha incorreta"?
- O que precisa acontecer com a tela de login quando a autenticação falha, segundo o requisito 4.1?

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito completo

**Pseudocódigo de referência:**

```
função autenticar(login, senha):
    se login == "" ou senha == "":
        retornar erro "Preencha login e senha."
    resultado = consultar usuario onde login=login e senha=senha
    se resultado vazio:
        retornar erro "Login ou senha inválidos."
    senão:
        guardar usuário na sessão
        navegar para interface principal
```

**Perguntas de fixação — respostas:**

- Porque diferenciar "login não existe" de "senha errada" ajuda alguém mal-intencionado a descobrir, por
  tentativa e erro, quais logins são válidos no sistema — é uma prática de segurança evitar mensagens
  específicas demais.
- Segundo o requisito 4.1, ao falhar a autenticação o sistema deve **informar o motivo da falha** e, em
  seguida, **redirecionar novamente à tela de autenticação** — ou seja, o usuário permanece no login, não
  avança para nenhuma outra tela.

[« Voltar para a Atividade](#atividade)
