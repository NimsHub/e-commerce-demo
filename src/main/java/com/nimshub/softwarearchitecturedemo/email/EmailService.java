package com.nimshub.softwarearchitecturedemo.email;

public interface EmailService {
    void send(String receiver,String subject,String body);
}
