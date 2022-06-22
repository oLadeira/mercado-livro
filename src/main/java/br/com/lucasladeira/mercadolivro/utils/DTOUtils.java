package br.com.lucasladeira.mercadolivro.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DTOUtils {

    public static final ModelMapper mapper = new ModelMapper();

    public <D> D fromDTO(Object source, Class<D> destinationType){
        return mapper.map(source, destinationType);
    }

    public <D> D toDTO(Object source, Class<D> destinationType){
        return mapper.map(source, destinationType);
    }
}
