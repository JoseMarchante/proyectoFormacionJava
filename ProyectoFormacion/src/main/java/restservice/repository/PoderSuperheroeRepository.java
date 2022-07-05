package restservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import restservice.model.PoderSuperheroe;
import restservice.model.PoderSuperheroeKey;


@Repository
public interface PoderSuperheroeRepository extends CrudRepository<PoderSuperheroe, PoderSuperheroeKey> {

}
