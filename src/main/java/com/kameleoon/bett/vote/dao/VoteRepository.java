package com.kameleoon.bett.vote.dao;

import com.kameleoon.bett.quote.model.Quote;
import com.kameleoon.bett.vote.model.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByQuoteId(Long quoteId, Pageable pageable);

    List<Vote> findAllByQuoteIn(List<Quote> quotes);
}