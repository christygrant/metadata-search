package edu.ucar.cisl.metadatasearch.repository;

import edu.ucar.cisl.metadatasearch.model.Result;
import edu.ucar.cisl.metadatasearch.model.SearchResultsImpl;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository{

    private HttpSolrClient solrClient;

    @Autowired
    public SearchRepositoryImpl(HttpSolrClient solrClient) {
        this.solrClient = solrClient;
    }

    public List getAllTest() {
        List results = new ArrayList();

        results.add ("Thing1");
        results.add ("Thing2");
        results.add ("Thing3");

        return results;
    }

    public SearchResultsImpl getQueryResults(String queryText) {

        // Default is 10 if not set
        int MAX_ROWS = 100;

        SolrQuery query = new SolrQuery().setRows(MAX_ROWS);
        query.set("q", queryText);

        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SolrDocumentList docList = queryResponse.getResults();

        // Get count of results for SearchResultsImpl
        Long resultCount = docList.getNumFound();

        // Get List of Result Objects for SearchResultsImpl.  This is more work so needs methods to break it up
        List<Result> resultList = getResultList(queryResponse);

        SearchResultsImpl searchResults = new SearchResultsImpl(resultCount, resultList);

        return searchResults;
    };

    // Convert queryResponse to List of Result objects
    protected List<Result> getResultList(QueryResponse queryResponse) {

        SolrDocumentList solrResults = queryResponse.getResults();

        List<Result> resultList = new ArrayList<Result>();

        for (SolrDocument document : solrResults) {
            Result result = setResultFromSolrFieldValues(document);
            resultList.add(result);
        }

        return resultList;
    }

    private Result setResultFromSolrFieldValues(SolrDocument document) {
        // Create our Result from Solr info
        Result result = new Result();

        result.setTitle(getSolrDocumentFieldValue(document, "title"));
        result.setDescription(getSolrDocumentFieldValue(document, "description"));

        return result;
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
