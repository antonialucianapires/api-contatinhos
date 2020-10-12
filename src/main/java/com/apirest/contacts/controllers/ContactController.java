package com.apirest.contacts.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.apirest.contacts.ContactNotFoundException;
import com.apirest.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apirest.contacts.models.Contact;
import com.apirest.contacts.repository.ContactRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="Contatinhos API REST")
@CrossOrigin(origins="*")
public class ContactController {

		@Autowired
		private ContactService service;
		
		@GetMapping("/contacts")
		@ApiOperation(value="Listar todos os contatos")
		public ResponseEntity<List<Contact>> findAll() {
			return ResponseEntity.ok().body(service.getAll());
		}
		
		@GetMapping("/contacts/{id}")
		@ApiOperation(value="Obter um contato específico pelo ID")
		public ResponseEntity<Contact> findById(@PathVariable long id) {
			Optional<Contact> contact = service.getById(id);
			if(contact.isPresent()) {
				return  ResponseEntity.ok().body(contact.get());
			} else {
				return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
			}
		}
		
		@PostMapping("/contacts")
		@ApiOperation(value="Criar um novo contato")
		public Contact create(@Valid @RequestBody Contact contact) {
			return service.save(contact);
		}
		
		@PutMapping("/contacts/{id}")
		@ApiOperation(value="Atualizar detalhes de um contato")
		public ResponseEntity<Contact> update(@PathVariable("id") long id,
										@RequestBody Contact contact) {
		Contact update = service.update(id, contact);
						return ResponseEntity.ok().body(update);
			
		}
		
		@DeleteMapping("/contacts/{id}")
		@ApiOperation(value="Remover um contato pelo ID")
		public ResponseEntity<?> delete(@PathVariable long id) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}
}
