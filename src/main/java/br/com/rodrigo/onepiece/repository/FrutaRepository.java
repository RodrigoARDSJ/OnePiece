package br.com.rodrigo.onepiece.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigo.onepiece.model.Fruta;

@Repository
public interface FrutaRepository extends JpaRepository<Fruta, Long> {

}
