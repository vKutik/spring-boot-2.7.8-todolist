package com.example.test.dto.mapper;

public interface RequestMapperDto <D,T>
{
    T toModel(D d);
}

