package br.com.contact.service;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.repository.ContactRepository;
import br.com.contact.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void createContact(ContactRequest request) {
        this.contactRepository.save(new Contact().convertToEntity(request));
    }

    @Override
    public void removeContact(Long id) {
        this.contactRepository.deleteById(id);
    }

    @Override
    public ContactResponse getContactByName(String name) {
        try {
            List<Contact> contacts = this.contactRepository.findAll();
            Contact c = contacts.stream().filter(contact -> contact.getName().equals(name)).findAny().get();
            return new Contact().convertToResponse(c);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<ContactResponse> getAllContact() {
        return this.contactRepository.findAll()
                .stream().map(contact -> contact.convertToResponse(contact))
                .collect(Collectors.toList());
    }

    @Override
    public void updateContact(Long id, ContactRequest request) {
        if (request == null || id == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        Contact contact = new Contact().convertToEntity(request);
        contact.setId(id);
        this.contactRepository.save(contact);
    }

    public ContactResponse findById(Long id) {
        return new Contact().convertToResponse(this.contactRepository.findById(id).orElse(null));
    }
}
