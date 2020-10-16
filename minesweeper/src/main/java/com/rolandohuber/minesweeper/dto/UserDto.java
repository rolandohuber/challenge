package com.rolandohuber.minesweeper.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private long id;
    private String username;
}
