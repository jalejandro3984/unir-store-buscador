package com.unir.store_core.data;

import com.unir.store_core.model.db.Product;
import com.unir.store_core.model.response.AggregationDetails;
import com.unir.store_core.model.response.ProductsQueryResponse;
import com.unir.store_core.utils.Const;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.ParsedRange;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DataAccessRepository {

    // Esta clase (y bean) es la unica que usan directamente los servicios para
    // acceder a los datos.
    private final ProductRepository ProductRepository;
    private final ElasticsearchOperations elasticClient;

    //private final String[] Name_fields = {"ProductName", "ProductName._2gram", "ProductName._3gram"};

    public Product save(Product product) {
        return ProductRepository.save(product);
    }

    public Boolean delete(Product product) {
        ProductRepository.delete(product);
        return Boolean.TRUE;
    }


    public Optional<Product> findById(String id) {
        return ProductRepository.findById(id);
    }

    @SneakyThrows
    public List<Product> search(String keyword) {

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (keyword!=null){
            boolQuery
                    .should(QueryBuilders.wildcardQuery("name", "" + keyword.toLowerCase() + ""))
                    .should(QueryBuilders.wildcardQuery("description", "" + keyword.toLowerCase() + ""))
                    .should(QueryBuilders.wildcardQuery("categoryName", "" + keyword.toLowerCase() + ""));
        }
        else boolQuery.must(QueryBuilders.matchAllQuery());

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .build();

        SearchHits<Product> searchHits = elasticClient.search(searchQuery, Product.class);

        return searchHits.getSearchHits().stream().map(SearchHit::getContent).toList();
    }
}

