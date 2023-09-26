package com.kyle.singlestonecodingchallenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ContactsController {

    private final ContactRepository repository;

    ContactsController(ContactRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/contacts")
    List<Contact> getAllContacts() {
        return repository.findAll();
    }

    @PostMapping("/contacts")
    Contact createContact(@RequestBody String jsonData) throws JsonProcessingException {
        Contact emp = deserialize(jsonData);
        return repository.save(emp);
    }

    @GetMapping("/contacts/{id}")
    Optional<Contact> getContact(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping("/contacts/{id}")
    Contact updateContact(@RequestBody String jsonData, @PathVariable Long id) throws JsonProcessingException{
        Contact newContact = deserialize(jsonData);

        return repository.findById(id) //
                .map(contact -> {
                    contact.setFirstName(newContact.getFirstName());
                    contact.setMiddleName(newContact.getMiddleName());
                    contact.setLastName(newContact.getLastName());
                    contact.setStreet(newContact.getStreet());
                    contact.setCity(newContact.getCity());
                    contact.setState(newContact.getState());
                    contact.setZip(newContact.getZip());
                    contact.setPhone(newContact.getPhone());
                    contact.setPhoneType(newContact.getPhoneType());
                    contact.setEmail(newContact.getEmail());
                    return repository.save(contact);
                })
                .orElseGet(() -> {
                    newContact.setId(id);
                    return repository.save(newContact);
                });
    }

    @DeleteMapping("/contacts/{id}")
    void deleteContact(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/call-list")
    ResponseEntity<Object> getCallList() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Contact> contacts = repository.findAllByPhoneType("home");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(contacts.size());
        System.out.println(contacts.get(0).getFirstName());
        List<ObjectNode> callList = new ArrayList<ObjectNode>();
        for (int i = 0; i < contacts.size(); i++) {
            ObjectNode nameObjectNode = objectMapper.createObjectNode();
            nameObjectNode.put("first", contacts.get(i).getFirstName());
            nameObjectNode.put("middle", contacts.get(i).getMiddleName());
            nameObjectNode.put("last", contacts.get(i).getLastName());

            ObjectNode parentObjectNode = objectMapper.createObjectNode();
            parentObjectNode.put("name", nameObjectNode);
            parentObjectNode.put("phone", contacts.get(i).getPhone());

            callList.add(parentObjectNode);
        }

//		String jsonStr = JSONArray.toJSONString(callList);
//		return jsonStr;
        return new ResponseEntity<>(callList, HttpStatus.OK);
    }

    private Contact deserialize(String jsonData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonData);
        String firstName = jsonNode.get("name").get("first").asText();
        String middleName = jsonNode.get("name").get("middle").asText();
        String lastName = jsonNode.get("name").get("last").asText();
        String street = jsonNode.get("address").get("street").asText();
        String city = jsonNode.get("address").get("city").asText();
        String state = jsonNode.get("address").get("state").asText();
        String zip = jsonNode.get("address").get("zip").asText();
        String phone = jsonNode.get("phone").get("number").asText();
        String phoneType = jsonNode.get("phone").get("type").asText();
        String email = jsonNode.get("email").asText();

        return new Contact(firstName, middleName, lastName, street, city, state, zip, phone, phoneType, email);
    }
}