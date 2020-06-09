package edu.ucar.cisl.metadatasearch.model;

import java.util.List;

public class SearchResults implements SearchResultsInterface {

    Long resultCount;
    List<Result> results;

    public SearchResults(Long resultCount, List<Result> results) {
        this.resultCount = resultCount;
        this.results = results;
    }

    public Long getResultCount() {
        return this.resultCount;
    }

    public List<Result> getResults() {
        return this.results;
    }

    // No Setters, just getters.  Must use constructor to set.
}
