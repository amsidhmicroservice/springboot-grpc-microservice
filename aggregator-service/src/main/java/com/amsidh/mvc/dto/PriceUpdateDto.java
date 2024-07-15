package com.amsidh.mvc.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PriceUpdateDto {
    private String ticker;
    private Integer price;
}
