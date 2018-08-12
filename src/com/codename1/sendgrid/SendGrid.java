/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Codename One designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *  
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Please contact Codename One through http://www.codenameone.com/ if you 
 * need additional information or have any questions.
 */

package com.codename1.sendgrid;

import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.util.CallbackAdapter;

/**
 * Simple API for sending an email via sendgrid
 *
 * @author Shai Almog
 */
public class SendGrid {
    private String token;
    private SendGrid(String token) {
        this.token = token;
    }
    
    /**
     * You need the API token from send grid to use this class, it's available 
     * by signing up to their service
     * @param token the API token
     * @return the class instance
     */
    public static SendGrid create(String token) {
        return new SendGrid(token);
    }

    /**
     * Sends an email synchronously 
     * @param to to email
     * @param from from email
     * @param subject email subject
     * @param body the body of the email
     */
    public void sendSync(String to, String from, String subject, String body) {
        Response<String> s = Rest.post("https://api.sendgrid.com/v3/mail/send").
            jsonContent().
            header("Authorization", "Bearer "+ token).
            body("{\"personalizations\": [{\"to\": [{\"email\": \"" + to + 
                "\"}]}],\"from\": {\"email\": \"" + from + 
                "\"},\"subject\": \"" + from + 
                "\",\"content\": [{\"type\": \"text/plain\", \"value\": \"" + 
                body + "\"}]}").getAsString();        
    }

    /**
     * Sends an email asynchronously 
     * @param to to email
     * @param from from email
     * @param subject email subject
     * @param body the body of the email
     */
    public void sendASync(String to, String from, String subject, String body) {
        Rest.post("https://api.sendgrid.com/v3/mail/send").
            jsonContent().
            header("Authorization", "Bearer "+ token).
            body("{\"personalizations\": [{\"to\": [{\"email\": \"" + to + 
                "\"}]}],\"from\": {\"email\": \"" + from + 
                "\"},\"subject\": \"" + from + 
                "\",\"content\": [{\"type\": \"text/plain\", \"value\": \"" + 
                body + "\"}]}").getAsStringAsync(new CallbackAdapter<>());
    }
}
