package br.com.rodrigo.onepiece.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigo.onepiece.model.Personagem;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

	List<Personagem> findByFrutaNome(String fruta);
	
}