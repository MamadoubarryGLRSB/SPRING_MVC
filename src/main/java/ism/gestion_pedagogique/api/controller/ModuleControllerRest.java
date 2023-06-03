package ism.gestion_pedagogique.api.controller;

import ism.gestion_pedagogique.api.dto.ModuleDTO;
import ism.gestion_pedagogique.entities.Module;
import ism.gestion_pedagogique.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/modules")
public class ModuleControllerRest {
    @Autowired
    private ModuleRepository moduleRepository;

    @GetMapping()
    public ResponseEntity<List<ModuleDTO>> getAllModules() {
        List<Module> modules = moduleRepository.findAll();
        List<ModuleDTO> moduleDTOs = new ArrayList<>();
        for (Module module : modules) {
            moduleDTOs.add(new ModuleDTO(module));
        }
        return ResponseEntity.ok(moduleDTOs);
    }

    @PostMapping("/ajouter-module")
    public ResponseEntity<ModuleDTO> ajoutModule(@RequestBody ModuleDTO moduleDTO) {
        Module module = new Module();
        module.setLibelle(moduleDTO.getLibelle());

        // Autres propriétés à assigner à l'objet Module

        moduleRepository.save(module);

        moduleDTO.setId(module.getId());
        return ResponseEntity.ok(moduleDTO);
    }

    @PostMapping("/enregistrer-module")
    public ResponseEntity<ModuleDTO> enregistrerModule(@RequestBody ModuleDTO moduleDTO) {
        Module module = new Module();
        module.setLibelle(moduleDTO.getLibelle());

        moduleRepository.save(module);

        moduleDTO.setId(module.getId());
        return ResponseEntity.ok(moduleDTO);
    }

    @PutMapping("/update-module/{id}")
    public ResponseEntity<ModuleDTO> updateModule(@PathVariable("id") Long id, @RequestBody ModuleDTO moduleDTO) {
        Optional<Module> optionalModule = moduleRepository.findById(id);
        if (optionalModule.isPresent()) {
            Module module = optionalModule.get();
            module.setLibelle(moduleDTO.getLibelle());

            moduleRepository.save(module);

            moduleDTO.setId(module.getId());
            return ResponseEntity.ok(moduleDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/module/{id}")
    public ResponseEntity<ModuleDTO> getModule(@PathVariable("id") Long id) {
        Optional<Module> optionalModule = moduleRepository.findById(id);
        if (optionalModule.isPresent()) {
            Module module = optionalModule.get();
            ModuleDTO moduleDTO = new ModuleDTO(module);
            return ResponseEntity.ok(moduleDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-module/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable("id") Long id) {
        Optional<Module> optionalModule = moduleRepository.findById(id);
        if (optionalModule.isPresent()) {
            moduleRepository.delete(optionalModule.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
