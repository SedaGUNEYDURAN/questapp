package com.project.questapp.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@EntityScan
@Table(name="comment")
@Data
public class Comment {

	@Id
	Long id;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="post_id",nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
	Post post;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
	User user;
	
	@Lob
	@Column(columnDefinition="text")
	String text;
}
