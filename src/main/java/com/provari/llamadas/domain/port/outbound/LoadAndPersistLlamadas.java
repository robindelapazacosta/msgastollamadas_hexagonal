package com.provari.llamadas.domain.port.outbound;

import com.provari.llamadas.domain.model.Llamada;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface LoadAndPersistLlamadas {


    public List<Llamada> CargarLlamadas() throws IOException, ParseException;

}
