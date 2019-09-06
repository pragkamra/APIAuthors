package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.test.model.*;
import java.util.*;
import com.test.persistance.AuthorRespository;
import com.test.persistance.PublicationRepository;

@Service
public class AuthorsService {

	@Autowired
	private AuthorRespository authorRespository;
	
	private static List<Authors> auths= new ArrayList<>();
	
    public AuthorRespository getRepository() {
        return authorRespository;
    }
 
    public void setRepository(AuthorRespository repository) {
        this.authorRespository= repository;
    }
	

	public List<Authors> retrieveAllAuthors() {		
		return authorRespository.findAll();
	}

	public Authors retrieveAuthor(String authorName) {
		Authors auth =  authorRespository.findByName(authorName);
		return auth;
	}

	public List<Publications> retrievePublications(String authorName) {
		Authors author = retrieveAuthor(authorName);

		if (author == null) {
			return null;
		}

		return author.getPublications();
	}

	public Publications retrievePublication(String authorName, String publicationType) {
		Authors author = retrieveAuthor(authorName);
		List<Publications> allPub = author.getPublications();
		for(Publications pub:allPub) {
			if (pub.getPublicationType().equalsIgnoreCase(publicationType))
				return pub;
		}
	
		return null;
	}

	public void addPublication(String authorName, Publications newPublication) {
		Authors author = authorRespository.findByName(authorName);
		author.getPublications().add(newPublication);
		author.addPublication(newPublication);
	}

	public void removePublication(String authorName, Publications newPublication) {
		Authors author = authorRespository.findByName(authorName);
		author.removePublication(newPublication);
	}
		
	public Authors save(Authors author) {
		return authorRespository.save(author);
	}

	public Authors findByName(String name) {
		Authors auth =  authorRespository.findByName(name);
		return auth;
	}

	public void deleteByName(String name) {
		Authors author = authorRespository.findByName(name);
		authorRespository.delete(author);
		
	}

	public List<Publications> retrievePubs(String authorName, String publicationYear, String publicationType) {
		List<Publications> updatedPubs = new ArrayList<Publications>();
		Authors author = authorRespository.findByName(authorName);
		for (Publications pub:author.getPublications()) {
			if (pub.getYear().equalsIgnoreCase(publicationYear) &&
					pub.getPublicationType().equalsIgnoreCase(publicationType))
				updatedPubs.add(pub);
				
		} 
		return updatedPubs;
	}
	
	public AuthorsService() {
		//auths = authorRespository.findAll();
	}
}
	


