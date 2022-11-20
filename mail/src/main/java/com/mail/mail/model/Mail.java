package com.mail.mail.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Mail {
    private String firstname, lastname, email;
    private String product, issue;

    private String userInfos;

    public void setUserInfos(String f, String l, String em) {
        userInfos = f + " | " + l + " | " + em;
    }
}
