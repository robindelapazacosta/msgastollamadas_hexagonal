package com.provari.llamadas.infraestructure;

import com.provari.llamadas.domain.model.Llamada;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface LlamadasRepository extends MongoRepository<Llamada, String> {


   @Query("{$and: [{fechahora:{$gte:?0,$lte:?1}},{\"extension._id\":'?2'}]}")
   public List<Llamada> filtrar(Date fechahora1, Date fechahora2, String num_extension);

   @Query("{\"extension._id\":'?0'}")
   List<Llamada> filtrarPorExtension(String num_extension);

   public List<Llamada> findByFechahoraBetween(Date fechai, Date fechaf);


}
