package org.mce.teknoservice.config;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hibernate4.HibernateAnnotationIntrospector;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.fasterxml.jackson.datatype.joda.ser.JacksonJodaFormat;

@Configuration
public class JacksonConfiguration {

    @Bean
    public JodaModule jacksonJodaModule() {
        JodaModule module = new JodaModule();
        DateTimeFormatterFactory formatterFactory = new DateTimeFormatterFactory();
        formatterFactory.setIso(DateTimeFormat.ISO.DATE);
        module.addSerializer(DateTime.class, new DateTimeSerializer(
                new JacksonJodaFormat(formatterFactory.createDateTimeFormatter()
                        .withZoneUTC())));
        return module;
    }
    /*
    @Bean
    public HibernateAnnotationIntrospector hibernateAnnotationIntrospector(){
    	HibernateAnnotationIntrospector hibernateAnnotationIntrospector = new HibernateAnnotationIntrospector();
    	hibernateAnnotationIntrospector.setUseTransient(true);// ignora transietn eand process the property
    	return null; 
    }*/ 
    
    @Bean
    public ObjectMapper objectMapper(){ 
            return new ObjectMapper(){{
                Hibernate4Module module = new Hibernate4Module();
                module.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
                registerModule(module);
            }};
    }        
}