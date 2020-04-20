package com.apirest.contacts.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.contacts.models.Contact;
import com.apirest.contacts.repository.ContactRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/contacts")
@Api(value="Contatinhos API REST")
@CrossOrigin(origins="*")
public class ContactController {

		private ContactRepository repository;
	
		ContactController(ContactRepository contactRepository) {
			this.repository = contactRepository;
		}
		
		@GetMapping
		@ApiOperation(value="Listar todos os contatos")
		public List<Contact> findAll() {
			return repository.findAll();
		}
		
		@GetMapping("/{id}")
		@ApiOperation(value="Obter um contato específico pelo ID")
		public ResponseEntity<Contact> findById(@PathVariable long id) {
			return repository.findById(id)
					.map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		}
		
		@PostMapping("/contacts/new")
		@ApiOperation(value="Criar um novo contato")
		public Contact create(@RequestBody Contact contact) {
			return repository.save(contact);
		}
		
		@PutMapping("/{id}")
		@ApiOperation(value="Atualizar detalhes de um contato")
		public ResponseEntity<Contact> update(@PathVariable("id") long id,
										@RequestBody Contact contact) {
			
			return repository.findById(id)
					.map(record -> {
						record.setName(contact.getName());
						record.setEmail(contact.getEmail());
						record.setPhone(contact.getPhone());
						Contact update = repository.save(record);
						return ResponseEntity.ok().body(update);
					}).orElse(ResponseEntity.notFound().build());
			
		}
		
		@DeleteMapping("/{id}")
		@ApiOperation(value="Remover um contato pelo ID")
		public ResponseEntity<?> delete(@PathVariable long id) {
			return repository.findById(id)
					.map(record -> {
						repository.deleteById(id);
						return ResponseEntity.ok().build();
					}).orElse(ResponseEntity.notFound().build());
		}
}
