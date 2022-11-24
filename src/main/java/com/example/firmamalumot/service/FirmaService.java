package com.example.firmamalumot.service;

import com.example.firmamalumot.entity.Bolim;
import com.example.firmamalumot.entity.Firma;
import com.example.firmamalumot.entity.Manzil;
import com.example.firmamalumot.payload.ApiRespons;
import com.example.firmamalumot.payload.FirmaDto;
import com.example.firmamalumot.repository.BolimRepository;
import com.example.firmamalumot.repository.FirmaRepository;
import com.example.firmamalumot.repository.ManzilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirmaService {

    @Autowired
    FirmaRepository firmaRepository;
    @Autowired
    ManzilRepository manzilRepository;

    @Autowired
    BolimRepository bolimRepository;

    public ApiRespons addFirma(FirmaDto firmaDto) {
        boolean b = firmaRepository.existsByFirmaNomi(firmaDto.getFirmaNomi());
        if (b){
            return new ApiRespons("Bunday nomli firma mavjud",false);
        }
        Manzil manzil1 = new Manzil();
        manzil1.setViloyat(firmaDto.getViloyat());
        manzil1.setTuman(firmaDto.getTuman());
        manzil1.setKocha(firmaDto.getKocha());
        manzil1.setUyRaqami(firmaDto.getUyRaqami());
        Firma firma1 = new Firma();
        firma1.setFirmaNomi(firmaDto.getFirmaNomi());
        firma1.setDirektorNomi(firmaDto.getDirektorNomi());
        firma1.setManzil(manzil1);
        manzilRepository.save(manzil1);
        firmaRepository.save(firma1);
        return new ApiRespons("Ma'lumotlar saqlandi",true);
    }

    public ApiRespons writeFirma(Integer id,FirmaDto firmaDto) {
        Optional<Firma> byId = firmaRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bunday IDli firma mavjud emas",false);
        }
        Manzil manzil1 = byId.get().getManzil();
        manzil1.setViloyat(firmaDto.getViloyat());
        manzil1.setTuman(firmaDto.getTuman());
        manzil1.setKocha(firmaDto.getKocha());
        manzil1.setUyRaqami(firmaDto.getUyRaqami());
        Firma firma1 = byId.get();
        firma1.setFirmaNomi(firmaDto.getFirmaNomi());
        firma1.setDirektorNomi(firmaDto.getDirektorNomi());
        firma1.setManzil(manzil1);
        manzilRepository.save(manzil1);
        firmaRepository.save(firma1);
        return new ApiRespons("Ma'lumotlar tahrirlandi",true);
    }

    public ApiRespons allUqish() {
        List<Firma> firmaList = firmaRepository.findAll();
        if (firmaList.isEmpty()){
            return new ApiRespons("Baza bo'sh",false);
        }
        String S = "";
        for (Firma firma : firmaList) {
            S += firma.toString();
            S += "\n";
        }
        return new ApiRespons(S,true);
    }

    public ApiRespons idUqish(Integer id) {
        Optional<Firma> byId = firmaRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Ushbu IDli ma'lumot mavjud emas",false);
        }
        String S = byId.toString();
        return new ApiRespons(S,true);
    }

    public ApiRespons delete(Integer id) {
        Optional<Firma> byId = firmaRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Ushbu IDli ma'lumot mavjud emas",false);
        }
        List<Bolim> bolimList = bolimRepository.findAll();
        for (Bolim bolim : bolimList) {
            if (bolim.getFirma().getId() == id){
                bolimRepository.deleteById(bolim.getId());
            }
        }
        firmaRepository.deleteById(id);
        manzilRepository.deleteById(byId.get().getManzil().getId());
        return new ApiRespons("Ma'lumot o'chirildi",true);
    }
}
