package com.test.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tbl_author")
public class Authors implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUTHOR_ID", unique = true, nullable = false)
	private Long author_id;
	  
	/**
	 * @return the author_id
	 */
	public Long getAuthor_id() {
		return author_id;
	}

	/**
	 * @param author_id the author_id to set
	 */
	public void setAuthor_id(Long author_id) {
		this.author_id = author_id;
	}



	@Column(name = "AUTHOR_NAME")
	private String name;
	
	public Authors() {}
	
	public Authors (String name) {
		this.name = name;
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
    public void addPublication(Publications publication) {
        this.publications.add(publication);
        publication.getAuthors().add(this);
    }
 
    public void removePublication(Publications publication) {
        this.publications.remove(publication);
        publication.getAuthors().remove(this);
    }
	
	
	
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_authorspublications",
            joinColumns = { @JoinColumn(name = "author_id") },
            inverseJoinColumns = { @JoinColumn(name = "publication_id") })
    private List<Publications> publications = new ArrayList<>();

	/**
	 * @return the publications
	 */
	public List<Publications> getPublications() {
		return publications;
	}

	/**
	 * @param publications the publications to set
	 */
	public void setPublications(List<Publications> publications) {
		this.publications = publications;
	}
	
	

}

