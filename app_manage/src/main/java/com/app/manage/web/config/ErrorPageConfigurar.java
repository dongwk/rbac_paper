package com.app.manage.web.config;

import com.app.common.web.constant.ErrorPagePath;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorPageConfigurar implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages=new ErrorPage[2];
        errorPages[0]=new ErrorPage(HttpStatus.NOT_FOUND, ErrorPagePath.E404);
        errorPages[1]=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,ErrorPagePath.E500);

        registry.addErrorPages(errorPages);
    }
}