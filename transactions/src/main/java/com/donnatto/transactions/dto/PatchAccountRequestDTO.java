package com.donnatto.transactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatchAccountRequestDTO {
    
    private Optional<String> accountType;
    private Optional<String> clientId;
}
