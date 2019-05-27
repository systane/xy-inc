package br.com.zup.api.repository;

import br.com.zup.api.entity.POI;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface IPoiRepository extends MongoRepository<POI, String> {
}
