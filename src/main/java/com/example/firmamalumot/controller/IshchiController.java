package com.example.firmamalumot.controller;

import com.example.firmamalumot.payload.ApiRespons;
import com.example.firmamalumot.payload.IshchiDto;
import com.example.firmamalumot.service.IshchiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ishchilar")
public class IshchiController {
    @Autowired
    IshchiService ishchiService;

    @PostMapping("/joylash")
    public HttpEntity<?> Joylash(@RequestBody IshchiDto ishchiDto){
        ApiRespons apiRespons = ishchiService.addIshchi(ishchiDto);
        return ResponseEntity.status(apiRespons.isHolat()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
    
    @PutMapping("/tahrirlash/{id}")
    public HttpEntity<?> Tahrir(@PathVariable Integer id,@RequestBody IshchiDto ishchiDto){
        ApiRespons apiRespons = ishchiService.tahrirIshchi(id,ishchiDto);
        return ResponseEntity.status(apiRespons.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }

    @GetMapping("/allUqish")
    public HttpEntity<?> allUqish(){
        ApiRespons apiRespons = ishchiService.allUqish();
        return ResponseEntity.status(apiRespons.isHolat()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(apiRespons.getXabar());
    }

    @GetMapping("/IDliO'qish/{id}")
    public HttpEntity<?> IDliUqish(@PathVariable Integer id){
        ApiRespons apiRespons = ishchiService.idUqish(id);
        return ResponseEntity.status(apiRespons.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }

    @DeleteMapping("/O'chirish/{id}")
    public HttpEntity<?> Uchirish(@PathVariable Integer id){
        ApiRespons apiRespons = ishchiService.uchirish(id);
        return ResponseEntity.status(apiRespons.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
}
