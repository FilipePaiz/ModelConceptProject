package com.shop.project.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.shop.project.controllers.exceptions.FieldMessage;
import com.shop.project.daos.enums.ClientType;
import com.shop.project.dto.ClientFullDTO;
import com.shop.project.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientFullDTO> {
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientFullDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getType().equals(ClientType.INDIVIDUALPERSON.getCode()) && !BR.isValidCPF(objDto.getIdCard())) {
			list.add(new FieldMessage("idCard", "Id Card 1 not valid"));
		}
		
		if(objDto.getType().equals(ClientType.COMPANYPERSON.getCode()) && !BR.isValidCNPJ(objDto.getIdCard())) {
			list.add(new FieldMessage("idCard", "Id Card 2 not valid"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
