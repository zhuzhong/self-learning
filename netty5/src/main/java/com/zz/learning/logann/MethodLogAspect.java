package com.zz.learning.logann;
import java.lang.reflect.Field;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
  
/** 
 *  
 * @author arthur.paincupid.lee 
 * @since 2016.04.17 
 */  
@Aspect  
public class MethodLogAspect {  
    private static final org.apache.commons.logging.Log logger = LogFactory  
            .getLog(MethodLogAspect.class);  
  
    private static String[] types = { "java.lang.Integer", "java.lang.Double",  
            "java.lang.Float", "java.lang.Long", "java.lang.Short",  
            "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",  
            "java.lang.String", "int", "double", "long", "short", "byte",  
            "boolean", "char", "float" };  
  

    @After(value = "@annotation(c.z.log.MethodLog)")
    public void afterInvoke(JoinPoint joinPoint){
    	
    }
    @Before(value = "@annotation(c.z.log.MethodLog)")
    public void beforeInvoke(JoinPoint joinPoint)  
            throws Throwable {  
    	
        String classType = joinPoint.getTarget().getClass().getName();  
        Class<?> clazz = Class.forName(classType);  
        String clazzName = clazz.getName();  
       // String clazzSimpleName = clazz.getSimpleName();  
        String methodName = joinPoint.getSignature().getName();  
          
        String[] paramNames = getFieldsName(this.getClass(), clazzName, methodName);  
          
        String logContent = writeLogInfo(paramNames, joinPoint);  
          
        //org.apache.commons.logging.Log logger = LogFactory.getLog(clazzName);  
        logger.info("方法调用前置日志 clazzName: "+clazzName+", methodName:"+methodName+", param:"+ logContent);  
          
    } 
    
    private  String writeLogInfo(String[] paramNames, JoinPoint joinPoint){  
        Object[] args = joinPoint.getArgs();  
        StringBuilder sb = new StringBuilder();  
        boolean clazzFlag = true;  
        for(int k=0; k<args.length; k++){  
            Object arg = args[k];  
            sb.append(paramNames[k]+" ");  
            // 获取对象类型  
           // String typeName = arg.getClass().getTypeName();  
            String typeName = arg.getClass().getName(); 
            for (String t : types) {  
                if (t.equals(typeName)) {  
                    sb.append("=" + arg+"; ");  
                }  
            }  
            if (clazzFlag) {  
                sb.append(getFieldsValue(arg));  
            }  
        }  
        return sb.toString();  
    }  
    
    /** 
     * 得到方法参数的名称 
     * @param cls 
     * @param clazzName 
     * @param methodName 
     * @return 
     * @throws NotFoundException 
     */  
    private static String[] getFieldsName(Class cls, String clazzName, String methodName) throws NotFoundException{  
        ClassPool pool = ClassPool.getDefault();   
        ClassClassPath classPath = new ClassClassPath(cls);  
        pool.insertClassPath(classPath);  
          
        CtClass cc = pool.get(clazzName);  
        CtMethod cm = cc.getDeclaredMethod(methodName);  
        MethodInfo methodInfo = cm.getMethodInfo();  
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();  
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);  
        if (attr == null) {  
            // exception  
        }  
        String[] paramNames = new String[cm.getParameterTypes().length];  
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;  
        for (int i = 0; i < paramNames.length; i++){  
            paramNames[i] = attr.variableName(i + pos); //paramNames即参数名  
        }  
        return paramNames;  
    }  
    
  /*  
    private void getParamterName(String clazzName, String methodName)  
            throws NotFoundException {  
        ClassPool pool = ClassPool.getDefault();  
        ClassClassPath classPath = new ClassClassPath(this.getClass());  
        pool.insertClassPath(classPath);  
  
        CtClass cc = pool.get(clazzName);  
        CtMethod cm = cc.getDeclaredMethod(methodName);  
        MethodInfo methodInfo = cm.getMethodInfo();  
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();  
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute  
                .getAttribute(LocalVariableAttribute.tag);  
        if (attr == null) {  
            // exception  
        }  
        String[] paramNames = new String[cm.getParameterTypes().length];  
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;  
        for (int i = 0; i < paramNames.length; i++) { 
            paramNames[i] = attr.variableName(i + pos); 
        }
        // paramNames即参数名  
        for (int i = 0; i < paramNames.length; i++) {  
            System.out.println(paramNames[i]);  
        }  
    }  */
    /** 
     * 得到参数的值 
     * @param obj 
     */  
    private  String getFieldsValue(Object obj) {  
        Field[] fields = obj.getClass().getDeclaredFields();  
       // String typeName = obj.getClass().getTypeName();  
        String typeName = obj.getClass().getName();
        for (String t : types) {  
            if(t.equals(typeName))  
                return "";  
        }  
        StringBuilder sb = new StringBuilder();  
        sb.append("【");  
        for (Field f : fields) {  
            f.setAccessible(true);  
            try {  
                for (String str : types) {  
                    if (f.getType().getName().equals(str)){  
                        sb.append(f.getName() + " = " + f.get(obj)+"; ");  
                    }  
                }  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                e.printStackTrace();  
            }  
        }  
        sb.append("】");  
        return sb.toString();  
    }  
      
  
  
}  
