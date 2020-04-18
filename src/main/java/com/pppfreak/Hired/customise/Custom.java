package com.pppfreak.Hired.customise;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;
@Component
public class Custom {
    private Random random = new SecureRandom();
    private  String ALPHABET="0123456789qwertyuiopasdflkjghzxcvmnbQPWOEIRUTYLAKSJDFHGMNBVXZC";

    public String generatedCustomUserId(){
      return   generatedRandomUserId();
    }
    private String generatedRandomUserId(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return builder.toString();
    }
}
