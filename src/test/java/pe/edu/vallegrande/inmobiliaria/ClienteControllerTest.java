package pe.edu.vallegrande.inmobiliaria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.edu.vallegrande.inmobiliaria.model.Cliente;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCrearCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombres("Juan");
        cliente.setApellidos("Pérez");
        cliente.setDni("12345678");
        cliente.setCorreo("juan@example.com");
        cliente.setDireccion("Av. Example 123");
        cliente.setCelular("987654321");
        cliente.setAsesor("Asesor 1");
        cliente.setEstado("Activo");

        ResponseEntity<Cliente> response = restTemplate.postForEntity(
            "http://localhost:" + port + "/api/clientes",
            cliente,
            Cliente.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().getNombres());
        assertNotNull(response.getBody().getFecha());
    }

    @Test
    public void testDniDuplicado() {
        Cliente cliente1 = new Cliente();
        cliente1.setNombres("Juan");
        cliente1.setApellidos("Pérez");
        cliente1.setDni("87654321");
        cliente1.setCorreo("juan@example.com");
        cliente1.setCelular("987654321");
        cliente1.setAsesor("Asesor 1");

        // Primer intento de crear cliente
        restTemplate.postForEntity(
            "http://localhost:" + port + "/api/clientes",
            cliente1,
            Cliente.class
        );

        // Segundo intento con el mismo DNI
        Cliente cliente2 = new Cliente();
        cliente2.setNombres("Pedro");
        cliente2.setApellidos("García");
        cliente2.setDni("87654321"); // Mismo DNI
        cliente2.setCorreo("pedro@example.com");
        cliente2.setCelular("987654322");
        cliente2.setAsesor("Asesor 2");

        ResponseEntity<String> response = restTemplate.postForEntity(
            "http://localhost:" + port + "/api/clientes",
            cliente2,
            String.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Ya existe un cliente registrado con el DNI"));
    }
}
