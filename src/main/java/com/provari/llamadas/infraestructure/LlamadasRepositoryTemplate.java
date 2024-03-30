package com.provari.llamadas.infraestructure;

import com.provari.llamadas.domain.dto.DtoResumenPorExt;
import com.provari.llamadas.domain.dto.DtoSearch;
import com.provari.llamadas.domain.model.Llamada;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LlamadasRepositoryTemplate {


    private MongoTemplate mongoTemplate;

    public LlamadasRepositoryTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public List<Llamada> filtrarLlamadas(DtoSearch dtoSearch)
    {
        Query query= new Query();

        if(!dtoSearch.getNum_extension().isEmpty()) {
            query.addCriteria(Criteria.where("extension._id").is(dtoSearch.getNum_extension()));
        }

        if(!dtoSearch.getFechai().equals(null) && !dtoSearch.getFechaf().equals(null))
        {
            query.addCriteria(Criteria.where("fechahora")
                    .gte(dtoSearch.getFechai()).
                            and("fechahora").lte(dtoSearch.getFechaf()));
        }
        return mongoTemplate.find(query, Llamada.class);

    }

    public List<Llamada> listarTodas()
    {
        return mongoTemplate.findAll(Llamada.class);

    }

    public List<Llamada> filtrarPorExtension(String num_extension)
    {
         Query query= new Query();
         query.with( Sort.by(Sort.Direction.ASC,"duracion")  );
         query.addCriteria(Criteria.where("extension._id").is(num_extension));


         List<Llamada> lLlamadas= mongoTemplate.find(query,Llamada.class);

        return lLlamadas;
    }

    /* Resumen */

    public List<DtoResumenPorExt> cantLLamadasPorExt()
    {
      //Group
        GroupOperation groupByExt= Aggregation.group("extension._id").count().as("cant_llamadas");
      //Match Operation
      //Sort Operation
        SortOperation sortOperation= Aggregation.sort(Sort.by(Sort.Direction.DESC,"cant_llamadas"));
      //Aggregation
        Aggregation aggregation=Aggregation.newAggregation(groupByExt, sortOperation);
        AggregationResults output= mongoTemplate.aggregate(aggregation,"llamadas",DtoResumenPorExt.class);

        //Esto es lo que me está faltando, que salga el num de extension
        return output.getMappedResults();
    }

    public List<DtoResumenPorExt> TiempoTotalPorExt()
    {
        GroupOperation groupByExt= Aggregation.group("extension._id").sum("duracion").as("tiempototalenseg");

        SortOperation sortOperation= Aggregation.sort(Sort.by(Sort.Direction.DESC,"tiempototalenseg"));

        Aggregation aggregation=Aggregation.newAggregation(groupByExt, sortOperation);
        AggregationResults output= mongoTemplate.aggregate(aggregation,"llamadas",DtoResumenPorExt.class);

        return output.getMappedResults();
    }




    public List<Llamada> llamadasPorNumMarcado()
    {
        Query query= new Query();
        query.addCriteria(Criteria.where("num_marcado").regex("59989144"));
        List<Llamada> llamadas= mongoTemplate.find(query,Llamada.class);

        return llamadas;
    }


}
