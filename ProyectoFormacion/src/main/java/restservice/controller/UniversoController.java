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

import restservice.dto.UniversoDTO;
import restservice.exception.ResourceNotFoundException;
import restservice.model.Universo;
import restservice.service.UniversoService;
@RestController
@RequestMapping("/api/v1")
public class UniversoController {

	static final String MENSAJE_ERROR="No se ha encontrado al universo";
	
	@Autowired
	private UniversoService universoService;
	
	private UniversoDTO convertUniversoToDTO(Universo universo) {
		return new UniversoDTO(universo.getNombre());
	}
	
	@GetMapping("/universos")
	public List<String> universos()throws ResourceNotFoundException{
		List<UniversoDTO> response = new ArrayList<>(); 
		List<String> respuesta=new ArrayList<>();
		List<Universo> universos = universoService.findAllUniversos();
		
		universos.forEach(universo -> response.add(convertUniversoToDTO(universo)));
		response.forEach(pd -> respuesta.add(pd.toString()));
		return respuesta;
	}
	
	@GetMapping("/universos/{id}")
	@ResponseBody
	public String getUniversoById(@PathVariable Integer id) throws ResourceNotFoundException{
		Optional<Universo>universo=universoService.findUniverso(id);
		if(universo.isEmpty()) {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
		}
		else {
			 
			 return new UniversoDTO(universo.get().getNombre()).toString();
		}
	}
	
	@PostMapping("/universos/delete/{id}")
	public ResponseEntity<UniversoDTO> deleteUniversoById(@PathVariable Integer id)throws ResourceNotFoundException{
		Optional<Universo>universo=universoService.findUniverso(id);
		if(universo.isEmpty()) {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
		}
		else {
			 
			universoService.deleteUniverso(id);
			return ResponseEntity.ok(new UniversoDTO(universo.get().getNombre()));
		}
		
		
	}
	
	@PostMapping("/universos/update/{id}")
	public void updateUniverso(@RequestBody UniversoDTO universoDTO,@PathVariable Integer id)throws ResourceNotFoundException{
		Optional<Universo>universo=universoService.findUniverso(id);
		if(universo.isPresent()) {
			universo.get().setNombre(universoDTO.getNombre());
			universoService.guardarUniverso(universo.get());
		}
		else {
			throw new ResourceNotFoundException(MENSAJE_ERROR);
		}
	}
	
	@PostMapping("/createUniverso")
	public void crearUniverso(@RequestBody UniversoDTO universo) {
		universoService.guardarUniverso(new Universo(universo.getNombre()));
		
	}
}
