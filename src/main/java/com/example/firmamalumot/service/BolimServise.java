package com.example.firmamalumot.service;

import com.example.firmamalumot.entity.Bolim;
import com.example.firmamalumot.entity.Firma;
import com.example.firmamalumot.payload.ApiRespons;
import com.example.firmamalumot.payload.BolimDto;
import com.example.firmamalumot.repository.BolimRepository;
import com.example.firmamalumot.repository.FirmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BolimServise {
    @Autowired
    BolimRepository bolimRepository;
    @Autowired
    FirmaRepository firmaRepository;

    public ApiRespons addBolim(BolimDto bolimDto) {
        boolean b = firmaRepository.existsByFirmaNomi(bolimDto.getFirmaNomi());
        if (b){
            List<Firma> list=firmaRepository.findAll();
            for (Firma firma : list) {
                boolean b1 = bolimRepository.existsByNomiAndFirma_Id(bolimDto.getNomi(), firma.getId());
                if (!b1){
                    if (firma.getFirmaNomi().equals(bolimDto.getFirmaNomi())){
                        Bolim bolim=new Bolim();
                        bolim.setNomi(bolimDto.getNomi());
                        bolim.setFirma(firma);
                        bolimRepository.save(bolim);
                        return new ApiRespons("Ma'lumotlar muvoffaqiyatli bazaga saqlani", true);
                    }
                }
            }
        }
        return new ApiRespons("Bolim qo'shilayotgan firma mavjud emas",false);
    }
    public ApiRespons tahrirBulim(Integer id,Bolim bolim) {
        Optional<Bolim> byId = bolimRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bunday IDli bo'lim mavjud emas",false);
        }
        Bolim bolim1 = byId.get();
        bolim1.setNomi(bolim.getNomi());
        bolimRepository.save(bolim1);
        return new ApiRespons("Muvaffaqiyatli tahrirlandi",true);
    }

    public ApiRespons deleteBulim(Integer id) {
        Optional<Bolim> byId = bolimRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bunday IDli bo'lim mavjud emas",false);
        }
        bolimRepository.deleteById(id);
        return new ApiRespons("Bo'lim ma'lumotlari o'chirildi",true);
    }

    public ApiRespons allUqish() {
        List<Bolim> bolimList = bolimRepository.findAll();
        if (bolimList.isEmpty()){
            return new ApiRespons("Ushbu baza bo'sh",false);
        }
        String S = "";
        for (Bolim bolim : bolimList) {
            S += bolim;
            S += "\n";
        }
        return new ApiRespons(S,true);
    }

    public ApiRespons Uqish(Integer id) {
        Optional<Bolim> byId = bolimRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bunday IDli bo'lim mavjud emas",false);
        }
        String S = byId.toString();
        return new ApiRespons(S,true);
    }
}
