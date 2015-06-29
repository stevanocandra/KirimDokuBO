package com.kirimdoku.bo.ejb.query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stevano
 */
@Stateless
public class QueryFactoryBean implements QueryFactoryLocal {
    @PersistenceContext(unitName = "KIRIMDOKUBO")
    private EntityManager em;
    
    private Session getSession(){
        return (Session) em.getDelegate();
    }
    
    public <T> T hqlSelect(Class<T> castClass, String query, Map<String, Object> data){
        return hqlSelect(castClass, query, data, Boolean.FALSE);
    }
    
    public <T> T hqlSelect(Class<T> castClass, String query, Map<String, Object> data, Boolean transformClass) {
        try {
            org.hibernate.Query hql = getSession().createQuery(query);
            if (transformClass) {
                hql.setResultTransformer(Transformers.aliasToBean(castClass));
            }
            for (Map.Entry<String, Object> e : data.entrySet()) {
                if (e.getValue() instanceof Object[]) {
                    System.out.print("params [%s] is object array [%s] : " + e.getKey() + " : " + Arrays.toString((Object[]) e.getValue()));
                    hql.setParameterList(e.getKey(), (Object[]) e.getValue());
                } else if (e.getValue() instanceof Collection) {
                    System.out.print("params [%s] is object collection [%s] : " + e.getKey() + " : " + e.getValue());
                    hql.setParameterList(e.getKey(), (Collection) e.getValue());
                } else {
                    hql.setParameter(e.getKey(), e.getValue());
                }
            }
            return (T) hql.uniqueResult();
        } catch (NoResultException e) {
            System.out.print("no entity found for query");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("HQL [%s] : " + query.toString());
        return null;
    }
    
    public <T> List<T> hqlSelectList(Class<T> castClass, String query, Map<String, Object> data) {
        return hqlSelectList(castClass, query, data, null, null, null);
    }

    public <T> List<T> hqlSelectList(Class<T> castClass, String query, Map<String, Object> data, Boolean transformClass) {
        return hqlSelectList(castClass, query, data, null, null, transformClass);
    }

    public <T> List<T> hqlSelectList(Class<T> castClass, String query, Map<String, Object> data, Integer firstResult, Integer maxResult) {
        return hqlSelectList(castClass, query, data, firstResult, maxResult, null);
    }

    public <T> List<T> hqlSelectList(Class<T> castClass, String query, Map<String, Object> data, Integer firstResult, Integer maxResult, Boolean transformClass) {
        try {
            org.hibernate.Query hql = getSession().createQuery(query);
            if (transformClass != null && transformClass) {
                hql.setResultTransformer(Transformers.aliasToBean(castClass));
            }
            if (data != null) {
                for (Map.Entry<String, Object> e : data.entrySet()) {
                    hql.setParameter(e.getKey(), e.getValue());
                    System.out.print("Params [%s], value [%s] : " + e.getKey() + " : " + e.getValue());
                }
            }
            if (firstResult != null) {
                hql.setFirstResult(firstResult);
            }
            if (maxResult != null && maxResult > 0) {
                hql.setMaxResults(maxResult);
            }

            List<T> result = (ArrayList<T>) hql.list();
            if (result != null) {
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("HQL [%s] : " + query.toString());
        return (new ArrayList<T>());
    }
    
    public <T> List<T> sqlSelectList(Class<T> castClass, String query, String params, Integer firstResult, Integer maxResult, Boolean transformClass) {
        try {

            if (params != null) {
                query = query + " " + params;
            }

            if (firstResult != null) {
                query = query + " OFFSET " + firstResult;
            }

            if (maxResult != null) {
                query = query + " LIMIT " + maxResult;
            }

            org.hibernate.SQLQuery sql = getSession().createSQLQuery(query);

            if (transformClass != null && transformClass) {
                sql.setResultTransformer(Transformers.aliasToBean(castClass));
            }

            List<T> result = (ArrayList<T>) sql.list();
            if (result != null) {
                System.out.print("SQL [%s] : " + query.toString());
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        Echo.log("SQL [%s]", query.toString());
        return (new ArrayList<T>());
    }
}
