package com.provari.llamadas.infraestructure;

import com.provari.llamadas.domain.dto.DtoRangoSearch;
import com.provari.llamadas.domain.dto.DtoResumenPorExt;
import com.provari.llamadas.domain.dto.DtoSearch;
import com.provari.llamadas.domain.model.Llamada;
import com.provari.llamadas.domain.port.outbound.RetrieveLlamadas;
import com.provari.llamadas.domain.port.outbound.RetrieveResumenLlamadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class llamadasDAO implements RetrieveLlamadas, RetrieveResumenLlamadas {

    @Autowired
    private LlamadasRepository llamadasRepository;

    @Autowired
    private LlamadasRepositoryTemplate llamadasRepositoryTemplate;

    public List<Llamada> filtrarLlamadas(DtoSearch dtoSearch)
    {

        if(dtoSearch.getFechai()!=null && dtoSearch.getFechaf()!=null && (dtoSearch.getNum_extension()!=null && dtoSearch.getNum_extension()!="")) {
            return llamadasRepository.filtrar(dtoSearch.getFechai(), dtoSearch.getFechaf(), dtoSearch.getNum_extension());
        }
        else if(dtoSearch.getFechai()!=null && dtoSearch.getFechaf()!=null)
        {
            //Cómo pongo la paginacion aqui
            //return llamadasRepository.filtrarPorRangoDeFecha(dtoSearch.getFechai(), dtoSearch.getFechaf());
            return llamadasRepository.findByFechahoraBetween(dtoSearch.getFechai(), dtoSearch.getFechaf());
        }
        else if(dtoSearch.getNum_extension()!=null && dtoSearch.getNum_extension()!="")
        {
            return llamadasRepository.filtrarPorExtension(dtoSearch.getNum_extension());
        }
        else
        {
            return llamadasRepository.findAll();
        }

    }

    @Override
    public List<DtoResumenPorExt> getResumenPorExt(DtoRangoSearch dtoRangoSearch) {

        List<DtoResumenPorExt> listResumenPorExt= llamadasRepositoryTemplate.cantLLamadasPorExt();
        List<DtoResumenPorExt> listTiempoPorExt= llamadasRepositoryTemplate.TiempoTotalPorExt();
        //Completar esto concatenando las 2 listas

        Map<String,Integer> mapTiempo= new HashMap<>();
        listTiempoPorExt.stream().forEach(resumen->{
           mapTiempo.put(resumen.get_id(),resumen.getTiempototalenseg());
        });

        return listResumenPorExt
                .stream()
                .map(r->{
                    r.setTiempototalenseg(mapTiempo.get(r.get_id()));
                    return r;
                })
                .collect(Collectors.toList());



    }


    /*Ests métodos no implementan ningún puerto pero se necesita para cargar las llamadas*/
    public  Llamada getUltimaLlamada()
    {

        Sort sorter= Sort.by(Sort.Direction.DESC,"idllamada");
        List<Llamada> llamadasDesc= llamadasRepository.findAll(sorter);

        if(llamadasDesc.size()>0)
            return llamadasDesc.get(0);
        else
            return null;

    }

    public void adicionarLlamada(Llamada llamada) {
        llamadasRepository.save(llamada);
    }


}
