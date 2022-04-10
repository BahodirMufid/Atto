package com.company.dto;

import com.company.enums.ProfileCardStatus;
import com.company.enums.ProfileCardType;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCard {
    private String cardNum;
    private String phoneNum;
    private LocalDateTime createDate;
    private ProfileCardStatus status;
    private Long balance;
    private String expDate;
    private ProfileCardType type;

}
