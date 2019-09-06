package com.test.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.test.service.AuthorsService;
import com.test.exception.RecordNotFoundException;
import com.test.model.*;
import com.test.persistance.AuthorRespository;



/**
 * @author PRAG KAMRA
 * 
 * Authors Controller class basically use to handle all Authors CRUD operations
 * as well as it used to fetch all the publications of specific authors
 * 
 * -->CRUD for Authors
 * -->Add a new publication with Author
 * -->Search specific publication of author either by name or year
 * -->Search publications by year and author
 * 
 * --TO DO
 * Search by any attribute rater than year only
 *
 */
@RestController
public class AuthorsController {

	@Autowired
	AuthorsService authorService;
		
	@PostMapping("/addAuthor")
	public ResponseEntity  addAuthor(@RequestBody Authors author) {
		return ResponseEntity.ok(authorService.save(author));
	}
	
    @DeleteMapping("/deleteAuthor/{authorName}")
    public ResponseEntity deleteAuthor(@PathVariable String name) {
        authorService.deleteByName(name);
        return ResponseEntity.ok().build();
    }
	
	@GetMapping("/searchAuthor/{authorName}")
	public Authors searchAuthorByName(@PathVariable("authorName") String authorName) {
		Authors author = authorService.findByName(authorName);
		if(author!=null) 
			return author;
		else
			throw new RecordNotFoundException("Author '" +authorName + "' does no exist");
	}

	// update existing Auhtor
	@PutMapping("/updateAuthor")
	public ResponseEntity<Object> updateAuthor(@RequestBody Authors author) {
		return ResponseEntity.ok(authorService.save(author));
	}
    
	
	//Search publications by year and author and type(book,comics or magazine)
	//Example: find all books written by Stephen King in 1990 (authorName = Stephen King, type=book,year=1990)	
	@GetMapping("/searchPublication/{autorName}/{year}/{type}")
	public List<Publications> retrievePublication(@PathVariable("authorName") String authorName,
			@PathVariable("year") String publicationYear, @PathVariable("pubType") String publicationType) {
		List<Publications> pubList = authorService.retrievePubs(authorName,publicationYear,publicationType);
		if (pubList==null)
			throw new RecordNotFoundException("Author '" +authorName + "' with required publication does no exist");
		
		return pubList;
	} 	
	
	//Example: find all novels by Stephen King
	@GetMapping("/authors/{authorName}/publications")
	public List<Publications> retrieveAuthorAllPublications(@PathVariable String authorName) {
		List<Publications> auth =  authorService.retrievePublications(authorName);
		if (auth!=null)
			return auth;
		else
			throw new RecordNotFoundException("Author '" +authorName + "' with required publication does no exist");
	}
	
	@GetMapping("/authors/{authorName}/publications/publicationType")
	public Publications retrieveSpecificPublicationByAuthor(@PathVariable String authorName,
			@PathVariable String publicationType) {
		Publications pub= authorService.retrievePublication(authorName, publicationType);
		if (pub!=null)
			return pub;
		else
			throw new RecordNotFoundException("Author '" +authorName + "' with required publication does no exist");
	}

	@PostMapping("/authors/{authorName}/publications")
	public ResponseEntity<Void> addNewAuthorPublication(
			@PathVariable String authorName, @RequestBody Publications newPublication) {
		
			authorService.addPublication(authorName, newPublication);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(newPublication.getPublication_id()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	
}
