-- Criando tabela estado
create table estado (
                        id bigint not null auto_increment,
                        nome varchar(80) not null,

                        primary key (id)
) engine=InnoDB default charset=utf8;

-- inserindo os dados da coluna nome_estado da tabela cidade
-- na coluna nome da tabela estado
insert into estado (nome) select distinct nome_estado from cidade;

-- Criando coluna "estado_id" na tabela cidade
alter table cidade add column estado_id bigint not null;

-- Inserindo os ids na coluna estado_id de acordo com os ids
-- da tabela estado
update cidade c set c.estado_id = (select e.id from estado e where e.nome = c.nome_estado);

-- adicionando constraint fk
alter table cidade add constraint fk_cidade_estado
    foreign key (estado_id) references estado (id);

-- deletando a coluna nome_estado da tabela cidade
alter table cidade drop column nome_estado;

-- alterando o nome da coluna "nome_estado" da tabela cidade
-- para nome
alter table cidade change nome_cidade nome varchar(80) not null;