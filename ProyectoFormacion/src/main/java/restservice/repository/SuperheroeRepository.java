package restservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import restservice.model.Superheroe;



@Repository
public interface SuperheroeRepository extends CrudRepository<Superheroe, Long>{
	@Query("select u from superheroe s where s.nombre like %?1%")
	List<Superheroe> buscarSuperheroePorNombre(String nombre);
}
