package pe.edu.vallegrande.inmobiliaria.repository;

import pe.edu.vallegrande.inmobiliaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByDni(String dni);
    Cliente findByDni(String dni);
}
