package com.poc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.model.Hero;
import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@ApplicationScoped
@Slf4j
public class CachingService {

    ObjectMapper objectMapper = new ObjectMapper();
    @Inject
    RedisClient redisClient;

    public List<Hero> getHeroesByFilmID(Integer episodeID) throws JsonProcessingException {
        log.info("Getting heroes information from episodeID={}", episodeID);
        Response response = redisClient.get(episodeID.toString());

        if (response == null) {
            return Collections.emptyList();
        }

        return objectMapper.readValue(response.toString(), new TypeReference<>() {
        });
    }

    public void setHeroesByFilmID(Integer episodeID, List<Hero> heroes) throws JsonProcessingException {
        log.info("Caching heroes for 60 secs.");
        redisClient.setex(episodeID.toString(), "60", objectMapper.writeValueAsString(heroes));
    }
}
