package com.ubots.relationshipCenter.resources;

import com.ubots.relationshipCenter.entities.Called;
import com.ubots.relationshipCenter.services.CalledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/requests")
public class CalledResource {

    @Autowired
    private CalledService service;

    @GetMapping
    public ResponseEntity<List<Called>> findAll(){
        List<Called> list = this.service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/called/{id}")
    public ResponseEntity<Called> findById(@PathVariable Long id){
        Called obj = this.service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Called> insert(@RequestBody Called obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/called/{id}")
    public ResponseEntity<Called> update(@PathVariable Long id, @RequestBody Called obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
