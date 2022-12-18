package com.nfw.application.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublicationDto {
    private String source;
    private String title;
    private String description;
    private String url;
    private String language;
    private LocalDate published_at;
    private String category;
}
