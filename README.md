# Projeto final DAW-I

### Projeto final da disciplina de Desenvolvimento Web I

##  Features do sistema 

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

## Instalação do sistema

### Banco de dados

Este sistema manipula seus dados a partir do estabelecimento de uma conexão JDBC com um banco de dados MySQL v8.

#### Scripts utilizados para criar as tabelas:

##### Lances
```SQL
CREATE TABLE lance(
    id_lance int not null auto_increment primary key,
    valor_lance double not null,
    id_proponente varchar(14) not null,
    id_leilao int not null,
    constraint fk_proponente FOREIGN KEY (id_proponente) references proponente(cpf) on delete cascade on update cascade,
    constraint fk_leilao foreign key (id_leilao) references leilao(idLeilao) on delete cascade on update cascade);
```

##### Proponente
```SQL
CREATE TABLE proponente(cpf varchar(14) not null primary key,
		        nome varchar(80) not null,
                        email varchar(80) not null,
                        telefone varchar(14),
                        password varchar(40) not null,
                        user_name varchar(20) not null);
 ```
