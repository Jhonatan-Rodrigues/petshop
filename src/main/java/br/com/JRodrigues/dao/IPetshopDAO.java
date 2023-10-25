package br.com.JRodrigues.dao;

import java.util.List;

import br.com.JRodrigues.domain.Petshop;

public interface IPetshopDAO {

	Integer cadastrar(Petshop pet) throws Exception;

	Petshop consultar(String codigo)throws Exception;

	Integer excluir(Petshop pet)throws Exception;

	List<Petshop> buscarTodos()throws Exception;
	
	

}
