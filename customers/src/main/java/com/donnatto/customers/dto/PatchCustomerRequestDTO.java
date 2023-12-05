package com.donnatto.customers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatchCustomerRequestDTO {
    private Optional<String> dni;
    private Optional<String> name;
    private Optional<String> gender;
    private Optional<Integer> age;
    private Optional<String> address;
    private Optional<String> phone;
    private Optional<String> password;
}
