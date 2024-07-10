package com.ahu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

@Configuration
public class RestTemplateConfig {

        @Bean
        public RestTemplate restTemplate() throws Exception {
                // 创建SSLContext，忽略证书验证
                SSLContext sslContext = SSLContextBuilder.create()
                                .loadTrustMaterial((certificate, authType) -> true).build();

                // 创建CloseableHttpClient，配置SSLContext和NoopHostnameVerifier来忽略主机名验证
                @SuppressWarnings("deprecation")
                CloseableHttpClient httpClient = HttpClients.custom()
                                .setSslcontext(sslContext)
                                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                                .build();

                // 创建ClientHttpRequestFactory，配置httpClient
                ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

                // 创建并返回配置了自定义请求工厂的RestTemplate
                return new RestTemplate(requestFactory);
        }
}