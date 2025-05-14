package com.lmw.web.custom.converter;

import com.lmw.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class BaseEnumConverterFactory implements ConverterFactory<Integer, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return new BaseEnumConverter<>(targetType);
    }

    private static class BaseEnumConverter<T extends BaseEnum> implements Converter<Integer, T> {
        private final Class<T> enumType;

        public BaseEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(Integer source) {
            try {
                return (T) enumType.getMethod("valueOfCode", Integer.class).invoke(null, source);
            } catch (Exception e) {
                throw new IllegalArgumentException("Cannot convert " + source + " to " + enumType.getSimpleName(), e);
            }
        }
    }
}