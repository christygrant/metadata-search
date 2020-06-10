package edu.ucar.cisl.metadatasearch.model;

import java.util.List;

public interface SearchResults {

        Long getResultCount();

        List<Result> getResults();

}
