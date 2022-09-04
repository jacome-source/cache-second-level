package com.jacome.cachesl.cache.repository;

import com.jacome.cachesl.cache.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Cacheable;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>  {

    @QueryHints({
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    })
    @Query("select p from pessoa p where nome like :nome")
    List<Pessoa> findByNomeIncompleto(@Param("nome") String nome);

//    @QueryHints({
//            @QueryHint(name = "org.hibernate.cacheable", value = "true")
//    })
    // CACHE DESABILITADO
    List<Pessoa> findByNome(String nome);
}
