package org.mce.teknoservice.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import org.mce.teknoservice.domain.Cliente;
import org.mce.teknoservice.repository.ClienteRepository;
import org.mce.teknoservice.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing Cliente.
 */
@RestController
@RequestMapping("/api")
public class ClienteResource {

    private final Logger log = LoggerFactory.getLogger(ClienteResource.class);

    @Inject
    private ClienteRepository clienteRepository;

    /**
     * POST  /clientes -> Create a new cliente.
     */
    @RequestMapping(value = "/clientes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@Valid @RequestBody Cliente cliente) throws URISyntaxException {
        log.debug("REST request to save Cliente : {}", cliente);
        if (cliente.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new cliente cannot already have an ID").build();
        }
        clienteRepository.save(cliente);
        return ResponseEntity.created(new URI("/api/clientes/" + cliente.getId())).build();
    }

    /**
     * PUT  /clientes -> Updates an existing cliente.
     */
    @RequestMapping(value = "/clientes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@Valid @RequestBody Cliente cliente) throws URISyntaxException {
        log.debug("REST request to update Cliente : {}", cliente);
        if (cliente.getId() == null) {
            return create(cliente);
        }
        clienteRepository.save(cliente);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /clientes -> get all the clientes.
     */
    @RequestMapping(value = "/clientes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Cliente>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Cliente> page = clienteRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/clientes", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/clientes/clienti",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Cliente>> findByCognome(@RequestParam(value = "cognome" , required = true) String cognome ) throws URISyntaxException {
        List<Cliente> list = clienteRepository.findByCognomeStartingWith(cognome);
        return new ResponseEntity<List<Cliente>>(list, HttpStatus.OK);
    }

    /**
     * GET  /clientes/:id -> get the "id" cliente.
     */
    @RequestMapping(value = "/clientes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Cliente> get(@PathVariable Long id) {
        log.debug("REST request to get Cliente : {}", id);
        Cliente c1 = clienteRepository.findOne(id);
        Cliente c2 = clienteRepository.findById(id);
        return Optional.ofNullable(c1)
            .map(cliente -> new ResponseEntity<>(
                cliente,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /clientes/:id -> delete the "id" cliente.
     */
    @RequestMapping(value = "/clientes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Cliente : {}", id);
        clienteRepository.delete(id);
    }
    /*
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody() Exception handleException(Exception e) {
        return e;
    }*/
}
