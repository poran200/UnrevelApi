package com.unrevel.api.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static com.unrevel.api.utill.ResponseBuilder.getFailureResponse;
import static com.unrevel.api.utill.ResponseBuilder.getInternalServerError;


@Aspect
@Configuration
@Log4j2
public class DataValidation {

@Around("@annotation(com.unrevel.api.anotation.DataValidation) && args(..))")
public ResponseEntity<?> createValidate(ProceedingJoinPoint joinPoint){
        var signatureAgrs = joinPoint.getArgs();
        BindingResult bindingResult = null;
        for (Object signatureAgr : signatureAgrs) {
        if (signatureAgr instanceof BindingResult) {
        bindingResult = (BindingResult) signatureAgr;
        }
        }
        if (bindingResult != null && bindingResult.hasFieldErrors()){
            var response = getFailureResponse(bindingResult, "Validation error");
            return ResponseEntity.status((int) response.getStatusCode()).body(response);
        }
        try{
            return (ResponseEntity<?>) joinPoint.proceed();
        } catch (Throwable throwable) {
        log.error(throwable.getMessage());
        log.error(throwable.getStackTrace());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getInternalServerError());
        }
        }

//        @Around("@annotation(com.unrevel.api.anotation.PageAbleData) && args(..))")
//        public ResponseEntity<?> pageableData(ProceedingJoinPoint joinPoint){
//          var args = joinPoint.getArgs();
//          Pageable pageable = null;
//          for(Object singnetureagrs: args){
//                  if (singnetureagrs instanceof Pageable){
//                       pageable =(Pageable) singnetureagrs;
//                  }
//          }
//            try {
//                if (pageable== null){
//                    pageable= PageRequest.of(0, 10);
//                }
//                return (ResponseEntity<?>)joinPoint.proceed(new Pageable[]{pageable});
//            } catch (Throwable throwable) {
//                log.error("page abel data not readable");
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getInternalServerError());
//            }
//
//        }

@Pointcut("execution (public * com.unrevel.api.controller.*.*(..))")
public void controllerAll(){}
        }

