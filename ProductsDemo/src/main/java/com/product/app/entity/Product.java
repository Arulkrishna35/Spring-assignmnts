package com.product.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import io.swagger.annotations.ApiModelProperty;
@Entity
public class Product {
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
		@SequenceGenerator(sequenceName = "product_seq", allocationSize = 1, name = "product_seq")

		@Column(name = "product_ID", unique = true, nullable = false)
		private Integer id;

		@ApiModelProperty(notes = "Name of the product",name="name",required=true,value="test Java")
		
		@Column(name = "NAME", unique = true, nullable = false, length = 100)
		private String name;
		@Column(name = "PRICE", unique = false, nullable = false)
		private float price;
		private String description;

		public Product() {
			super();
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Product(int id, String name, float price,String description) {
			super();
			this.id = id;
			this.name = name;
			this.price = price;
			this.description=description;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}



}
