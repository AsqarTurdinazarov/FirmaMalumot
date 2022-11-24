package com.example.firmamalumot.service;

import com.example.firmamalumot.entity.Bolim;
import com.example.firmamalumot.entity.Ishchi;
import com.example.firmamalumot.entity.Manzil;
import com.example.firmamalumot.payload.ApiRespons;
import com.example.firmamalumot.payload.IshchiDto;
import com.example.firmamalumot.repository.BolimRepository;
import com.example.firmamalumot.repository.IshchiRepository;
import com.example.firmamalumot.repository.ManzilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IshchiService {
    @Autowired
    IshchiRepository ishchiRepository;
    @Autowired
    ManzilRepository manzilRepository;
    @Autowired
    BolimRepository bolimRepository;


    public ApiRespons addIshchi(IshchiDto ishchiDto) {
        boolean b = ishchiRepository.existsByTelRaqam(ishchiDto.getTelRaqam());
        if (b){
            return  new ApiRespons("Bunday ishchi mavjud",false);
        }
        Ishchi ishchi = new Ishchi();
        Manzil manzil = new Manzil();
        ishchi.setNomi(ishchiDto.getNomi());
        ishchi.setTelRaqam(ishchiDto.getTelRaqam());
        manzil.setViloyat(ishchiDto.getViloyat());
        manzil.setTuman(ishchiDto.getTuman());
        manzil.setKocha(ishchiDto.getKocha());
        manzil.setUyRaqami(ishchiDto.getUyRaqami());
        Optional<Bolim> byId = bolimRepository.findById(ishchiDto.getBolimId());
        if (!byId.isPresent()){
            return  new ApiRespons("Bunday IDli bo'lim mavjud emas",false);
        }
        manzilRepository.save(manzil);
        Bolim bolim = byId.get();
        bolim.setId(ishchiDto.getBolimId());
        ishchi.setManzil(manzil);
        ishchi.setBolim(bolim);
        ishchiRepository.save(ishchi);
        return  new ApiRespons("Ma'lumotlar bazaga saqlandi",true);
    }

    public ApiRespons tahrirIshchi(Integer id, IshchiDto ishchiDto) {
        Optional<Ishchi> byId = ishchiRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bunday IDli ishchi mavjud emas",false);
        }
        Ishchi ishchi1 = byId.get();
        ishchi1.setNomi(ishchiDto.getNomi());
        ishchi1.setTelRaqam(ishchiDto.getTelRaqam());
        Manzil manzil1 = byId.get().getManzil();
        manzil1.setViloyat(ishchiDto.getViloyat());
        manzil1.setTuman(ishchiDto.getTuman());
        manzil1.setKocha(ishchiDto.getKocha());
        manzil1.setUyRaqami(ishchiDto.getUyRaqami());
        Bolim bolim1 = byId.get().getBolim();
        bolim1.setId(ishchiDto.getBolimId());
        manzilRepository.save(manzil1);
        ishchi1.setBolim(bolim1);
        ishchi1.setManzil(manzil1);
        ishchiRepository.save(ishchi1);
        return new ApiRespons("Ma'lumotlar tahrirlandi",true);
    }

    public ApiRespons allUqish() {
        List<Ishchi> ishchiList = ishchiRepository.findAll();
        if (ishchiList.isEmpty()){
            return new ApiRespons("Ma'lumotlar bazasi bo'sh",false);
        }
        String S = "";
        for (Ishchi ishchi : ishchiList) {
            S += ishchi;
            S += "\n";
        }
        return new ApiRespons(S,true);
    }

    public ApiRespons idUqish(Integer id) {
        Optional<Ishchi> byId = ishchiRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bunday Idli ishchi mavjud emas",false);
        }
        String S = byId.toString();
        return new ApiRespons(S,true);
    }


    public ApiRespons uchirish(Integer id) {
        Optional<Ishchi> byId = ishchiRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bunday IDli ma'lumot mavjud emas",false);
        }
        ishchiRepository.deleteById(id);
        manzilRepository.deleteById(byId.get().getManzil().getId());
        return new ApiRespons("Ma'lumot muvaffaqiyatli o'chirildi",true);
    }
}
