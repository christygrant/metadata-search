package edu.ucar.cisl.metadatasearch.repository;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.NoOpResponseParser;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchRepository {

    public List getAllTest() {
        List results = new ArrayList();

        results.add ("Thing1");
        results.add ("Thing2");
        results.add ("Thing3");

        return results;
    }

    public List getAll() {

        String urlString = "http://localhost:8983/solr/metadata";
        HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
      //  solr.setParser(new NoOpResponseParser());

        SolrQuery query = new SolrQuery();
        query.set("q", "*:*");

        QueryResponse response = null;
        try {
            response = solr.query(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SolrDocumentList docList = response.getResults();

        return docList;
    };
}
