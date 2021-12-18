package br.com.contact.service;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.repository.ContactRepository;
import br.com.contact.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

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
    public Contact getContactByName(String name) {
        List<Contact> contacts = this.contactRepository.findAll();
        Contact c = contacts.stream().filter(contact -> contact.getName().equals(name)).findAny().get();
        return c;
    }

    @Override
    public Contact getByName(String name) {
        return this.contactRepository.findByName(name);
    }


    @Override
    public List<ContactResponse> getAllContact() {
        return this.contactRepository.findAll()
                .stream().map(contact -> contact.convertToResponse(contact))
                .collect(Collectors.toList());
    }

    @Override
    public void updateContact(ContactRequest request) {
//        this.contactRepository.;
    }
}
