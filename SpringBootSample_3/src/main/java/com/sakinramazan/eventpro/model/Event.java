package com.sakinramazan.eventpro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Entity ; ORM(Object Relation Mapping) yapısındaki java tarafında databasedeki tablolara 
// karşılık gelen herbir class için kullanılır.
@Entity
// Tablo adının doğru yazılması önemlidir fakat case-sensitive
// değildir.(Büyük-küçük harf dikkat etmeye gerek yoktur)
@Table(name = "EVENTS")
// JPA Auditing i aktive etmiştik, burada da bu entity için audit atacağını
// söylüyoruz.
@EntityListeners(AuditingEntityListener.class)
public class Event {
	// Primary Key
	@Id
	// Tablodaki kolon adı ile uyuşmalıdır
	@Column(name = "ID")
	// Unique olacağını ifade eder.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "CONTENT")
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
