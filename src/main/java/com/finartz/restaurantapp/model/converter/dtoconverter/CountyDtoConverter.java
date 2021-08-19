package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.CountyDto;
import com.finartz.restaurantapp.model.entity.CountyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CountyDtoConverter implements GenericConverter<CountyEntity, CountyDto> {

    @Override
    public CountyDto convert(final CountyEntity countyEntity){
        if(Objects.isNull(countyEntity)){
            return null;
        }

        CountyDto countyDto = new CountyDto();

        countyDto.setId(countyEntity.getId());
        countyDto.setName(countyEntity.getName());
        if(Objects.nonNull(countyEntity.getCityEntity())) {
            countyDto.setCityId(countyEntity.getCityEntity().getId());
        }

        return countyDto;
    }

}
