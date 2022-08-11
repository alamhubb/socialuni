package com.socialuni.sdk.utils.common;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RestUtil {
    public static RestTemplate getFileRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    public static RestTemplate restTemplate() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        StringHttpMessageConverter xmlConverter = new StringHttpMessageConverter();
        //优先xml解析
        List<MediaType> xmlTypes = new ArrayList<>();
        xmlTypes.add(MediaType.APPLICATION_XML);
        xmlTypes.add(MediaType.TEXT_PLAIN);
        xmlConverter.setSupportedMediaTypes(xmlTypes);
        // json
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> jsonTypes = new ArrayList<>();
        jsonTypes.add(MediaType.APPLICATION_JSON);
        jsonTypes.add(MediaType.APPLICATION_JSON_UTF8);
        jsonTypes.add(MediaType.APPLICATION_ATOM_XML);
        jsonTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        jsonTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        jsonTypes.add(MediaType.APPLICATION_PDF);
        jsonTypes.add(MediaType.APPLICATION_RSS_XML);
        jsonTypes.add(MediaType.APPLICATION_XHTML_XML);
        jsonTypes.add(MediaType.IMAGE_GIF);
        jsonTypes.add(MediaType.IMAGE_JPEG);
        jsonTypes.add(MediaType.IMAGE_PNG);
        jsonTypes.add(MediaType.TEXT_EVENT_STREAM);
        jsonTypes.add(MediaType.TEXT_HTML);
        jsonTypes.add(MediaType.TEXT_MARKDOWN);
        jsonTypes.add(MediaType.TEXT_PLAIN);
        jsonTypes.add(MediaType.TEXT_XML);
        jsonConverter.setSupportedMediaTypes(jsonTypes);

        messageConverters.add(xmlConverter);
        messageConverters.add(jsonConverter);
        //form
        messageConverters.add(new FormHttpMessageConverter());

        restTemplate.setMessageConverters(messageConverters);
        //使用build()方法进行获取
        return restTemplate;
    }
}
