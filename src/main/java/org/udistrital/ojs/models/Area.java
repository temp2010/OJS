package org.udistrital.ojs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "AreasProfesionales")
public class Area {

	private Integer id;
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
	public Integer getId() {
		return id;
	}
	
	@Column(name = "Area", nullable = false, length = 50)
	public String getArea() {
		return area;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setArea(String area) {
		this.area = area;
	}

}
