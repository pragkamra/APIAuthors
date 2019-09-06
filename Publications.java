package com.test.model;


import java.io.Serializable;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tbl_publications")
public class Publications implements Serializable{	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PUBLICATION_ID", unique = true, nullable = false)
	private Long publication_id;


	@Column(name ="PUBLICATION_TYPE")
	public String publicationType;
	@Column(name ="PUBLICATION_TITLE")
	public String title;
	@Column(name ="OTHER")
	public String other;
	@Column(name ="PUBLICATION_YEAR")
	public String year;
	
	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}


	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	
	/**
	 * @return the publication_id
	 */
	public Long getPublication_id() {
		return publication_id;
	}

	/**
	 * @param publication_id the publication_id to set
	 */
	public void setPublication_id(Long publication_id) {
		this.publication_id = publication_id;
	}
	
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	
	/**
	 * @return the publicationType
	 */
	public String getPublicationType() {
		return publicationType;
	}

	/**
	 * @param publicationType the publicationType to set
	 */
	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType;
	}

	public Publications() {
		
	}
	
	
	
	
    @ManyToMany(fetch = FetchType.EAGER,
    		cascade = CascadeType.ALL,
            mappedBy = "publications")
    private Set<Authors> authors = new HashSet<>();

	/**
	 * @return the authors
	 */
	public Set<Authors> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(Set<Authors> authors) {
		this.authors = authors;
	}
	
    public void addAuthor(Authors author) {
        this.authors.add(author);
        author.getPublications().add(this);
    }
 
    public void removeAuthor(Authors author) {
        this.authors.remove(author);
        author.getPublications().remove(this);
    }
	

}
