package com.provari.llamadas.domain.port.inbound;

import com.provari.llamadas.domain.model.Llamada;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface CargarLlamadas {

    public List<Llamada> cargarLlamadas() throws IOException, ParseException;
}
