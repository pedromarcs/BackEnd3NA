import { test, APIRequestContext } from "@playwright/test";

const BASE_URL = "http://localhost:8080";

function emailUnico(): string {
  return `teste${Date.now()}${Math.floor(Math.random() * 1000)}@email.com`;
}

async function enviar(
  request: APIRequestContext,
  rotulo: string,
  dados: object
): Promise<void> {
  const resposta = await request.post(`${BASE_URL}/usuario`, { data: dados });
  const corpo = (await resposta.text()).slice(0, 250);
  console.log(`[${rotulo}] status=${resposta.status()} corpo=${corpo}`);
}

test("CP2 - nome vazio", async ({ request }) => {
  await enviar(request, "CP2 nome vazio", {
    nomeCompleto: "",
    email: emailUnico(),
    senhaHash: "123456",
  });
});

test("CP3 - e-mail sem @", async ({ request }) => {
  await enviar(request, "CP3 email sem @", {
    nomeCompleto: "Ana Souza",
    email: "ana.email.com",
    senhaHash: "123456",
  });
});

test("CP5 - senha com 6 caracteres", async ({ request }) => {
  await enviar(request, "CP5 senha 6 caracteres", {
    nomeCompleto: "Ana Souza",
    email: emailUnico(),
    senhaHash: "123456",
  });
});

test("H1 - nome com 151 caracteres", async ({ request }) => {
  await enviar(request, "H1 nome 151 caracteres", {
    nomeCompleto: "A".repeat(151),
    email: emailUnico(),
    senhaHash: "123456",
  });
});

test("H2 - e-mail duplicado trocando maiúsculas", async ({ request }) => {
  const email = emailUnico();
  await enviar(request, "H2 primeiro cadastro", {
    nomeCompleto: "Ana Souza",
    email: email,
    senhaHash: "123456",
  });
  await enviar(request, "H2 mesmo e-mail em maiusculas", {
    nomeCompleto: "Ana Souza",
    email: email.toUpperCase(),
    senhaHash: "123456",
  });
});

test("H3 - a resposta devolve a senha?", async ({ request }) => {
  await enviar(request, "H3 resposta do cadastro", {
    nomeCompleto: "Ana Souza",
    email: emailUnico(),
    senhaHash: "senhaSecreta123",
  });
});

test("H4 - data no futuro e valores negativos", async ({ request }) => {
  await enviar(request, "H4 data futura e pesos negativos", {
    nomeCompleto: "Ana Souza",
    email: emailUnico(),
    senhaHash: "123456",
    dataNascimento: "2090-01-01",
    pesoAtual: -50,
    altura: -1.5,
  });
});

test("H5 - e-mail sem ponto no domínio", async ({ request }) => {
  await enviar(request, "H5 email ana@email", {
    nomeCompleto: "Ana Souza",
    email: "ana@email",
    senhaHash: "123456",
  });
});

test("Extra - corpo vazio e gênero inválido", async ({ request }) => {
  await enviar(request, "Extra corpo vazio", {});
  await enviar(request, "Extra genero invalido", {
    nomeCompleto: "Ana Souza",
    email: emailUnico(),
    senhaHash: "123456",
    genero: "ALIEN",
  });
});
