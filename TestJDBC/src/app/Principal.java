package app;

import java.math.BigDecimal;

import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ProdutoDAO;
import db.DbException;
import modelo.Produto;

public class Principal {
	public static void main(String[] args) {
		SimpleDateFormat fd = new SimpleDateFormat("dd.MM.yyyy");
		NumberFormat fm = NumberFormat.getCurrencyInstance();
		
		ProdutoDAO dao = new ProdutoDAO();
		
		Produto pdt  = new Produto(null, 
									"Tablet",
									Date.valueOf("2023-12-25"),
									10,
									new BigDecimal("25999.99"));

		try {
			if (dao.excluir(19)) {
				System.out.println("Excluindo afetada com sucesso");
			} else {
				System.out.println("Registro não excluído!");
			}
		} catch (DbException e) {
			System.out.println(e.getMessage());
		}
		}
		pdt.setIdProd(8);
		Map<String, Integer> retorno = new HashMap<>();

		try {
			retorno = dao.salvar(pdt);

			System.out.println(retorno.get("afetados").toString() + " registro(s) afetado(s)");
			if (retorno.get("chave") != null) {
				System.out.println("Novo ID gerado: " + retorno.get("chave"));
			}
		} catch (DbException e) {
			System.out.println(e.getMessage());
		}

		}


	private ProdutoDAO dao;

		
		List<Produto> lista = dao.listarTodos();
		
		System.out.println("Lista de Contatos");
		System.out.println("-----------------");
		
		for (Produto produto : lista) {
			System.out.println("Id............ " + produto.getIdProd());
			System.out.println("Nome produto.: " + produto.getNomeProd());
			System.out.println("Data cadastro: " + fd.format(produto.getDataCadastro()));
			System.out.println("Quantidade...: " + produto.getQuantidade());
			System.out.println("Preço......... " + fm.format(produto.getPreco()));
			System.out.println();
		}
		
		Produto prod = dao.buscarPorId(2);
		if (prod !=  null) {
			System.out.println("Id............ " + prod.getIdProd());
			System.out.println("Nome produto.: " + prod.getNomeProd());
			System.out.println("Data cadastro: " + fd.format(prod.getDataCadastro()));
			System.out.println("Quantidade...: " + prod.getQuantidade());
			System.out.println("Preço......... " + fm.format(prod.getPreco()));
			System.out.println();
		}
	}
}
