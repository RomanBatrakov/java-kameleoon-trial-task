package com.kameleoon.bett.vote.model;

import com.kameleoon.bett.quote.model.Quote;
import com.kameleoon.bett.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "quote_id", referencedColumnName = "id")
    private Quote quote;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "reaction")
    @Enumerated(EnumType.STRING)
    private Reaction reaction;
}