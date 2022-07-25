package com.booking.yoya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booking.yoya.model.common.SharedEntity;

import lombok.Data;

@Entity
@Table(name = "content")
@Data
//@Where(clause = "is_active = true")
public class Content extends SharedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "content_id")
	private Long contentId;

	@Column(name = "content_title")
	private String contentTitle;

	@Column(name = "content_description")
	private String contentDescription;

	@Column(name = "content_image")
	private String contentImage;

}
