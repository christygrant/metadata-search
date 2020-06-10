package edu.ucar.cisl.metadatasearch.model;

import java.util.List;

public class SearchResultsImpl implements SearchResults {

    Long resultCount;
    List<Result> results;

    public SearchResultsImpl(Long resultCount, List<Result> results) {
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
