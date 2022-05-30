package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private static final PetModel petmodel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);

    @PostMapping(value = "/createPet", consumes = "application/json")
    public String createPet(@RequestBody Pet pet){
        petmodel.add(newId.getAndIncrement(), pet);//вывод сообщения!!!
        return "Pet added:"+pet.getName();
    }

    @GetMapping(value ="/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll(){
        return petmodel.getAll();
    }

    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id){
        return petmodel.getFromList(id.get("id"));
    }
    @DeleteMapping(value = "/deletePet", consumes = "application/json",produces = "application/json" )
    public void deletePet(@RequestBody Map<String, Integer> id){
        petmodel.delete(id.get("id"));
    }
    @PutMapping(value="/putPet", consumes = "application/json",produces = "application/json" )
    public Map<Integer, Pet> putPet(@RequestBody int id,  Pet pet){
        petmodel.put(id, pet);
        return petmodel.getAll();
    }
}
