package com.ubots.relationshipCenter.resources;

import com.ubots.relationshipCenter.entities.Attendant;
import com.ubots.relationshipCenter.services.AttendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/attendants")
public class AttendantResource {

    @Autowired
    private AttendantService service;

    @GetMapping
    public ResponseEntity<List<Attendant>> findAll(){
        List<Attendant> list = this.service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Attendant> findById(@PathVariable Long id){
        Attendant obj = this.service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Attendant> insert(@RequestBody Attendant obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
