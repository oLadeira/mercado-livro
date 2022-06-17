package br.com.lucasladeira.mercadolivro.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DTOUtils {

    public static final ModelMapper mapper = new ModelMapper();


    public <T, V> V fromDTO(T t, V v){
        v = (V) mapper.map(t, v.getClass());
        return v;
    }
}
