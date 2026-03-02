package com.factory.auditable;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

	@CreatedBy
    @Column(name="created_by", updatable = false)
    private Long createdBy;
	
	@CreatedDate
	@Column(name="created_date", updatable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedBy
	@Column(name="updated_by")
	private Long updatedBy;
	
	@LastModifiedDate
	@Column(name="updated_date")
	private LocalDateTime updatedDate;
	
	@Column(name="deleted_by")
	private Long deletedBy;
	
	@Column(name="deleted_date")
	private LocalDateTime deletedDate;
	
	
}
