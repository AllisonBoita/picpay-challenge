package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.NotificationDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        /*ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://run.mocky.io/v3/b844a612-60c4-4db0-85e9-33b15af1890c", notificationRequest, String.class);

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            System.out.println("Erro ao enviar notificação");
            throw new Exception("Serviço fora do ar");
        }*/

        System.out.println("Notificação enviada ao usuário.");
    }
}

// https://run.mocky.io/v3/3aec4638-4052-45c5-9814-44fac1473d95 - 400
// https://run.mocky.io/v3/b844a612-60c4-4db0-85e9-33b15af1890c - 200