package com.jastech.hroauth.interfaces;

import com.jastech.hroauth.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "hr-user", path = "/users")
public interface UserFeignClient {

    @GetMapping(value = "{id}")
    ResponseEntity<User> findById(@PathVariable("id") Long id);

    @GetMapping()
    ResponseEntity<User> findByEmail(@RequestParam("email") String email);
}
