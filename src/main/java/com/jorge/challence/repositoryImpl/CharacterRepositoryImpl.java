package com.jorge.challence.repositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.jorge.challence.domain.Character;
import com.jorge.challence.domain.Movie;
import com.jorge.challence.repository.CharacterRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = false)
public class CharacterRepositoryImpl implements CharacterRepositoryCustom {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Character> findByParamsOutMovieId(HashMap<String, Object> params) {
    try {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Character> cq = cb.createQuery(Character.class);
      Root<Character> r_character = cq.from(Character.class);

      Predicate[] predicates = new Predicate[params.size() + 1];
      predicates[0] = cb.isNull(r_character.get("deletedAt"));
      int i = 1;
      
      for (Map.Entry<String, Object> entry : params.entrySet()) {
        if (entry.getValue().getClass().getSimpleName().equals("String")){
          predicates[i] = cb.like(r_character.get(entry.getKey()), "%" + entry.getValue() + "%");
        } else if(entry.getValue().getClass().getSimpleName().equals("Float") )
          predicates[i] = cb.between(r_character.get(entry.getKey()), (Float)entry.getValue() - 0.1, (Float) entry.getValue() + 0.1);
        else
          predicates[i] = cb.equal(r_character.get(entry.getKey()), entry.getValue().toString());
        ++i;
      }

      cq.select(r_character).where(predicates);
      return em.createQuery(cq).getResultList();
    } catch (Exception e) {
      e.getStackTrace();
    } finally {
      em.close();
    }
    return null;
  }

  @Override
  public List<Character> findByParamsWithMovieId(HashMap<String, Object> params) {
    try {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Character> cq = cb.createQuery(Character.class);
      Root<Character> r_character = cq.from(Character.class);
      Join<Character, Movie> cm = r_character.join("movies");
      List<Predicate> predicates = new ArrayList<>();

      for(Map.Entry<String, Object> entry: params.entrySet()){
        if(entry.getValue().getClass().getSimpleName().equals("String"))
          predicates.add(cb.like(r_character.get(entry.getKey()), "%" + entry.getValue() + "%"));
        if(entry.getKey().equals("movie")){
          predicates.add(cb.isNull(cm.get("deletedAt")));
          predicates.add(cb.equal(cm.get("id"), entry.getValue()));
        }
        if(entry.getValue().getClass().getSimpleName().equals("Float"))
          predicates.add(cb.between(r_character.get(entry.getKey()), (float)entry.getValue() - 0.01, (float)entry.getValue() + 0.01));
        if(entry.getValue().getClass().getSimpleName().equals("Integer"))
          predicates.add(cb.equal(r_character.get(entry.getKey()), entry.getValue() ));
      }
      predicates.add(cb.isNull(r_character.get("deletedAt")));

       return em.createQuery(cq.select(r_character).where(predicates.toArray(new Predicate[] {}))).getResultList();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();

    } finally {
      em.close();
    }
    return new ArrayList<Character>();
  }

}
