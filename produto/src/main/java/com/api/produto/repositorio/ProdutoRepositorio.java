package com.api.produto.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.api.produto.modelo.ProdutoModelo;

public interface ProdutoRepositorio extends CrudRepository<ProdutoModelo, Integer> {
    // Listar os Produtos
    List<ProdutoModelo> findAll();

    // Pesquisar por código
    ProdutoModelo findByCodigo(int id);

    // Remover Produto
    void delete(ProdutoModelo produto);

    // Cadastrar / Alterar Produto
    <ProdMod extends ProdutoModelo> ProdMod save(ProdMod produto);

}
