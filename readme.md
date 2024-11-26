# Trabalho Substitutivo

RM - 97891

## Descrição Geral
Esta aplicação tem como objetivo gerenciar projetos relacionados a energias renováveis, permitindo o cadastro de fontes de energia, projetos, investimentos e dados sobre a geração de energia. A aplicação será usada para acompanhar o impacto e o progresso de iniciativas sustentáveis no setor energético.

### Objetivo Principal
Prover um sistema que facilite o gerenciamento de informações sobre fontes renováveis, projetos de energia e seus respectivos investimentos, incentivando o uso de tecnologias limpas e a transição para um modelo energético sustentável.

---

## Entidades e Seus Propósitos
A seguir estão as entidades do sistema, com uma descrição do papel de cada uma:

### 1. Usuário (Usuario)
- **Descrição:** Representa os usuários que acessam o sistema. Cada usuário possui informações básicas como nome de usuário, email e senha.
- **Campos:**
  - `id`: Identificador único do usuário.
  - `nomeUsuario`: Nome de usuário para identificação.
  - `email`: Endereço de email único usado para login.
  - `senha`: Senha criptografada para acesso seguro.

---

### 2. Fonte Renovável (FonteRenovavel)
- **Descrição:** Representa as fontes de energia renováveis disponíveis, como solar e eólica.
- **Campos:**
  - `id`: Identificador único da fonte.
  - `nome`: Nome da fonte (ex.: "Solar", "Eólica").
  - `descricao`: Descrição detalhada sobre a fonte renovável.

---

### 3. Projeto de Energia (ProjetoEnergia)
- **Descrição:** Representa um projeto específico de geração de energia, como uma usina solar ou parque eólico.
- **Campos:**
  - `id`: Identificador único do projeto.
  - `nome`: Nome do projeto.
  - `localizacao`: Local onde o projeto está instalado.
  - `capacidade`: Capacidade de geração do projeto em megawatts (MW).
  - `idFonteRenovavel`: Identificador da fonte renovável associada ao projeto.

---

### 4. Investimento (Investimento)
- **Descrição:** Representa um registro de investimento financeiro em projetos de energia renovável.
- **Campos:**
  - `id`: Identificador único do investimento.
  - `idUsuario`: Identificador do usuário que realizou o investimento.
  - `idProjetoEnergia`: Identificador do projeto no qual o investimento foi realizado.
  - `valor`: Valor do investimento em reais (R$).

---

### 5. Dado de Energia (DadoEnergia)
- **Descrição:** Armazena registros sobre a energia gerada por um projeto em determinado período.
- **Campos:**
  - `id`: Identificador único do dado.
  - `idProjetoEnergia`: Identificador do projeto relacionado.
  - `energiaGerada`: Quantidade de energia gerada em megawatts-hora (MWh).
  - `data`: Data do registro no formato ISO (yyyy-MM-dd).

---

## Modelo de Relacionamento
### Relacionamentos entre as Entidades
- **Usuario ↔ Investimento:**
  - Um usuário pode realizar múltiplos investimentos.
- **FonteRenovavel ↔ ProjetoEnergia:**
  - Cada projeto está associado a uma única fonte renovável.
- **ProjetoEnergia ↔ DadoEnergia:**
  - Cada projeto pode ter múltiplos registros de geração de energia.
- **ProjetoEnergia ↔ Investimento:**
  - Cada investimento está vinculado a um único projeto.

---

## Uso Previsto do Sistema
1. **Cadastro de Fontes Renováveis:**
   - Inserir informações sobre fontes de energia como solar e eólica.
2. **Gerenciamento de Projetos:**
   - Adicionar e acompanhar informações sobre projetos de energia renovável.
3. **Registro de Investimentos:**
   - Permitir que usuários registrem investimentos nos projetos.
4. **Acompanhamento de Energia Gerada:**
   - Armazenar dados da quantidade de energia gerada ao longo do tempo.
