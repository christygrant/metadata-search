package edu.ucar.cisl.metadatasearch.repository;

import edu.ucar.cisl.metadatasearch.model.Result;
import edu.ucar.cisl.metadatasearch.model.SearchResults;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.NoOpResponseParser;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
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

    public SearchResults getQueryResults(String queryText) {

        // Default is 10 if not set
        int MAX_ROWS = 100;

        String urlString = "http://localhost:8983/solr/metadata";
        HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
      //  solr.setParser(new NoOpResponseParser());

        SolrQuery query = new SolrQuery().setRows(MAX_ROWS);
        query.set("q", queryText);

        QueryResponse queryResponse = null;
        try {
            queryResponse = solr.query(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SolrDocumentList docList = queryResponse.getResults();

        // Get count of results for SearchResults
        Long resultCount = docList.getNumFound();

        // Get List of Result Objects for SearchResults.  This is more work so needs methods to break it up
        List<Result> resultList = getResultList(queryResponse);

        SearchResults searchResults = new SearchResults(resultCount, resultList);

        return searchResults;
    };

    // Convert queryResponse to List of Result objects
    protected List<Result> getResultList(QueryResponse queryResponse) {

        SolrDocumentList solrResults = queryResponse.getResults();

        List<Result> resultList = new ArrayList<Result>();

        for (SolrDocument document : solrResults) {

            // Create our Result from Solr info
            Result result = new Result();
            result.setTitle(getSolrDocumentFieldValue(document, "title"));
            result.setDescription(getSolrDocumentFieldValue(document, "description"));

            resultList.add(result);
        }

        return resultList;
    }

    // Given SolrDocument and field name, pull out field value
    protected String getSolrDocumentFieldValue(SolrDocument document, String field) {

        String fieldValue;

        Object value = document.getFieldValue(field);

        if (value == null) {
            fieldValue = "";
        } else {
            fieldValue = value.toString();
        }

        return fieldValue;
    }
}
