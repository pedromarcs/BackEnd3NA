import { test } from "@playwright/test";

const BASE_URL = "http://localhost:8080";

test("H3 - a resposta do cadastro devolve a senha?", async ({ request }) => {
  const resposta = await request.post(`${BASE_URL}/usuario`, {
    data: {
      nomeCompleto: "Ana Souza",
      email: `teste${Date.now()}@email.com`,
      senhaHash: "senhaSecreta123",
    },
  });
  const corpo = await resposta.text();
  console.log("status:", resposta.status());
  console.log("resposta contém o campo senhaHash?", corpo.includes("senhaHash"));
  console.log("resposta contém a senha digitada?", corpo.includes("senhaSecreta123"));
});
