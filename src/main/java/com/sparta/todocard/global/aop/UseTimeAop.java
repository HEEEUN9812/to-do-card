package com.sparta.todocard.global.aop;

import com.sparta.todocard.entity.User;
import com.sparta.todocard.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j(topic = "UseTimeAop")
@Aspect
@Component
@RequiredArgsConstructor
public class UseTimeAop {

    private final ApiUseTimeRepository apiUseTimeRepository;

    @Around("@within(com.sparta.todocard.global.aop.PerfLogging) || @annotation(com.sparta.todocard.global.aop.PerfLogging)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        Long startTime = System.currentTimeMillis();

        try{
            Object output = joinPoint.proceed();
            return output;
        } finally {
            Long endTime = System.currentTimeMillis();
            Long runTime = endTime - startTime;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth != null && auth.getPrincipal().getClass() == UserDetailsImpl.class){
                UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
                User loginUser = userDetails.getUser();

                ApiUseTime apiUseTime = apiUseTimeRepository.findByUser(loginUser).orElse(null);
                if (apiUseTime == null) {
                    apiUseTime = new ApiUseTime(loginUser, runTime);
                }else {
                    apiUseTime.addUseTime(runTime);
                }

                log.info("[API Use Time] Username" + loginUser.getUsername() + ", Total Time: " + apiUseTime.getTotalTime());
                apiUseTimeRepository.save(apiUseTime);
            }
        }
    }

}
