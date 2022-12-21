package com.kovaitech.adani.solar.supervision.bean;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    public String id;

    public String firstname;
    public String lastname;
    public String email;
    public String mobile;
    public String username;
    public String password;


}
