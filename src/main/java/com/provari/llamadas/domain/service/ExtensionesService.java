package com.provari.llamadas.domain.service;

import com.provari.llamadas.domain.model.ExtensionTelefonica;
import com.provari.llamadas.infraestructure.ExtensionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExtensionesService {

    @Autowired
    ExtensionesRepository extRepository;

    public void adicionarExtension(ExtensionTelefonica ext)
    {
        extRepository.save(ext);
    }

    public void adicionarExtensiones(List<ExtensionTelefonica> lext)
    {
        extRepository.saveAll(lext);
    }

    public List<ExtensionTelefonica> listarExtensiones()
    {
        Sort sorter= Sort.by("num_extension");
        return extRepository.findAll(sorter);
    }

    public Map<String, ExtensionTelefonica> mapExtensiones()
    {
        Map<String, ExtensionTelefonica> mapExt= new HashMap<>();
        List<ExtensionTelefonica> lextensiones= this.listarExtensiones();

        for(ExtensionTelefonica ext: lextensiones)
        {
            mapExt.put(ext.getNum_extension(),ext);
        }

        return mapExt;
    }

}
