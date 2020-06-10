package edu.ucar.cisl.metadatasearch.repository;

import edu.ucar.cisl.metadatasearch.model.SearchResults;

public interface SearchRepository {

    SearchResults getQueryResults(String queryText);

}
