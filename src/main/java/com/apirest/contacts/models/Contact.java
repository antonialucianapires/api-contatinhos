package com.apirest.contacts.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="TB_CONTACT")	
public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotBlank
		@Size(max = 50)
		private String name;
		
		@NotBlank
		@Email(message = "O e-mail deve ser válido.")
		private String email;
		
		@NotBlank(message = "O telefone está inválido.")
		@Size(max = 11)
		private String phone;


}
