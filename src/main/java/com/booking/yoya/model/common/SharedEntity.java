package com.booking.yoya.model.common;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.TypeDef;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.Data;

@Data
@MappedSuperclass
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public abstract class SharedEntity {

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "updated_by", nullable = false)
	private String updatedBy;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive;

	@PrePersist
	protected void onCreate() {
//        this.uniqueId = UUID.randomUUID().toString();
		this.createdAt = new Date();
		this.updatedAt = new Date();
//        this.createdBy = UserUtil.getCurrentUserLogin();
		this.createdBy = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
//        this.updatedBy = UserUtil.getCurrentUserLogin();
		this.updatedBy = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
		if (Objects.isNull(isActive)) {
			this.isActive = true;
		}
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
//        this.updatedBy = UserUtil.getCurrentUserLogin();
		this.updatedBy = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
		if (Objects.isNull(isActive)) {
			this.isActive = true;
		}
	}
}