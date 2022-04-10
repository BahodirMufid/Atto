package com.company.dto;

import com.company.enums.CardStatus;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private Integer id;
    private String number;
    private Double balance;
    private String phone;
    private String expDate;
    private CardStatus status;
    private LocalDateTime create_date;

}
