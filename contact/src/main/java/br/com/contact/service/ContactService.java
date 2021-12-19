package br.com.contact.service;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.model.Contact;

import java.util.List;

public interface ContactService {

    public void createContact(ContactRequest request);
    public void removeContact(Long id);
    public ContactResponse getContactByName(String name);
//    public ContactResponse getByName(String name);
    public List<ContactResponse> getAllContact();
    public ContactResponse findById(Long id);
    public void updateContact(Long id, ContactRequest request);


}
