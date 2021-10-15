package es.ubu.lsi.database;

import java.util.Date;

public class Comment {
	// attributes
	private long id;
	private String name;
	private String surname;
	private String comments;
	private Date date;

	// getters & setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	// constructors
	public Comment(String name, String surname, String comments) {
		super();
		this.setName(name);
		this.setSurname(surname);
		this.setComments(comments);
		this.setDate(new Date());
	}
	public Comment(long id, String name, String surname, String comments, Date date) {
		super();
		this.setId(id);
		this.setName(name);
		this.setSurname(surname);
		this.setComments(comments);
		this.setDate(date);
	}
	
	public Comment() {
		
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", surname="
				+ surname + ", comments=" + comments + ", date=" + date
				+ "]";
	}
}
