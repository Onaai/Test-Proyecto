package com.integrador.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

        @Autowired
        private JavaMailSender mailSender;

        public void sendPasswordResetEmail(String toEmail, String resetUrl) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setTo(toEmail);
                helper.setSubject("Restablecer Contraseña");
                helper.setText("<p>Haz clic en el siguiente enlace para restablecer tu contraseña:</p>"
                        + "<a href=\"" + resetUrl + "\">Restablecer contraseña</a>", true);

                mailSender.send(message);
                System.out.println("Correo enviado a: " + toEmail);
            } catch (MessagingException e) {
                System.err.println("Error al enviar el correo: " + e.getMessage());
            }
        }
    }


