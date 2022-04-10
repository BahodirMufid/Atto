package com.company.dto;

import com.company.enums.TermStatus;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Terminal {
    private Integer id;
    private String cod;
    private String adress;
    private LocalDateTime creat_date;
    private TermStatus status;
}
