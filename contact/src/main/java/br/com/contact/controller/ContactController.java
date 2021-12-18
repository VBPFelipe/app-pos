package br.com.contact.controller;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.model.Contact;
import br.com.contact.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContactResponse> get() {
        return this.contactService.getAllContact();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(@RequestBody ContactRequest request) {
        this.contactService.createContact(request);
    }

    @DeleteMapping
    public void deleteContact(@RequestParam Long id) {
        this.contactService.removeContact(id);
    }

    @GetMapping("/find-by-name")
    public Contact getContactByName(@RequestParam String name) {
        return this.contactService.getContactByName(name);
    }

    @PutMapping
    private void updateContact(@RequestBody ContactRequest request) {
        this.contactService.updateContact(request);
    }

}