package com.achui.quick.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the word database table.
 * 
 */
@Entity
@Table(name="word")
public class Word  extends com.achui.quick.common.entity.BaseEntity<Integer> implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Column(name="explain_")
	private String explain;

	@Column(name="is_passed")
	private Integer isPassed;
	
	@Column(name="word")
	private String word;

	public Word() {
	}

	public String getExplain() {
		return this.explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Integer getIsPassed() {
		return this.isPassed;
	}

	public void setIsPassed(Integer isPassed) {
		this.isPassed = isPassed;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}