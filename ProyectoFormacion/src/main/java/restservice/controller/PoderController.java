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

import restservice.dto.PoderDTO;
import restservice.exception.ResourceNotFoundException;
import restservice.model.Poder;
import restservice.service.PoderService;
@RestController
@RequestMapping("/api/v1")
public class PoderController {

	static final String MENSAJE_ERROR="No se ha encontrado el poder";
	
	@Autowired
	private PoderService poderService;
	
	private PoderDTO convertPoderToDTO(Poder poder) {
		return new PoderDTO(poder);
	}
	
	@GetMapping("/poderes")
	public List<String> poderes() throws ResourceNotFoundException {
		List<PoderDTO> response = new ArrayList<>(); 
		List<String> respuesta= new ArrayList<>();
		List<Poder> poderes = poderService.findAllPoderes();
		if(poderes.isEmpty()) {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
		}
		else {
			poderes.forEach(poder -> response.add(convertPoderToDTO(poder)));
		}
		response.forEach(pd -> respuesta.add(pd.toString()));
		return respuesta;
	}
	
	@GetMapping("/poderes/{id}")
	@ResponseBody
	public String getPoderById(@PathVariable Integer id) throws ResourceNotFoundException{
		Optional<Poder>poder=poderService.findPoder(id);
		if(poder.isEmpty()) {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
			
		}
		else {
			return new PoderDTO(poder.get().getNombre()).toString();
		}
	}
	@PostMapping("/poderes/delete/{id}")
	public ResponseEntity<PoderDTO> deletePoderById(@PathVariable Integer id)throws ResourceNotFoundException{
		Optional<Poder>poder=poderService.findPoder(id);
		if(poder.isEmpty()) {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
			
		}
		else {
			poderService.deletePoder(id);
			return ResponseEntity.ok(new PoderDTO(poder.get().getNombre()));
		}
		
		
	}
	
	@PostMapping("/poderes/update/{id}")
	public void updatePoder(@RequestBody PoderDTO poderDTO,@PathVariable Integer id)throws ResourceNotFoundException{
		Optional<Poder>poder=poderService.findPoder(id);
		if(poder.isPresent()) {
			poder.get().setNombre(poderDTO.getNombre());
			poderService.guardarPoder(poder.get());
		}
		else {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
		}
	}
	
	
	
	@PostMapping(value = "/createPoder")
	public void crearPoder(@RequestBody PoderDTO poder) {
		
		poderService.guardarPoder(new Poder(poder.getNombre()));
		
	}
}
