package com.example.firmamalumot.controller;

import com.example.firmamalumot.entity.Bolim;
import com.example.firmamalumot.payload.ApiRespons;
import com.example.firmamalumot.payload.BolimDto;
import com.example.firmamalumot.service.BolimServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bo'lim")
public class BolimController {
    @Autowired
    BolimServise bolimServise;

    @PostMapping("/joylash")
    public HttpEntity<?> Joylash(@RequestBody BolimDto bolimDto){
        ApiRespons apiRespons = bolimServise.addBolim(bolimDto);
        return ResponseEntity.status(apiRespons.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiRespons.getXabar());
    }

    @PutMapping("/tahrirBo'lim/{id}")
    public HttpEntity<?> Tahrir(@PathVariable Integer id,@RequestBody Bolim bolim){
        ApiRespons apiRespons = bolimServise.tahrirBulim(id,bolim);
        return ResponseEntity.status(apiRespons.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> Delete(@PathVariable Integer id){
        ApiRespons apiRespons = bolimServise.deleteBulim(id);
        return ResponseEntity.status(apiRespons.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }

    @GetMapping("/allBo'lim")
    public HttpEntity<?> allUqish(){
        ApiRespons apiRespons = bolimServise.allUqish();
        return ResponseEntity.status(apiRespons.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }

    @GetMapping("/Uqish/{id}")
    public HttpEntity<?> Uqish(@PathVariable Integer id){
        ApiRespons apiRespons = bolimServise.Uqish(id);
        return ResponseEntity.status(apiRespons.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
}
