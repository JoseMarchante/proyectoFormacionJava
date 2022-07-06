package restservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Superheroe;
import restservice.repository.SuperheroeRepository;
import restservice.service.SuperheroeService;

public class SuperheroeServiceImpl implements SuperheroeService {

	@Autowired
	SuperheroeRepository repository;
	
	@Override
	public Superheroe guardarSuperheroe(Superheroe superheroe) {
		return repository.save(superheroe);
	}

	@Override
	public void deleteSuperheroe(Long id_superheroe) {
		if (repository.existsById(id_superheroe)) {
			repository.deleteById(id_superheroe);
		}
		
	}

	@Override
	public List<Superheroe> findAllSuperheroes() throws ResourceNotFoundException {
		List<Superheroe> superheroes = (List<Superheroe>) repository.findAll();
		if(superheroes.isEmpty()) {
			throw new ResourceNotFoundException("No existen superheroes en la base de datos");
		}
		return superheroes;
	}

	@Override
	public Optional<Superheroe> findSuperheroe(Long id) throws ResourceNotFoundException {
		return repository.findById(id);
	}

	@Override
	public List<Superheroe> findByNameSuperheroe(String nombre) throws ResourceNotFoundException {
		return repository.buscarSuperheroePorNombre(nombre);
	}

	@Override
	public void morir(Superheroe superheroe) {
		superheroe.setEstado(false);
		guardarSuperheroe(superheroe);
	}

	@Override
	public void revivir(Superheroe superheroe) {
		superheroe.setEstado(true);
		guardarSuperheroe(superheroe);
	}

}
