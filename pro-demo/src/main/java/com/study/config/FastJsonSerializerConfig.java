package com.study.config;

// @EnableWebMvc
// @Configuration
// public class FastJsonSerializerConfig implements WebMvcConfigurer {
//
//
//     @Override
//     public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//         FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//         FastJsonConfig config = new FastJsonConfig();
//         config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
//         config.setWriterFeatures(JSONWriter.Feature.WriteMapNullValue, JSONWriter.Feature.PrettyFormat);
//         converter.setFastJsonConfig(config);
//         converter.setDefaultCharset(StandardCharsets.UTF_8);
//         List<MediaType> supportedMediaTypes = new ArrayList<>();
//         supportedMediaTypes.add(MediaType.APPLICATION_JSON);
//         converter.setSupportedMediaTypes(supportedMediaTypes);
//         converters.add(0, converter);
//     }
// }

