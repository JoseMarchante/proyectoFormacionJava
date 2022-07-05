package restservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import restservice.model.Superheroe;



@Repository
public interface SuperheroeRepository extends CrudRepository<Superheroe, Long>{

}
