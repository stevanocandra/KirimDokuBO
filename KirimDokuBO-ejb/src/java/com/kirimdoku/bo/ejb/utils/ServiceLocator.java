/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirimdoku.bo.ejb.utils;
import javax.naming.InitialContext;
/**
 *
 * @author stevano
 */
public class ServiceLocator {
    public static final String PACKAGE_NAME = "KIRIMDOKUBO/";
    
    public static <T> T lookupLocal(Class<T> localClass){
        return lookupLocal(localClass, PACKAGE_NAME);
    }
    
    public static <T> T lookupLocal(Class<T> localClass, String packageName){
        try{
            InitialContext ic = new InitialContext();
            String name = "";
            if(localClass.getSimpleName().endsWith("BeanLocal")){
                name = localClass.getSimpleName().replace("Local", "");
            }
            else{
                name = localClass.getSimpleName().replace("Local", "Bean");
            }
            return (T) ic.lookup(packageName + name + "/local");
        }
        catch(Throwable th){
            th.printStackTrace();
            return null;
        }
    }
}
