/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.Radishy.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author 54347
 */
@Service
public class MailService {
    
    @Autowired
   private JavaMailSender javaMailSender;
    
    public void enviarMail(String destinatario, String asunto, String contenido){
       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
       simpleMailMessage.setTo(destinatario);
       simpleMailMessage.setSubject(asunto);
       simpleMailMessage.setText(contenido);
       javaMailSender.send(simpleMailMessage);
    } 
   
    public void recuperarContrasenia(String destinatario, String contra){
       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
       String asunto = "Recuperar contraseña =)";
       String contenido = "Esta es tu nueva contraseña: " + contra;
       simpleMailMessage.setTo(destinatario);
       simpleMailMessage.setSubject(asunto);
       simpleMailMessage.setText(contenido);
       javaMailSender.send(simpleMailMessage);
    }

    public void mandarMail(String nombre, String correo, String num, String msj){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
       String asunto = correo;
       String contenido = nombre + "\n" + num + "\n" + msj;
       simpleMailMessage.setTo("radishycompany@gmail.com");
       simpleMailMessage.setSubject(asunto);
       simpleMailMessage.setText(contenido);
       javaMailSender.send(simpleMailMessage);
    }
}