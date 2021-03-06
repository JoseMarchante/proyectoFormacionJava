package restservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Universo;
import restservice.repository.UniversoRepository;
import restservice.service.UniversoService;

@Service
public class UniversoServiceImpl implements UniversoService {
	
	@Autowired
	UniversoRepository repository;
	
	
	@Override
	public Universo guardarUniverso(Universo universo) {
		return repository.save(universo);
	}

	@Override
	public List<Universo> findAllUniversos() throws ResourceNotFoundException {
		List<Universo> universos = repository.findAll();
		if(universos.isEmpty()) {
			throw new ResourceNotFoundException("No existen universos en la base de datos");
		}
		return universos;
	}

	@Override
	public void deleteUniverso(Integer id_universo) throws ResourceNotFoundException{
		if (repository.existsById(id_universo)) {
			repository.deleteById(id_universo);
		}
		
	}
	
	@Override
	public Optional<Universo> findUniverso(Integer id) throws ResourceNotFoundException {
		return repository.findById(id);
	}

	@Override
	public Universo findByNombre(String nombre) throws ResourceNotFoundException{
		return repository.findByNombre(nombre);
	}

}
