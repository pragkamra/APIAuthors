package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.persistance.PublicationRepository;
import com.test.model.*;

import java.net.URI;
import java.util.*;

/**
 * @author PRAG KAMRA
 * 
 * This controller class is basically used to handle CRUD for puplications
 * 
 * --CRUD for publications
 * Search publications by author and any arbitrary attribute
 * Add new author with existing publication type
 * 
 * --TO DO
 * Exception Handling
 * --> More Searching criteria
 *
 */
@RestController
public class PublicationsController {
	   @Autowired
	    private PublicationRepository repository;
	 
	    public PublicationRepository getRepository() {
	        return repository;
	    }
	 
	    public void setRepository(PublicationRepository repository) {
	        this.repository = repository;
	    }
	 
	    @GetMapping("/publications/{type}/{attribute}")
	  //Example: find all comics with Spider-man
		public List<Publications> retrieveAuthorAllPublications(@PathVariable String publicationType,
				@PathVariable String publicationattribute) {
	    	List<Publications> pubList = getAllPublications();
	    	List<Publications> fetchList = new ArrayList<Publications>();
	    	for (Publications pub: pubList) {
	    		if (pub.getPublicationType().equalsIgnoreCase(publicationType) &&
	    				pub.getOther().equalsIgnoreCase(publicationattribute)) {
	    			fetchList.add(pub);	
	    		}
	    	}	    	
			return fetchList;
		}
	    
	    @PostMapping("/publications/{type}")
	  //Example: Add a new Novel by Stephen King
		public ResponseEntity<Void> addAuthorWithPublication(@PathVariable String publicationType,
				@RequestBody Authors newauthor) {
	    	List<Publications> pubList = getAllPublications();
	    	for (Publications pub: pubList) {
	    		if (pub.getPublicationType().equalsIgnoreCase(publicationType)) {
	    			pub.getAuthors().add(newauthor);
	    		}
	    	}	    	
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
					"/{id}").buildAndExpand(newauthor.getName()).toUri();

			return ResponseEntity.created(location).build();
		}
	    
	    
	    
	    
	    
	    @GetMapping(value = "/publications")
	    public List<Publications> getAllPublications() {
	        return repository.findAll();
	    }
	 
	    @PostMapping("/publications")
	    Publications createOrSaveEmployee(@RequestBody Publications newPublication) {
	        return repository.save(newPublication);
	    }
	 
	    @GetMapping("/publications/{id}")
	    Publications getPublicationById(@PathVariable Long id) {
	        return repository.findOne(id);
	    }
	 
	    @PutMapping("/updatepublications/{id}")
	
	  public void updatePublication(@RequestBody Publications
	  newPublication, @PathVariable Long id) {
	    	repository.save(newPublication);
	 }
	 
	    @DeleteMapping("/publications/{id}")
	    void deletePublications(@PathVariable Long id) {
	        repository.delete(id);
	    }

}
