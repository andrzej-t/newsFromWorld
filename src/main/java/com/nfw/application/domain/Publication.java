package com.nfw.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Publication {
    String source;
    String title;
    String description;
    String url;
    String language;
    LocalDate published_at;
    String category;
}
