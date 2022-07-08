package restservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Poder;
import restservice.model.Superheroe;
import restservice.repository.PoderRepository;
import restservice.repository.SuperheroeRepository;
import restservice.service.SuperheroeService;

@Service
public class SuperheroeServiceImpl implements SuperheroeService {

	@Autowired
	SuperheroeRepository repository;
	@Autowired
	PoderRepository poderRepository;
	
	@Override
	public Superheroe guardarSuperheroe(Superheroe superheroe) {
		return repository.save(superheroe);
	}

	@Override
	public void deleteSuperheroe(Integer id_superheroe) throws ResourceNotFoundException{
		if (repository.existsById(id_superheroe)) {
			repository.deleteById(id_superheroe);
		}
		
	}

	@Override
	public List<Superheroe> findAllSuperheroes() throws ResourceNotFoundException {
		List<Superheroe> superheroes = repository.findAll();
		if(superheroes.isEmpty()) {
			throw new ResourceNotFoundException("No existen superheroes en la base de datos");
		}
		return superheroes;
	}

	@Override
	public Optional<Superheroe> findSuperheroe(Integer id) throws ResourceNotFoundException {
		return repository.findById(id);
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

	@Override
	public Optional<Superheroe> agregarPoderSuperheroe(Integer id,Poder poder) throws ResourceNotFoundException{
		Optional<Superheroe> superheroe = findSuperheroe(id);
		if(superheroe.isEmpty()) {
			throw new ResourceNotFoundException("No se ha encontrado el superheroe");
		}
		else {
			
			List<Poder> poderes=superheroe.get().getPoder();
			poderes.add(poder);
			superheroe.get().setPoder(poderes);
			return superheroe;
		}
	}

	@Override
	public List<Superheroe> findByNameContaining(String nombre) throws ResourceNotFoundException {
		return repository.findByNombreContaining(nombre);
	}

}
