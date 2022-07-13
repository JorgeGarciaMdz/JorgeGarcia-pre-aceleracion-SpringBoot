package com.jorge.challence.repositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.jorge.challence.domain.Movie;
import com.jorge.challence.repository.MovieRepositoryCustom;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepositoryImpl implements MovieRepositoryCustom {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Movie> findByParams(HashMap<String, Object> params) {
    try {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
      Root<Movie> r_movie = cq.from(Movie.class);

      List<Predicate> predicates = new ArrayList<>();
      predicates.add(cb.isNull(r_movie.get("deletedAt")));
      Order o = null;

      for (Map.Entry<String, Object> entry : params.entrySet()) {
        if (entry.getValue().getClass().getSimpleName().equals("String") && !entry.getKey().equals("order"))
          predicates.add(cb.like(r_movie.get(entry.getKey()), "%" + entry.getValue() + "%"));
        if (!entry.getValue().getClass().getSimpleName().equals("String") && !entry.getKey().equals("order"))
          predicates.add(cb.equal(r_movie.get(entry.getKey()), entry.getValue()));
        if (entry.getKey().equals("order")) {
          if (entry.getValue().equals("DESC"))
            o = cb.desc(r_movie.get("title"));
          else
            o = cb.asc(r_movie.get("title"));
        }
      }

      Predicate[] predicates2 = new Predicate[predicates.size()];
      predicates.toArray(predicates2);
      
      cq.select(r_movie).where(predicates2);
      cq.orderBy(o);
      return em.createQuery(cq).getResultList();
    } catch (Exception e) {
      System.out.println("--------------------- error -------------");
      e.printStackTrace();
    } finally {
      em.close();
    }
    return null;
  }

}
