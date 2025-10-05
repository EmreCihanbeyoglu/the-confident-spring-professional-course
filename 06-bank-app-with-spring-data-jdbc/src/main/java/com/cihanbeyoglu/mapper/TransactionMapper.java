package com.cihanbeyoglu.mapper;

import com.cihanbeyoglu.dto.TransactionDto;
import com.cihanbeyoglu.model.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {


    private final ModelMapper modelMapper;

    @Autowired
    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Transaction mapToEntity(TransactionDto dto) {
        return modelMapper.map(dto, Transaction.class);
    }

    public TransactionDto mapToDto(Transaction entity) {
        return modelMapper.map(entity, TransactionDto.class);
    }

}
