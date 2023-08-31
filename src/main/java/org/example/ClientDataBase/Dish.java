package org.example.ClientDataBase;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    private String Name;
    private String Description;
    private String Composition;
    private String Price;
    private String Photo;
}
