package restservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import restservice.dto.SuperheroeDTO;
import restservice.exception.ResourceNotFoundException;
import restservice.model.Poder;
import restservice.model.Superheroe;
import restservice.service.SuperheroeService;
@RestController
@RequestMapping("/api/v1")
public class SuperheroeController {
	
	static final String MENSAJE_ERROR="No se ha encontrado al superheroe";
	
	@Autowired
	private SuperheroeService superheroeService;
	
	private SuperheroeDTO convertSuperheroeToDTO(Superheroe superheroe) {
		return new SuperheroeDTO(superheroe);
	}
	
	
	@GetMapping("/superheroes")
	public List<String> superheroes()throws ResourceNotFoundException{
		List<SuperheroeDTO> response = new ArrayList<>(); 
		List<Superheroe> superheroes = superheroeService.findAllSuperheroes();
		List<String> respuesta = new ArrayList<>();
		if(superheroes.isEmpty()) {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
		}
		else {
			
			superheroes.forEach(superheroe -> response.add(convertSuperheroeToDTO(superheroe)));
			response.forEach(pd -> respuesta.add(pd.toString()));
			return respuesta;
		}
	}
	
	@GetMapping("/superheroesByName/{nombre}")
	public List<String> superheroesByName(@PathVariable String nombre)throws ResourceNotFoundException{
		List<SuperheroeDTO> response = new ArrayList<>(); 
		List<String> respuesta=new ArrayList<>();
		List<Superheroe> superheroes = superheroeService.findByNameContaining(nombre);
		superheroes.forEach(superheroe -> response.add(convertSuperheroeToDTO(superheroe)));
		response.forEach(pd -> respuesta.add(pd.toString()));
		return respuesta;
	}
	
	@GetMapping("/superheroes/{id}")
	@ResponseBody
	public String getSuperheroeById(@PathVariable Integer id) throws ResourceNotFoundException{
		Optional<Superheroe>superheroe=superheroeService.findSuperheroe(id);
		if(superheroe.isPresent()) {
			return new SuperheroeDTO(superheroe.get()).toString();
		}
		else {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
		}
	}
	@PostMapping("/superheroes/matar/{id}")
	public void matarSuperheroe(@PathVariable Integer id)throws ResourceNotFoundException{
		Optional<Superheroe>superheroe=superheroeService.findSuperheroe(id);
		if(superheroe.isEmpty()) {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
		}
		else {
			superheroe.get().setEstado(false);
			superheroeService.guardarSuperheroe(superheroe.get());
		}
	}
	
	@PostMapping("/superheroes/revivir/{id}")
	public void revivirSuperheroe(@PathVariable Integer id)throws ResourceNotFoundException{
		Optional<Superheroe>superheroe=superheroeService.findSuperheroe(id);
		if(superheroe.isEmpty()) {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
		}
		else {
			superheroe.get().setEstado(true);
			superheroeService.guardarSuperheroe(superheroe.get());
		}
	}
	
	@PostMapping("/superheroes/delete/{id}")
	public ResponseEntity<SuperheroeDTO> deleteSuperheroeById(@PathVariable Integer id)throws ResourceNotFoundException{
		Optional<Superheroe>superheroe=superheroeService.findSuperheroe(id);
		if(superheroe.isPresent()) {
			superheroeService.deleteSuperheroe(id);
			return ResponseEntity.ok(new SuperheroeDTO());
		}
		else {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
		}
		
		
	}
	
	@PostMapping("/superheroes/agregarPoder/{id}")
	public void agregarPoderSuperheroe(@RequestBody Integer poder,@PathVariable Integer id) throws ResourceNotFoundException{
		Optional<Superheroe> superheroe=superheroeService.findSuperheroe(id);
		if(superheroe.isEmpty()) {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
		}
		else {
			List<Poder> poderes=superheroe.get().getPoder();
			poderes.add(new Poder(poder));
			superheroeService.guardarSuperheroe(superheroe.get());
		}
	}
	
	@PostMapping("/superheroes/eliminarPoder/{id}")
	public void eliminarPoderSuperheroe(@RequestBody Integer poder,@PathVariable Integer id) throws ResourceNotFoundException{
		Optional<Superheroe> superheroe=superheroeService.findSuperheroe(id);
		if(superheroe.isEmpty()) {
			throw new ResourceNotFoundException(MENSAJE_ERROR);	
		}
		else {
			List<Poder> poderes=superheroe.get().getPoder();
			poderes.remove(new Poder(poder));
			superheroe.get().setPoder(poderes);
			superheroeService.guardarSuperheroe(superheroe.get());
		}
	}
	
	@PostMapping("/superheroes/update/{id}")
	public void updateSuperheroe(@RequestBody SuperheroeDTO superheroeDTO,@PathVariable Integer id) throws ResourceNotFoundException{
		Optional<Superheroe> superheroe=superheroeService.findSuperheroe(id);
		if(superheroe.isEmpty()) {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
		}
		else {
			superheroe.get().setNombre(superheroeDTO.getNombre());
			superheroe.get().setEstado(superheroeDTO.isEstado());
			superheroeDTO.getPoderes().forEach(pd-> superheroe.get().getPoder().add(new Poder(pd)));
			superheroe.get().setId_universo(superheroeDTO.getUniversoId());
			
			superheroeService.guardarSuperheroe(superheroe.get());
		}
	}
	
	@PostMapping("/createSuperheroe")
	public void crearSuperheroe(@RequestBody SuperheroeDTO superheroeDTO) {
		
		Superheroe superheroe=new Superheroe(superheroeDTO.getNombre());
		superheroe.setEstado(superheroeDTO.isEstado());
		superheroe.setId_universo(superheroeDTO.getUniversoId());
		
		superheroeDTO.getPoderes().forEach(pd -> superheroe.getPoder().add(new Poder(pd)));
		
		superheroeService.guardarSuperheroe(superheroe);
		
	}
	
}
