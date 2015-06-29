/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirimdoku.bo.ejb.query;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
/**
 *
 * @author stevano
 */
@Local
public interface QueryFactoryLocal {
    public <T> T hqlSelect(Class<T> castClass, String query, Map<String, Object> data);

    public <T> T hqlSelect(Class<T> castClass, String query, Map<String, Object> data, Boolean transformClass);

    public <T> List<T> hqlSelectList(Class<T> castClass, String query, Map<String, Object> data);

    public <T> List<T> hqlSelectList(Class<T> castClass, String query, Map<String, Object> data, Boolean transformClass);

    public <T> List<T> hqlSelectList(Class<T> castClass, String query, Map<String, Object> data, Integer firstResult, Integer maxResult);

    public <T> List<T> hqlSelectList(Class<T> castClass, String query, Map<String, Object> data, Integer firstResult, Integer maxResult, Boolean transformClass);
    
    public <T> List<T> sqlSelectList(Class<T> castClass,String query, String params, Integer firstResult, Integer maxResult, Boolean transformClass);
}
