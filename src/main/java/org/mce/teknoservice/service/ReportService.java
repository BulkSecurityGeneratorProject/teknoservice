package org.mce.teknoservice.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import net.sf.jasperreports.engine.JRException;

import org.mce.teknoservice.domain.Contratto;
import org.mce.teknoservice.repository.ContrattoRepository;
import org.mce.teknoservice.web.util.JasperReportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
	
	public enum ReportResource{
		TEST_PDF("report/contratto.pdf"),
		DOC("report/doc.jasper"),
		CONTRATTO("report/contratto-1.jasper");
		
		private String resourceName;
		
		private ReportResource(String resourceName){
			this.resourceName = resourceName;
		}
		
		public String getResourceName(){
			return this.resourceName;
		}
	}
	
	@Inject ContrattoRepository contrattoRepository;

    private final Logger log = LoggerFactory.getLogger(ReportService.class);
    
    
    public byte[] createPdf(Long id) throws JRException{
		
		Contratto contratto = contrattoRepository.findById(id);
		
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		
		parameterMap.put("contratto", contratto);
		
		byte[] bytes = JasperReportUtil.createPdfReport(contratto.getConsistenzas(), parameterMap, ReportResource.CONTRATTO);
		
		return bytes;
	}
    
    public InputStream getPdf(){
    	return Thread.currentThread().getContextClassLoader().getResourceAsStream(ReportResource.TEST_PDF.getResourceName());
    }
}
