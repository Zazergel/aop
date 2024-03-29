package com.zazergel.aop.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserExtendedDto {
    private Long id;
    private String name;
    private String email;
    private Integer countOfOrders;

}

