from dataclasses import dataclass
from typing import Dict, Optional

@dataclass
class Usuario:
    nome: str
    email: str
    senha: str
    idade: int
    tentativas_falhas: int = 0
    bloqueado: bool = False

usuarios: Dict[str, Usuario] = {}

def senha_valida(senha: str) -> bool:
    return len(senha) >= 8 and any(c.isdigit() for c in senha)

def email_valido(email: str) -> bool:
    return "@" in email and "." in email.split("@")[-1]

def cadastrar(nome: str, email: str, senha: str, idade: int) -> str:
    if not nome.strip():
        return "ERRO: nome obrigatório"
    if not email_valido(email):
        return "ERRO: e-mail inválido"
    if email in usuarios:
        return "ERRO: e-mail já cadastrado"
    if idade < 18 or idade > 120:
        return "ERRO: idade fora do intervalo permitido"
    if not senha_valida(senha):
        return "ERRO: senha deve ter ao menos 8 caracteres e 1 número"

    usuarios[email] = Usuario(nome.strip(), email, senha, idade)
    return "OK: cadastro realizado"

def login(email: str, senha: str) -> str:
    usuario: Optional[Usuario] = usuarios.get(email)

    if usuario is None:
        return "ERRO: usuário não encontrado"

    if usuario.bloqueado:
        return "ERRO: conta bloqueada"

    if senha == usuario.senha:
        usuario.tentativas_falhas = 0
        return "OK: login realizado"

    usuario.tentativas_falhas += 1

    if usuario.tentativas_falhas >= 3:
        usuario.bloqueado = True
        return "ERRO: conta bloqueada após 3 tentativas"

    return f"ERRO: senha incorreta ({usuario.tentativas_falhas}/3)"


# ---------------- testes ----------------

OK = "OK: cadastro realizado"
ERRO_IDADE = "ERRO: idade fora do intervalo permitido"
ERRO_SENHA = "ERRO: senha deve ter ao menos 8 caracteres e 1 número"

def testar(id, tecnica, entrada, esperado, obtido):
    status = "PASSOU" if esperado == obtido else "FALHOU"
    print(f"[{status}] {id} | {tecnica} | entrada: {entrada} | esperado: {esperado} | obtido: {obtido}")

def titulo(texto):
    print("\n=== " + texto + " ===")

def limpar():
    usuarios.clear()

def criar_ana():
    limpar()
    cadastrar("Ana", "ana@email.com", "senha123", 25)


# Etapa 1 - níveis de teste
titulo("ETAPA 1 - NIVEIS")
testar("N1", "Unidade", "senha_valida('abcdefg1')", True, senha_valida("abcdefg1"))
testar("N2", "Unidade", "email_valido('ana@email.com')", True, email_valido("ana@email.com"))
limpar()
testar("N3", "Integração", "cadastrar válido + duplicado", [OK, "ERRO: e-mail já cadastrado"],
       [cadastrar("Ana", "ana@email.com", "senha123", 25), cadastrar("Ana", "ana@email.com", "senha123", 25)])
limpar()
cadastrar("Ana", "ana@email.com", "senha123", 25)
testar("N4", "Sistema", "cadastro -> login", "OK: login realizado", login("ana@email.com", "senha123"))

# Etapa 2 - caixa-preta: idade (18 a 120)
titulo("ETAPA 2 - IDADE")
casos_idade = [
    ("CT1", "Limite inferior", 17, ERRO_IDADE),
    ("CT2", "Limite inferior", 18, OK),
    ("CT3", "Limite inferior", 19, OK),
    ("CT4", "Equivalência válida", 50, OK),
    ("CT5", "Limite superior", 119, OK),
    ("CT6", "Limite superior", 120, OK),
    ("CT7", "Limite superior", 121, ERRO_IDADE),
]
for id, tecnica, idade, esperado in casos_idade:
    limpar()
    testar(id, tecnica, f"idade {idade}", esperado, cadastrar("Ana", "ana@email.com", "senha123", idade))

# Etapa 3 - caixa-preta: senha
titulo("ETAPA 3 - SENHA")
casos_senha = [
    ("S1", "Menos de 8 (vazia)", "", ERRO_SENHA),
    ("S2", "Limite: 7 com número", "abcdef1", ERRO_SENHA),
    ("S3", "8 sem número", "abcdefgh", ERRO_SENHA),
    ("S4", "Limite: 8 com número", "abcdefg1", OK),
    ("S5", "9 com número", "abcdefgh1", OK),
    ("S6", "Longa com número", "senha_bem_longa_123", OK),
]
for id, tecnica, senha, esperado in casos_senha:
    limpar()
    testar(id, tecnica, f"senha '{senha}'", esperado, cadastrar("Ana", "ana@email.com", senha, 25))

# Etapa 4 - caixa-branca: cadastrar()
titulo("ETAPA 4 - CAIXA-BRANCA CADASTRAR")
limpar()
testar("CB1", "Decisão nome = V", "nome '   '", "ERRO: nome obrigatório", cadastrar("   ", "ana@email.com", "senha123", 25))
limpar()
testar("CB2", "Decisão e-mail = V", "e-mail 'ana.com'", "ERRO: e-mail inválido", cadastrar("Ana", "ana.com", "senha123", 25))
criar_ana()
testar("CB3", "Decisão duplicado = V", "ana@email.com de novo", "ERRO: e-mail já cadastrado", cadastrar("Ana", "ana@email.com", "senha123", 25))
limpar()
testar("CB4", "Decisão idade = V", "idade 17", ERRO_IDADE, cadastrar("Ana", "ana@email.com", "senha123", 17))
limpar()
testar("CB5", "Decisão senha = V", "senha 'curta'", ERRO_SENHA, cadastrar("Ana", "ana@email.com", "curta", 25))
limpar()
testar("CB6", "Todas as decisões = F", "dados válidos", OK, cadastrar("Ana", "ana@email.com", "senha123", 25))

# Etapa 5 - caminhos do login()
titulo("ETAPA 5 - CAMINHOS LOGIN")
limpar()
testar("P1", "Usuário inexistente", "e-mail não cadastrado", "ERRO: usuário não encontrado", login("x@email.com", "senha123"))
criar_ana()
login("ana@email.com", "errada"); login("ana@email.com", "errada"); login("ana@email.com", "errada")
testar("P2", "Conta bloqueada", "bloqueada + senha correta", "ERRO: conta bloqueada", login("ana@email.com", "senha123"))
criar_ana()
testar("P3", "Senha correta", "senha123", "OK: login realizado", login("ana@email.com", "senha123"))
criar_ana()
testar("P4", "Senha incorreta (1ª vez)", "errada", "ERRO: senha incorreta (1/3)", login("ana@email.com", "errada"))
criar_ana()
login("ana@email.com", "errada"); login("ana@email.com", "errada")
testar("P5", "Terceira tentativa errada", "errada (3ª)", "ERRO: conta bloqueada após 3 tentativas", login("ana@email.com", "errada"))

# Etapa 6 - teste exploratório
titulo("ETAPA 6 - EXPLORATORIO")
criar_ana()
testar("EX1", "Exploratório", "cadastrar ANA@email.com (já existe ana@email.com)", "ERRO: e-mail já cadastrado", cadastrar("Ana2", "ANA@email.com", "senha123", 25))
limpar()
testar("EX2", "Exploratório", "e-mail ' ana@email.com' (espaço no início)", "ERRO: e-mail inválido", cadastrar("Ana", " ana@email.com", "senha123", 25))
limpar()
testar("EX3", "Exploratório", "e-mail '@a.b'", "ERRO: e-mail inválido", cadastrar("Ana", "@a.b", "senha123", 25))
limpar()
testar("EX4", "Exploratório", "e-mail 'a@.com'", "ERRO: e-mail inválido", cadastrar("Ana", "a@.com", "senha123", 25))
limpar()
testar("EX5", "Exploratório", "idade 18.5", ERRO_IDADE, cadastrar("Ana", "ana@email.com", "senha123", 18.5))
limpar()
try:
    resultado = cadastrar("Ana", "ana@email.com", "senha123", "25")
except Exception as erro:
    resultado = "EXCEÇÃO " + type(erro).__name__
testar("EX6", "Exploratório", "idade como texto '25'", ERRO_IDADE, resultado)
criar_ana()
testar("EX7", "Exploratório", "login ANA@EMAIL.COM (cadastrado como ana@email.com)", "OK: login realizado", login("ANA@EMAIL.COM", "senha123"))
criar_ana()
login("ana@email.com", "errada"); login("ana@email.com", "errada"); login("ana@email.com", "errada")
testar("EX8", "Exploratório", "bloqueada, tenta de novo com senha correta", "ERRO: conta bloqueada", login("ana@email.com", "senha123"))
criar_ana()
login("ana@email.com", "errada"); login("ana@email.com", "errada"); login("ana@email.com", "senha123")
testar("EX9", "Exploratório", "2 erros, 1 acerto e 1 erro", "ERRO: senha incorreta (1/3)", login("ana@email.com", "errada"))
criar_ana()
testar("EX10", "Exploratório", "senha guardada em texto puro?", False, usuarios["ana@email.com"].senha == "senha123")
limpar()
testar("EX11", "Exploratório", "senha '       1' (7 espaços e 1 número)", ERRO_SENHA, cadastrar("Ana", "ana@email.com", "       1", 25))

print("\nFim dos testes.")
