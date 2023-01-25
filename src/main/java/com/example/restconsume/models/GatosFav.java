package com.example.restconsume.models;

import com.example.restconsume.utils.Imagex;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GatosFav {
    String id;
    String image_id;
    String apikey= "74668d02-a299-40ae-bb05-130d2e65c227";
    Imagex image;
}
