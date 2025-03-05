alter table algafood.restaurante add aberto tinyint(1) not null;
update algafood.restaurante set aberto = true;