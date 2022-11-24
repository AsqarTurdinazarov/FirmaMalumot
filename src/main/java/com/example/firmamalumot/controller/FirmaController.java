package com.example.firmamalumot.controller;

import com.example.firmamalumot.entity.Firma;
import com.example.firmamalumot.payload.ApiRespons;
import com.example.firmamalumot.payload.FirmaDto;
import com.example.firmamalumot.service.FirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firma")
public class FirmaController {

    @Autowired
    FirmaService firmaService;

    @PostMapping("/Joylash")
    public HttpEntity<?> Joylash(@RequestBody FirmaDto firmaDto){
        ApiRespons apiRespons = firmaService.addFirma(firmaDto);
        return ResponseEntity.status(apiRespons.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiRespons.getXabar());
    }

    @PutMapping("/tahrirlash/{id}")
    public HttpEntity<?> Tahrir(@PathVariable Integer id,@RequestBody FirmaDto firmaDto){
        ApiRespons apiRespons = firmaService.writeFirma(id,firmaDto);
        return ResponseEntity.status(apiRespons.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }

    @GetMapping("/allUish")
    public HttpEntity<?> allUqish(){
        ApiRespons apiRespons = firmaService.allUqish();
        return ResponseEntity.status(apiRespons.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }

    @GetMapping("/idliUqish/{id}")
    public HttpEntity<?> IDliUqish(@PathVariable Integer id){
        ApiRespons apiRespons = firmaService.idUqish(id);
        return ResponseEntity.status(apiRespons.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delFirma(@PathVariable Integer id){
        ApiRespons apiRespons = firmaService.delete(id);
        return ResponseEntity.status(apiRespons.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
}
