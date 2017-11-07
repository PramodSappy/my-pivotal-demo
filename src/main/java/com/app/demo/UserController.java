package com.app.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    StringRedisTemplate redisTemplate;

    ValueOperations<String, String> opsName;
    String key = "spring.boot.redis.test1";


    @RequestMapping("/setName")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        opsName = this.redisTemplate.opsForValue();
        if (!this.redisTemplate.hasKey(key)) {
            opsName.set(key, name);
        }
        System.out.println("Found key " + key + ", value=" + opsName.get(key));
        return "Hurry! your name saved in redis db! " + name;
    }

    @RequestMapping("/getName")
    public String getName() {
        if (!this.redisTemplate.hasKey(key)) {
            System.out.println("Key not found");
        } else {
            System.out.println("Found key " + key + ", value=" + opsName.get(key));
        }
        return "Here is your name from redis db ! " + opsName.get(key);
    }

    @RequestMapping("/")
    public String greeting() {
        return "greetings!";
    }


    @RequestMapping("/redis")
    public String redis() {
        int counter = 0;
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        String key = counter + "";
        if (!this.redisTemplate.hasKey(key)) {
            ops.set(key, Integer.toString(counter));
            System.out.println("Key not found");
        } else {
            counter = Integer.parseInt(ops.get(key));
            counter++;
            ops.set(key, Integer.toString(counter));
            System.out.println("Key  found");
        }
        return ops.get(key);

    }

    @RequestMapping("/load")
    public String load() {
        for (int i = 0; i < 100000; i++) {
            System.out.println("Load " + i);
            for (int j = 0; j < 1000000000; j++) {
                double foo = 1.0 + 2.0 + 30000000.0 / 20000004.201;
            }
        }
        return "load complete!";
    }

}
