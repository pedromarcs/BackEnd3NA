import { test, expect, APIRequestContext } from "@playwright/test";

const BASE_URL = "http://localhost:8080";

function emailUnico(): string {
  return `login${Date.now()}${Math.floor(Math.random() * 1000)}@email.com`;
}

async function criarUsuario(
  request: APIRequestContext,
  email: string,
  senha: string
): Promise<void> {
  const resposta = await request.post(`${BASE_URL}/usuario`, {
    data: { nomeCompleto: "Usuario Teste", email: email, senhaHash: senha },
  });
  expect(resposta.status()).toBe(201);
}

async function fazerLogin(request: APIRequestContext, email: string, senha: string) {
  return request.post(`${BASE_URL}/usuario/login`, {
    data: { email: email, senhaHash: senha },
  });
}

// ---------- Testes com resultado esperado ----------

test("Deve fazer login com e-mail e senha corretos", async ({ request }) => {
  const email = emailUnico();
  await criarUsuario(request, email, "123456");

  const resposta = await fazerLogin(request, email, "123456");

  expect(resposta.status()).toBe(200);
  const corpo = await resposta.json();
  expect(corpo.email).toBe(email);
});

test("Não deve fazer login com senha incorreta", async ({ request }) => {
  const email = emailUnico();
  await criarUsuario(request, email, "123456");

  const resposta = await fazerLogin(request, email, "senhaerrada");

  expect(resposta.status()).toBe(401);
  expect(await resposta.text()).toBe("Senha incorreta!");
});

test("Não deve fazer login com e-mail inexistente", async ({ request }) => {
  const resposta = await fazerLogin(request, emailUnico(), "123456");

  expect(resposta.status()).toBe(404);
  expect(await resposta.text()).toBe("Usuário não encontrado!");
});

// ---------- Exploratórios: só registram o que o sistema respondeu ----------

test("Explorar - e-mail em letras maiúsculas", async ({ request }) => {
  const email = emailUnico();
  await criarUsuario(request, email, "123456");

  const resposta = await fazerLogin(request, email.toUpperCase(), "123456");
  const corpo = (await resposta.text()).slice(0, 150);
  console.log(`[LOGIN maiusculas] status=${resposta.status()} corpo=${corpo}`);
});

test("Explorar - corpo vazio", async ({ request }) => {
  const resposta = await request.post(`${BASE_URL}/usuario/login`, { data: {} });
  const corpo = (await resposta.text()).slice(0, 150);
  console.log(`[LOGIN corpo vazio] status=${resposta.status()} corpo=${corpo}`);
});

test("Explorar - 5 senhas erradas e depois a senha certa", async ({ request }) => {
  const email = emailUnico();
  await criarUsuario(request, email, "123456");

  const statusErrados: number[] = [];
  for (let i = 0; i < 5; i++) {
    const erro = await fazerLogin(request, email, "errada" + i);
    statusErrados.push(erro.status());
  }
  const certa = await fazerLogin(request, email, "123456");

  console.log(`[LOGIN tentativas] 5 erradas=${statusErrados.join(",")} depois a certa=${certa.status()}`);
});

test("Explorar - a resposta do login devolve a senha?", async ({ request }) => {
  const email = emailUnico();
  await criarUsuario(request, email, "senhaSecreta123");

  const resposta = await fazerLogin(request, email, "senhaSecreta123");
  const corpo = await resposta.text();
  console.log(`[LOGIN senha na resposta] status=${resposta.status()} contem senhaHash=${corpo.includes("senhaHash")} contem a senha=${corpo.includes("senhaSecreta123")}`);
});
