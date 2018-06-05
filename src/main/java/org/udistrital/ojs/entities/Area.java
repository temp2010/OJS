package org.udistrital.ojs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "AreasProfesionales")
public class Area {

	private Integer idArea;
	private String area;

	public Area() {
	}

	public Area(String area) {
		this.area = area;
	}

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(generator = "native")
	@Column(name = "idArea", unique = true, nullable = false)
	public Integer getIdArea() {
		return idArea;
	}
	
	@Column(name = "area", nullable = false, length = 50)
	public String getArea() {
		return area;
	}
	
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}
	
	public void setArea(String area) {
		this.area = area;
	}

}
