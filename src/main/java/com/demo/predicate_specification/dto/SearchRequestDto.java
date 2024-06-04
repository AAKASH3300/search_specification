package com.demo.predicate_specification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {

    String columnName;
    String value;

}
