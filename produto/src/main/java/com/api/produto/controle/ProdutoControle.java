package com.api.produto.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.produto.modelo.ProdutoModelo;
import com.api.produto.modelo.RespostaModelo;
import com.api.produto.repositorio.ProdutoRepositorio;

@RestController
@RequestMapping("/api")
public class ProdutoControle {

    // Ações
    @Autowired
    private ProdutoRepositorio acoes;

    // Listar Produtos
    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
    public @ResponseBody List<ProdutoModelo> listar() {
        return acoes.findAll();
    }

    // Procurar Produto
    @RequestMapping(value = "/produtos/{id}", method = RequestMethod.GET)
    public @ResponseBody ProdutoModelo filtrar(@PathVariable int id) {
        return acoes.findByCodigo(id);
    }

    // Cadastrar Produtos
    @RequestMapping(value = "/produtos", method = RequestMethod.POST)
    public @ResponseBody ProdutoModelo cadastrar(@RequestBody ProdutoModelo produto) {
        return acoes.save(produto);
    }

    // Atualizar Produto
    @RequestMapping(value = "/produtos/{id}", method = RequestMethod.PUT)
    public @ResponseBody ProdutoModelo atualizar(@RequestBody ProdutoModelo produto, @PathVariable int id) {
        acoes.save(acoes.findByCodigo(id));
        return acoes.save(produto);

    }

    // Deletar Produto
    @RequestMapping(value = "/produtos/{id}", method = RequestMethod.DELETE)
    public @ResponseBody RespostaModelo deletar(@PathVariable Integer id) {
        RespostaModelo resposta = new RespostaModelo();
        try {
            ProdutoModelo produto = filtrar(id);
            this.acoes.delete(produto);
            resposta.setMensagem("Produto Removido Com Sucesso!");
        } catch (Exception erro) {
            resposta.setMensagem("Falha Ao Remover: " + erro.getMessage());
        }
        return resposta;
    }

}
