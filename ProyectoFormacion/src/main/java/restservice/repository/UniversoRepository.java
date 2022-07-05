package restservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import restservice.model.Universo;
@Repository
public interface UniversoRepository extends CrudRepository<Universo, Long>{

}
