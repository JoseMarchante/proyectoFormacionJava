package restservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restservice.model.Superheroe;



@Repository
public interface SuperheroeRepository extends JpaRepository<Superheroe, Integer>{
	List<Superheroe> findByNombreContaining(String nombre);
	
}
