package pe.edu.vallegrande.inmobiliaria.service;

import pe.edu.vallegrande.inmobiliaria.model.Cliente;
import pe.edu.vallegrande.inmobiliaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private final List<String> ASESORES = Arrays.asList(
        "Asesor 1",
        "Asesor 2",
        "Asesor 3",
        "Asesor 4",
        "Asesor 5",
        "Asesor 6",
        "Asesor 7",
        "Asesor 8"
    );

    public List<String> getAsesores() {
        return ASESORES;
    }

    public Cliente guardarCliente(Cliente cliente) {
        if (clienteRepository.existsByDni(cliente.getDni())) {
            throw new RuntimeException("Ya existe un cliente registrado con el DNI: " + cliente.getDni());
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    public boolean existeClientePorDni(String dni) {
        return clienteRepository.existsByDni(dni);
    }
}
