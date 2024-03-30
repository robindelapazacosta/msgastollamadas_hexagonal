package com.provari.llamadas.infraestructure;

import com.provari.llamadas.domain.model.ExtensionTelefonica;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExtensionesRepository extends MongoRepository<ExtensionTelefonica,String> {
}
