package restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restservice.model.Universo;
@Repository
public interface UniversoRepository extends JpaRepository<Universo, Integer>{
	Universo findByNombre(String nombre);
}
