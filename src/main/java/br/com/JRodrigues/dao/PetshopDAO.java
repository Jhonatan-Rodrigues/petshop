package br.com.JRodrigues.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.JRodrigues.dao.jdbc.ConnectionFactory;
import br.com.JRodrigues.domain.Petshop;

public class PetshopDAO implements IPetshopDAO {

	@Override
	public Integer cadastrar(Petshop pet) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO TB_PETSHOP(ID, CODIGO, RACA_ANIMAL) " + "VALUES (nextval('sq_id'), ?, ?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, pet.getCodigo());
			stm.setString(2, pet.getRacaAnimal());
			return stm.executeUpdate();
			
		}
		catch(Exception e) {
			throw e;
			
		}
		finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection!= null && !connection.isClosed()) {
				connection.close();
			}
			
		}
		
	}

	@Override
	public Petshop consultar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Petshop pet = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_PETSHOP WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			rs = stm.executeQuery();
			if(rs.next()) {
				pet = new Petshop();
				pet.setId(rs.getLong("id"));
				pet.setCodigo(rs.getString("codigo"));
				pet.setRacaAnimal(rs.getString("raca_animal"));
			}
			return pet;
			
		}
		catch(Exception e) {
			throw e;
			
		}
		finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection!= null && !connection.isClosed()) {
				connection.close();
			}
			
		}
	}

	@Override
	public Integer excluir(Petshop pet) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM TB_PETSHOP WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, pet.getCodigo());
			return stm.executeUpdate();
			
		}
		catch(Exception e) {
			throw e;
			
		}
		finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection!= null && !connection.isClosed()) {
				connection.close();
			}
			
		}
	}

	@Override
	public List<Petshop> buscarTodos() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Petshop>  list = new ArrayList<>();
		Petshop pet = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_PETSHOP";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()) {
				pet = new Petshop();
				Long id = rs.getLong("id");
				String codigo = rs.getString("codigo");
				String racaAnimal = rs.getString("raca_animal");
				pet.setId(id);
				pet.setCodigo(codigo);
				pet.setRacaAnimal(racaAnimal);
				list.add(pet);
			}
			return list;
			
		}
		catch(Exception e) {
			throw e;
			
		}
		finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection!= null && !connection.isClosed()) {
				connection.close();
			}
			
		}
	}
	
	

}
