package com.company.dto;

import com.company.enums.ProfileStatus;
import com.company.enums.ProfileType;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private LocalDateTime create_date;
    private ProfileType type;
    private ProfileStatus status;
}
