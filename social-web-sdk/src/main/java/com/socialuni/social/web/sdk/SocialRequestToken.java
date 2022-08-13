package com.socialuni.social.web.sdk;

import javax.servlet.http.HttpServletRequest;

public interface SocialRequestToken {
    String getToken(HttpServletRequest request);
}