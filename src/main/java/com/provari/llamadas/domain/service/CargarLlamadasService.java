package com.provari.llamadas.domain.service;

import com.provari.llamadas.domain.model.Llamada;
import com.provari.llamadas.domain.port.inbound.CargarLlamadas;
import com.provari.llamadas.domain.port.outbound.LoadAndPersistLlamadas;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class CargarLlamadasService implements CargarLlamadas {

    private LoadAndPersistLlamadas loadPersistLlamadas;

    public CargarLlamadasService(LoadAndPersistLlamadas loadPersistLlamadas) {
        this.loadPersistLlamadas = loadPersistLlamadas;
    }

    @Override
    public List<Llamada> cargarLlamadas() throws IOException, ParseException {
        return loadPersistLlamadas.CargarLlamadas();
    }
}
