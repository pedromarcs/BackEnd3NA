# Testes do cadastro de usuário (Nutri+)

Testes da funcionalidade de cadastro (`POST /usuario`) feitos na Atividade 2 da
disciplina Engenharia de Requisitos, Teste e Qualidade de Software.

## Estrutura

- `playwright/tests/cadastro.spec.ts`: 3 testes de sistema (cadastro válido, senha de 5 caracteres, e-mail duplicado).
- `playwright/tests/exploratorio.spec.ts`: casos extras e hipóteses de erro. Não faz asserções, só imprime o status e a resposta de cada requisição.
- `playwright/tests/h3.spec.ts`: confere se a resposta do cadastro devolve a senha.
- `playwright/tests/login.spec.ts`: testes do login (`POST /usuario/login`): 3 com resultado esperado e 4 exploratórios.
- `python/testes_python.py`: testes da Atividade 1 (cadastro e login em Python, sem dependências).

## Como rodar os testes Playwright

Pré-requisitos: Node.js e o backend rodando em `http://localhost:8080`.

```
cd testes/playwright
npm install
npx playwright test --reporter=list
```

Atenção: os testes criam usuários de teste (`teste...@email.com`) no banco que o
backend estiver usando.

## Como rodar os testes Python

```
python testes/python/testes_python.py
```

## Defeitos conhecidos encontrados pelos testes

| Defeito | Situação nos testes |
|---|---|
| E-mail duplicado retorna erro 500 (o correto seria 409) | `cadastro.spec.ts` marca o teste com `test.fail()` até corrigir |
| Nome com mais de 150 caracteres retorna erro 500 | Aparece no `exploratorio.spec.ts` |
| E-mail em maiúsculas cria uma segunda conta | Aparece no `exploratorio.spec.ts` |
| E-mail sem ponto no domínio (`ana@email`), data futura e peso negativo são aceitos | Aparece no `exploratorio.spec.ts` |
| A resposta do cadastro devolve a senha (`senhaHash`) em texto puro | `h3.spec.ts` |
