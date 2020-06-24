package com.ok.cache.domain;

import lombok.*;

@ToString
@EqualsAndHashCode(of = "id")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    private String id;

    private String location;

    private double capacity;

    private boolean isFree;
}
