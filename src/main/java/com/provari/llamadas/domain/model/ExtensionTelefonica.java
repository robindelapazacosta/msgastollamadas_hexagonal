package com.provari.llamadas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("extensiones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtensionTelefonica {
    @Id
    String num_extension;

    String descripcion;

    public ExtensionTelefonica(String num_extension) {
        this.num_extension = num_extension;
    }
}
