package edu.ucar.cisl.metadatasearch.model;

import java.util.List;

public interface SearchResultsInterface {

        Long getResultCount();

        List<Result> getResults();

}
