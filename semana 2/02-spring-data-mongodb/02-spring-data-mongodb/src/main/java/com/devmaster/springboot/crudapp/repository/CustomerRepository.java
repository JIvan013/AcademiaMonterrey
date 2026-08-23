package com.devmaster.springboot.crudapp.repository;

import com.devmaster.springboot.crudapp.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Aquí estaban CustomerDAO y CustomerDAOJpaImpl — unas 70 líneas de código.
 *
 * MongoRepository&lt;Customer, String&gt; ya trae implementados findAll(),
 * findById(), save() y deleteById(). Spring Data genera la implementación en
 * tiempo de arranque; no hay ninguna clase que escribir.
 *
 * Los dos parámetros son el tipo de la entidad y el tipo de su @Id.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    // Sin cuerpo. Si más adelante necesitas una consulta propia, basta con
    // declarar el método siguiendo la convención de nombres, por ejemplo:
    //
    //     List<Customer> findByLastName(String lastName);
    //
    // Spring Data la implementa sola a partir del nombre.
}
