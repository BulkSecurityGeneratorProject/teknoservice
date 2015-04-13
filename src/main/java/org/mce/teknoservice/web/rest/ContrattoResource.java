package org.mce.teknoservice.web.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import net.sf.jasperreports.engine.JRException;

import org.mce.teknoservice.domain.Attivita;
import org.mce.teknoservice.domain.Consistenza;
import org.mce.teknoservice.domain.Contratto;
import org.mce.teknoservice.domain.Impianto;
import org.mce.teknoservice.domain.Intervento;
import org.mce.teknoservice.domain.Typeattivita;
import org.mce.teknoservice.domain.Typeconsistenza;
import org.mce.teknoservice.domain.Typeimpianto;
import org.mce.teknoservice.domain.Typeintervento;
import org.mce.teknoservice.repository.AttivitaRepository;
import org.mce.teknoservice.repository.ConsistenzaRepository;
import org.mce.teknoservice.repository.ContrattoRepository;
import org.mce.teknoservice.repository.ImpiantoRepository;
import org.mce.teknoservice.repository.InterventoRepository;
import org.mce.teknoservice.repository.TypeAttivitaRepository;
import org.mce.teknoservice.repository.TypeConsistenzaRepository;
import org.mce.teknoservice.repository.TypeImpiantoRepository;
import org.mce.teknoservice.repository.TypeInterventoRepository;
import org.mce.teknoservice.service.ContrattoService;
import org.mce.teknoservice.service.MailService;
import org.mce.teknoservice.service.ReportService;
import org.mce.teknoservice.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing Contratto.
 */
@RestController
@RequestMapping("/api")
public class ContrattoResource {

    private final Logger log = LoggerFactory.getLogger(ContrattoResource.class);

    @Inject
    private ContrattoRepository contrattoRepository;
    
    @Inject
    private ConsistenzaRepository consistenzaRepository;
    
    @Inject
    private ImpiantoRepository impiantoRepository;

    @Inject
    private InterventoRepository interventoRepository;
    
    @Inject
    private AttivitaRepository attivitaRepository;
    
    @Inject
    private ContrattoService contrattoService;
    
    @Inject
    private ReportService reportService;
    
    @Inject
    private MailService mailService;
    
    @Inject
    private TypeConsistenzaRepository typeConsistenzaRepository;
    
    @Inject
	private TypeImpiantoRepository typeImpiantoRepository;

	@Inject
	private TypeInterventoRepository typeInterventoRepository;

	@Inject
	private TypeAttivitaRepository typeAttivitaRepository;

    /**
     * POST  /contrattos -> Create a new contratto.
     */
    @RequestMapping(value = "/contrattos",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Contratto contratto) throws URISyntaxException {
        log.debug("REST request to save Contratto : {}", contratto);
        if (contratto.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new contratto cannot already have an ID").build();
        }
        contrattoRepository.save(contratto);
        return ResponseEntity.created(new URI("/api/contrattos/" + contratto.getId())).build();
    }

    /**
     * PUT  /contrattos -> Updates an existing contratto.
     */
    @RequestMapping(value = "/contrattos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Contratto contratto) throws URISyntaxException {
        log.debug("REST request to update Contratto : {}", contratto);
        if (contratto.getId() == null) {
            return create(contratto);
        }
        contrattoRepository.save(contratto);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /contrattos -> get all the contrattos.
     */
    @RequestMapping(value = "/contrattos",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Contratto>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Contratto> page = contrattoRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/contrattos", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /contrattos/:id -> get the "id" contratto.
     */
    @RequestMapping(value = "/contrattos/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Contratto> get(@PathVariable Long id) {
        log.debug("REST request to get Contratto : {}", id);
        Contratto contratto = contrattoRepository.findById(id);

        //Consistenza consistenza = contrattoService.addConsistenza(1);
        //contrattoDef.getConsistenzas().add(consistenza);
        
        return Optional.ofNullable(contratto)
            .map(contrattoz -> new ResponseEntity<>(
                contrattoz,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @RequestMapping(value = "/contrattos/{id}/pdf",
            method = RequestMethod.GET)
           // produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Timed
    public ResponseEntity<ByteArrayResource> downloadPdf(@PathVariable Long id) throws JRException, IOException{
    //public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) throws JRException{

        //byte[] bytes = reportService.createPdf(id);
    	byte[] bytes = null;
    
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.parseMediaType("application/pdf"));
    	//headers.setContentLength(bytes.length);
    	
    	headers.setContentDispositionFormData("attachment", "contratto.pdf");
    	headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        
        //InputStreamResource isr = new InputStreamResource(new ByteArrayInputStream(bytes));
    	
    	ByteArrayResource res = new ByteArrayResource(reportService.createPdf(id));
    	headers.setContentLength(res.contentLength());
        return new ResponseEntity<ByteArrayResource>(res, headers, HttpStatus.OK);
        //return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    	/* WORKS!!! ClassPathResource res = new ClassPathResource("report/contratto.pdf");
    	headers.setContentLength(res.contentLength());
    	return new ResponseEntity<ClassPathResource>(res, headers, HttpStatus.OK);
    	*/
    }
    
    @RequestMapping(value = "/consistenza/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Consistenza> getConsistenza(@PathVariable Integer id) {
        log.debug("REST request to get Consistenza : {}", id);

        Consistenza consistenza = contrattoService.createConsistenza(id);
        
        return Optional.ofNullable(consistenza)
            .map(consistenzaz -> new ResponseEntity<>(
            		consistenzaz,  HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @RequestMapping(value = "/consistenza/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void deleteConsistenza(@PathVariable Integer id) {
        log.debug("REST request to delete Consistenza: {}", id);
        consistenzaRepository.delete(id);
    }
    
    @RequestMapping(value = "/impianto/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Impianto> getImpianto(@PathVariable Integer id) {
        log.debug("REST request to get Impianto : {}", id);

        Impianto impianto = contrattoService.createImpianto(id);
        
        return Optional.ofNullable(impianto)
            .map(impiantoz -> new ResponseEntity<>(
            		impiantoz,  HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @RequestMapping(value = "/impianto/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void deleteImpianto(@PathVariable Integer id) {
        log.debug("REST request to delete impianot: {}", id);
        impiantoRepository.delete(id);
    }
    
    
    @RequestMapping(value = "/intervento/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Intervento> getIntervento(@PathVariable Integer id) {
        log.debug("REST request to get Intervento : {}", id);

        Intervento intervento = contrattoService.createIntervento(id);
        
        return Optional.ofNullable(intervento)
            .map(interventoz -> new ResponseEntity<>(
            		interventoz,  HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @RequestMapping(value = "/intervento/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void deleteIntervento(@PathVariable Integer id) {
        log.debug("REST request to delete intervento: {}", id);
        interventoRepository.delete(id);
    }
    
    
    @RequestMapping(value = "/attivita/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Attivita> getAttivita(@PathVariable Integer id) {
        log.debug("REST request to get Attivita : {}", id);

        Attivita attivita = contrattoService.createAttivita(id);
        
        return Optional.ofNullable(attivita)
            .map(attivitaz -> new ResponseEntity<>(
            		attivitaz,  HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @RequestMapping(value = "/attivita/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void deleteAttivita(@PathVariable Integer id) {
        log.debug("REST request to delete attivita: {}", id);
        attivitaRepository.delete(id);
    }
    
    @RequestMapping(value = "/typeconsistenza",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Typeconsistenza>> getAll(){
    	List<Typeconsistenza> result = typeConsistenzaRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/typeimpianto/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Typeimpianto>> getTypeimpiantos(@PathVariable("id") Integer parentId){
    	Typeconsistenza parent = new Typeconsistenza();
    	
    	parent.setTypeConsistenzaId(parentId);
    	
    	List<Typeimpianto> result = typeImpiantoRepository.findByTypeconsistenza(parent);
    	
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/typeintervento/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Typeintervento>> getTypeinterventos(@PathVariable("id") Integer parentId){
    	Typeimpianto parent = new Typeimpianto();
    	
    	parent.setTypeImpiantoId(parentId);
    	
    	List<Typeintervento> result = typeInterventoRepository.findByTypeimpianto(parent);
    	
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/typeattivita/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Typeattivita>> getTypeattivitas(@PathVariable("id") Integer parentId){
    	Typeintervento parent = new Typeintervento();
    	
    	parent.setTypeInterventoId(parentId);
    	
    	List<Typeattivita> result = typeAttivitaRepository.findByTypeintervento(parent);
    	
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * DELETE  /contrattos/:id -> delete the "id" contratto.
     */
    @RequestMapping(value = "/contrattos/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Contratto : {}", id);
        contrattoRepository.delete(id);
    }
    
    
    @RequestMapping(value = "/contrattos/{id}/pdf/send",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void sendPdf(@PathVariable Long id) throws Exception{
        log.debug("REST request to send Contratto : {}", id);
        
        Contratto contratto = contrattoRepository.findById(id);
        
        byte[] pdfBytes = reportService.createPdf(id);
        mailService.sendContrattoEmail(contratto.getCliente(), pdfBytes);
        //return ResponseEntity.ok().build();
    }
    
}
