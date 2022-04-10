package com.company.dto;

import com.company.enums.TransactionType;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDT0 {
    private Integer id;
    private String card_num;
    private String terminal_cod;
    private Long amaunt;
    private LocalDateTime create_date;
    private TransactionType type;

}
