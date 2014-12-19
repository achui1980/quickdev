package com.achui.quick.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonRootName;


/**
 * The persistent class for the word database table.
 * 
 */
@Entity
@Table(name="word")
@JsonRootName("word")
public class Word  extends com.achui.quick.common.entity.BaseEntity<Integer> implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Column(name="explain")
	private String explain;

	@Column(name="is_passed")
	private byte isPassed;
	
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

	public byte getIsPassed() {
		return this.isPassed;
	}

	public void setIsPassed(byte isPassed) {
		this.isPassed = isPassed;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}