package com.joaowudarski.host;

import com.br.jvcw.annotation.SecurityToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaowudarski.media.MediaType;
import com.joaowudarski.media.request.interfaces.RequestDto;
import com.joaowudarski.usecase.CreateMediaRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/create-media")
@RequiredArgsConstructor
public class CreateMediaEndpoint {

    private final CreateMediaRegister createMediaRegister;

    @SecurityToken(permissionLevel = "USER")
    @PostMapping("/{mediaName}")
    public ResponseEntity<String> create(@PathVariable String mediaName, @RequestBody String mediaObject) {
        try {
            Class<?> type = MediaType.findByName(mediaName).map(MediaType::getDtoInput).orElseThrow();
            RequestDto parsedObject = (RequestDto) new ObjectMapper().readValue(mediaObject, type);
            return ResponseEntity.ok(createMediaRegister.execute(parsedObject.toEntity()));
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(String.format("Erro ao converter json: %s", e.getMessage()));
        }
    }
}
