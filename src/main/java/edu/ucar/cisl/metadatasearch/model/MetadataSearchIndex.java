package edu.ucar.cisl.metadatasearch.model;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrInputDocument;

public class MetadataSearchIndex {

    private HttpSolrClient solrClient;

    public MetadataSearchIndex(HttpSolrClient solrClient) {

        this.solrClient = solrClient;
    }

    public void connectToSolr () {
        /* TODO: put in application.properties */
        String urlString = "http://localhost:8983/solr/#/metadata/query";
        HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
        solr.setParser(new XMLResponseParser());
    }


}
