alter table algafood.restaurante add ativo tinyint(1) not null;
update algafood.restaurante set ativo = true;