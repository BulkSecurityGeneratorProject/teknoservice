/*
 * Created on 14-gen-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.mce.teknoservice.web.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import org.mce.teknoservice.service.ReportService.ReportResource;

/**
 * @author mceccarini
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JasperReportUtil {

	public static byte[] createPdfReport(Collection beanCollection, Map<String,Object> parameterMap, ReportResource reportResource ) throws JRException { 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		/* URL fileUrl = this.getClass().getResource(urlReporte+".jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(fileUrl.openStream());
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		// for moe pages
		JasperPrint jp2 = JasperFillManager.fillReport(template2,new HashMap(),jrDS2);
		List pageList = jp2.getPages();
		ListIterator it = pageList.listIterator();
			
		while(it.hasNext()){
			JRPrintPage page = (JRPrintPage)it.next();
			jp1.addPage(page);
		}
		*/

		JRBeanCollectionDataSource jrds = new JRBeanCollectionDataSource(beanCollection);
		
		InputStream templateIs = getTemplateInputStream(reportResource);

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(templateIs);

		jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

		JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameterMap, jrds);

		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jp);

		exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, baos);

		exporter.exportReport();

		//log.info(" generato PDF...: " + nomeFilePdf);

		//JasperViewer.viewReport(jp);

		return baos.toByteArray();
	}
	
	
	public static InputStream getTemplateInputStream(ReportResource reportResource){
		
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(reportResource.getResourceName());
		
	}

}
