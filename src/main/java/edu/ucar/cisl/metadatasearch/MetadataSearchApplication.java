package edu.ucar.cisl.metadatasearch;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MetadataSearchApplication {

    @Value("${solr.host}")
    private String solrHost;

    public static void main(String[] args) {

        SpringApplication.run(MetadataSearchApplication.class, args);
    }

    @Bean
    public HttpSolrClient createHttpSolrClient (){

         return new HttpSolrClient.Builder(this.solrHost).build();
    }

}
