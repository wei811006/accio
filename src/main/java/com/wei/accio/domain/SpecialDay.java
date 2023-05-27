package com.wei.accio.domain;


import lombok.*;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class SpecialDay {

    private LocalDate date;

    private Boolean isWork;

}
