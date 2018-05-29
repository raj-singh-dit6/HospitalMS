package com.hospitalms.configuration;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.hospitalms.security.CORSFilter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
	private static final String LOCATION = "/home/rajendra/Downloads/temp";
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 20; //20MB
    private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 30;//30MB
    private static final int FILE_SIZE_THRESHOLD = 0;
    
	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
 
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
        registration.setInitParameter("throwExceptionIfNoHandlerFound","true");
    }
    
    /**
     * Configures MultiPart with initial values.
     * @return
     */
    private MultipartConfigElement getMultipartConfigElement(){
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }
    
    /**
     * Adds filter for CROSS Origin filter.
     */
    @Override
    protected Filter[] getServletFilters() {
	        Filter [] filters = {new CORSFilter()};
	        return filters;
    }
    
     
}
