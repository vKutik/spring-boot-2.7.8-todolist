package com.example.test.dto.mapper;

public interface ResponseMapperDto<D, T> {

    D toResponse(T t);
}
