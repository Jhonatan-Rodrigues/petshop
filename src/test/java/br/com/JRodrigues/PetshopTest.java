package br.com.JRodrigues;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.JRodrigues.dao.IPetshopDAO;
import br.com.JRodrigues.dao.PetshopDAO;
import br.com.JRodrigues.domain.Petshop;

public class PetshopTest {

	@Test
	public void cadastrarTest() throws Exception {
		IPetshopDAO dao = new PetshopDAO();
		Petshop pet = new Petshop();

		pet.setCodigo("1");
		pet.setRacaAnimal("Pitbull");

		Integer qtd = dao.cadastrar(pet);
		assertTrue(qtd == 1);

		Petshop petBD = dao.consultar(pet.getCodigo());
		assertNotNull(petBD);
		assertNotNull(petBD.getId());
		assertEquals(pet.getCodigo(), petBD.getCodigo());
		assertEquals(pet.getRacaAnimal(), petBD.getRacaAnimal());

		Integer qtdDel = dao.excluir(petBD);
		assertNotNull(qtdDel);

	}

	@Test
	public void buscarTodosTest() throws Exception {
		IPetshopDAO dao = new PetshopDAO();
		Petshop pet = new Petshop();
		pet.setCodigo("1");
		pet.setRacaAnimal("Pitbull");
		Integer qtd = dao.cadastrar(pet);
		assertTrue(qtd == 1);

		Petshop pet1 = new Petshop();
		pet1.setCodigo("2");
		pet1.setRacaAnimal("Labrador");
		Integer qtd1 = dao.cadastrar(pet1);
		assertTrue(qtd1 == 1);

		List<Petshop> list = dao.buscarTodos();
		assertNotNull(list);
		assertTrue(list.size() == 2);
		
		int countDel = 0;
		for (Petshop pe : list) {
			dao.excluir(pe);
			countDel++;
		}
		assertEquals(list.size(), countDel);
		list = dao.buscarTodos();

		assertEquals(list.size(), 0);
		
		

		

	}

}
