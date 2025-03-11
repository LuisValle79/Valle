package pe.edu.vallegrande.inmobiliaria.controller;

import pe.edu.vallegrande.inmobiliaria.model.Cliente;
import pe.edu.vallegrande.inmobiliaria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/asesores")
    public ResponseEntity<List<String>> obtenerAsesores() {
        return ResponseEntity.ok(clienteService.getAsesores());
    }

    @PostMapping
    public ResponseEntity<?> crearCliente(@Valid @RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = clienteService.guardarCliente(cliente);
            return ResponseEntity.ok(nuevoCliente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        return ResponseEntity.ok(clienteService.obtenerTodosLosClientes());
    }

    @GetMapping("/verificar/{dni}")
    public ResponseEntity<Boolean> verificarDniExistente(@PathVariable String dni) {
        return ResponseEntity.ok(clienteService.existeClientePorDni(dni));
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> obtenerClientePorDni(@PathVariable String dni) {
        Cliente cliente = clienteService.obtenerClientePorDni(dni);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
}
