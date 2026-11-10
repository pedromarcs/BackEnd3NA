import { test, expect } from "@playwright/test";

const BASE_URL = "http://localhost:8080";

function emailUnico(): string {
  return `teste${Date.now()}@email.com`;
}

test("Deve cadastrar usuário com dados válidos", async ({ request }) => {
  const resposta = await request.post(`${BASE_URL}/usuario`, {
    data: {
      nomeCompleto: "Ana Souza",
      email: emailUnico(),
      senhaHash: "123456",
    },
  });

  expect(resposta.status()).toBe(201);

  const corpo = await resposta.json();
  expect(corpo.idUser).toBeDefined();
});

test("Não deve cadastrar com senha de 5 caracteres", async ({ request }) => {
  const resposta = await request.post(`${BASE_URL}/usuario`, {
    data: {
      nomeCompleto: "Ana Souza",
      email: emailUnico(),
      senhaHash: "12345",
    },
  });

  expect(resposta.status()).toBe(400);

  const corpo = await resposta.json();
  expect(corpo.senhaHash).toBe("Senha deve ter no mínimo 6 caracteres");
});

test("Não deve cadastrar e-mail duplicado", async ({ request }) => {
  // Defeito conhecido: hoje o backend responde 500 em vez de 409.
  // Quando for corrigido, é só remover a linha abaixo.
  test.fail();

  const email = emailUnico();
  const dados = {
    nomeCompleto: "Ana Souza",
    email: email,
    senhaHash: "123456",
  };

  const primeira = await request.post(`${BASE_URL}/usuario`, { data: dados });
  expect(primeira.status()).toBe(201);

  const segunda = await request.post(`${BASE_URL}/usuario`, { data: dados });
  expect(segunda.status()).toBe(409);
});
