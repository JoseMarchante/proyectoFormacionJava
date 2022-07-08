package restservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Poder;
import restservice.repository.PoderRepository;
import restservice.service.PoderService;

@Service
public class PoderServiceImpl implements PoderService{

	@Autowired
	public PoderRepository repository;
	
	@Override
	public Poder guardarPoder(Poder poder) {
		return repository.save(poder);
	}

	@Override
	public void deletePoder(Integer id_poder)throws ResourceNotFoundException {
		if (repository.existsById(id_poder)) {
			repository.deleteById(id_poder);
		}
		
	}

	@Override
	public List<Poder> findAllPoderes() throws ResourceNotFoundException {
		List<Poder> poderes =repository.findAll();
		if(poderes.isEmpty()) {
			throw new ResourceNotFoundException("No existen poderes en la base de datos");

		}
		return poderes;
	}

	@Override
	public Optional<Poder> findPoder(Integer id) throws ResourceNotFoundException {
		return repository.findById(id);
	}

	@Override
	public Poder findByNombre(String nombre) throws ResourceNotFoundException {
		return repository.findByNombre(nombre);
	}


}
