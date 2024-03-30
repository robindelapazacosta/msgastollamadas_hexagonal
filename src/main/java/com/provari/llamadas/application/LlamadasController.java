package com.provari.llamadas.application;

import com.provari.llamadas.domain.dto.DtoRangoSearch;
import com.provari.llamadas.domain.dto.DtoResumenPorExt;
import com.provari.llamadas.domain.dto.DtoSearch;
import com.provari.llamadas.domain.model.Llamada;
import com.provari.llamadas.domain.service.CargarLlamadasService;
import com.provari.llamadas.domain.service.LlamadasQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("llamadas/")
public class LlamadasController {

    @Autowired
    private LlamadasQueryService llamadasQueryService;

    @Autowired
    private CargarLlamadasService cargarLlamadasService;


    @PostMapping("filtrar")
    public ResponseEntity<List<Llamada>> filtrarLlamadas(@RequestBody DtoSearch dtoSearch)
    {

         return ResponseEntity.status(HttpStatus.OK).body(llamadasQueryService.filtrarLlamadas(dtoSearch));
    }


    @PostMapping("resumen")
    public ResponseEntity<List<DtoResumenPorExt>> resumenPorExtesion(@RequestBody DtoRangoSearch dtoRangoSearch)
    {
        return ResponseEntity.status(HttpStatus.OK).body(llamadasQueryService.resumenPorExtension(dtoRangoSearch));
    }


    @GetMapping("cargar")
    public ResponseEntity<String> cargarLlamadas() throws IOException, ParseException {

        this.cargarLlamadasService.cargarLlamadas();
        return ResponseEntity.status(HttpStatus.OK).body("Se cargaron correctamente");

    }



}
