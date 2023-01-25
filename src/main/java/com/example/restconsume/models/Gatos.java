package com.example.restconsume.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Gatos {
    private String id;
    private String url;
    private String apikey = "74668d02-a299-40ae-bb05-130d2e65c227";
    private String image;
}
