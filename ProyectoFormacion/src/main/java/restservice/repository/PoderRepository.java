package restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restservice.model.Poder;


@Repository
public interface PoderRepository extends JpaRepository<Poder, Integer> {
	Poder findByNombre(String nombre);
}
