package com.kameleoon.bett.quote.dao;

import com.kameleoon.bett.quote.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Page<Quote> findAll(Pageable pageable);
}