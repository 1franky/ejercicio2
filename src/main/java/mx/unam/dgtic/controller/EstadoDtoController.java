package mx.unam.dgtic.controller;

import mx.unam.dgtic.dto.EstadoDto;
import mx.unam.dgtic.servicio.IEstadoDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estados")
public class EstadoDtoController {

    @Autowired
    IEstadoDtoService estadoDtoService;

   @GetMapping(path = "/")
   public List<EstadoDto> listarEstados(){
       return estadoDtoService.getEstadoList();
   }

   @GetMapping(path = "/{id}")
    public ResponseEntity<EstadoDto> getEstadoById(
            @PathVariable int id
   ){
       Optional<EstadoDto> estadoDto = estadoDtoService.getEstadoById(id);
       if (estadoDto.isPresent()){
           return ResponseEntity.ok(estadoDto.get());
       }
       return ResponseEntity.notFound().build();
   }

   @PostMapping(path = "/")
    public ResponseEntity<EstadoDto> createEstado(
            @RequestBody EstadoDto estadoDto
   ) throws ParseException, URISyntaxException {
       EstadoDto estadoDto1 = estadoDtoService.createEstado(estadoDto);
       URI location = new URI("/api/estados/" + estadoDto.getIdEstado());
       return ResponseEntity.created(location).body(estadoDto1);
   }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<EstadoDto> actualizacionParcialDto(
            @PathVariable("id") int idEstado,
            @RequestBody EstadoDto estadoDto
    ) throws ParseException {
        Optional<EstadoDto> estadoDb = estadoDtoService.getEstadoById(idEstado);
        if (estadoDb.isPresent()){
            EstadoDto modificable = estadoDb.get();
            if (estadoDto.getEstado()!= null) modificable.setEstado(estadoDto.getEstado());
            if (estadoDto.getAbreviatura()!= null) modificable.setAbreviatura(estadoDto.getAbreviatura());
            return ResponseEntity.ok(estadoDtoService.updateEstado(estadoDto));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EstadoDto> actualizarEstado(@PathVariable Integer id, @RequestBody EstadoDto estadoDto) throws ParseException, URISyntaxException {
       Optional<EstadoDto> estadoDb = estadoDtoService.getEstadoById(id);
       if (estadoDb.isEmpty()){
           URI location = new URI("/api/estados/" +  estadoDto.getIdEstado());
           return ResponseEntity.created(location).body(estadoDtoService.createEstado(estadoDto));
       }
       estadoDto.setIdEstado(id);
       URI location2 = new URI("/api/estados/" + estadoDto.getIdEstado());
       return ResponseEntity.ok(estadoDtoService.updateEstado(estadoDto));
    }

   @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteEstado(
            @PathVariable("id") int idEstado
   ){
       if (estadoDtoService.deletEstado(idEstado)){
           return ResponseEntity.ok().build();
       }else {
           return ResponseEntity.notFound().build();
       }
   }

    //  /api/estados/paginado?page=0&size=2&dir=asc&sort=estado
   @GetMapping(path="/paginado")
   public ResponseEntity<List<EstadoDto>> getPaginado(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "2") int size,
           @RequestParam(defaultValue = "asc") String dir,
           @RequestParam(defaultValue = "matricula") String sort
   ){
       return ResponseEntity.ok(estadoDtoService.getAllPageable(
               page,
               size,
               dir,
               sort
       ));
   }

}
