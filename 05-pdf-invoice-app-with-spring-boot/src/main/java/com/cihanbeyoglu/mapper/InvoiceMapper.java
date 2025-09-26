package com.cihanbeyoglu.mapper;

import com.cihanbeyoglu.dto.InvoiceDTO;
import com.cihanbeyoglu.model.Invoice;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    private final ModelMapper modelMapper;

    public InvoiceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public InvoiceDTO mapToDto(Invoice invoice) {
        return modelMapper.map(invoice, InvoiceDTO.class);
    }


}
