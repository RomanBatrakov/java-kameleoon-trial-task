package com.kameleoon.bett.vote.mapper;

import com.kameleoon.bett.vote.dto.VoteDto;
import com.kameleoon.bett.vote.model.Vote;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VoteMapper {
    VoteDto toVoteDto(Vote vote);

    List<VoteDto> toVoteDtoList(List<Vote> votes);
}