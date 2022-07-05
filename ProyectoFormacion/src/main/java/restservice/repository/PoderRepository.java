package restservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import restservice.model.Poder;


@Repository
public interface PoderRepository extends CrudRepository<Poder, Long> {

}
