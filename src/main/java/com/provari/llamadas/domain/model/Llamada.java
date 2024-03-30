package com.provari.llamadas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;

@Document("llamadas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Llamada {

    @Id
    public String idllamada;


    private Date fechahora; //formato "yyyy-MM-dd HH:mm"
    private ExtensionTelefonica extension;
    private String linea;
    private String num_marcado;
    @Field("duracion")
    private Integer duracion_ensegundos;

    public Llamada(Date fechahora, ExtensionTelefonica extension, String linea, String num_marcado, Integer duracion_ensegundos) {
        this.fechahora = fechahora;
        this.extension = extension;
        this.linea = linea;
        this.num_marcado = num_marcado;
        this.duracion_ensegundos = duracion_ensegundos;
    }

    //Se calcula costo de la llamada





}
