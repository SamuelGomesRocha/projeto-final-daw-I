# Projeto final DAW-I

### Projeto final da disciplina de Desenvolvimento Web I

##  Features do sistema: 

1. Listagem de Leilões, com filtro. O filtro deve permitir filtrar por leilões ABERTOS,
FINALIZADOS, EXPIRADOS e INATIVO;
2. Um leilão é considerado INATIVO quando este é cadastrado no sistema;
3. Um leilão é considerado ABERTO quando este está apto a receber lances;
4. Um leilão expira com base na sua data de expiração. Desta forma o leilão assume o
estado EXPIRADO;
5. Leilões expirados podem ser finalizados, assumindo o estado FINALIZADO;
6. O estado FINALIZADO é atribuído a um leilão a partir do estado EXPIRADO ou do
estado ABERTO.
7. A partir do momento que um leilão assume o estado ABERTO, este pode receber
lances. 
8. Sobre os lances as seguintes regras devem ser observadas:
      * Um lance nunca pode assumir um valor menor que o lance mínimo de um leilão;
      * Um lance nunca pode assumir um valor igual ao último lances;
      * Um mesmo participante não pode efetuar dois lances seguidos. No entanto, o
participante pode efetuar quantos lances quiser.
11. Quando um leilão assumir o estado FINALIZADO, o ganhador do leilão deve receber um
e-mail parabenizando-o pelo arremate.
12. É considerado ganhador do leilão, o proponente do maior lance efetuado para o leilão.
13. O participante deve ser previamente cadastrado.
14. Uma feature importante, é permitir que o usuário tenha acesso à lista de lances de um
determinado leilão.
