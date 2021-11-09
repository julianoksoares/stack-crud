package io.jks.stackcrud.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CepRetorno {
    private Integer status;
    private Boolean ok;
    private String code;
    private String state;
    private String city;
    private String district;
    private String address;
    private String statusText;
}