package com.demo.common.orika.config;

import com.demo.common.orika.converter.OrikaCustomConverterEnum;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuhoujie
 * @date 2020/8/13
 * @desc orika mapperfactory 注入容器
 */
@Configuration
public class OrikaConfiguration {

    private MapperFactory mapperFactory;

    @Bean
    public MapperFactory mapperFactory() {
        this.mapperFactory = new DefaultMapperFactory.Builder().build();

        // 自定义mapper注册
        this.registerCustomMapper(mapperFactory.getConverterFactory());

        // 自定义转换器注册
        this.registerCustomConverterGlobal(mapperFactory.getConverterFactory());
        this.registerCustomConverterById(mapperFactory.getConverterFactory());

        return mapperFactory;
    }


    /**
     * 注册自定义mapper
     * @param converterFactory
     */
    private void registerCustomMapper(ConverterFactory converterFactory) {
        // ...
    }

    /**
     * 注册全局类型的自定义转换器, 一般是orika的内建转换器没有指定类型转换器时才能匹配到,否则会先匹配内建的转换器
     * 如: 时间类型的转换
     * @param converterFactory
     */
    private void registerCustomConverterGlobal(ConverterFactory converterFactory) {
        // ...
    }

    /**
     * 注册id类型的自定义转换器, 用于特定转换时 指定转换器注册的id来达成特殊的转换
     * @param converterFactory
     */
    private void registerCustomConverterById(ConverterFactory converterFactory) {
        converterFactory.registerConverter(OrikaCustomConverterEnum.ORIKA_JSON_CONVERTER.id(),OrikaCustomConverterEnum.ORIKA_JSON_CONVERTER.value());
    }
}
