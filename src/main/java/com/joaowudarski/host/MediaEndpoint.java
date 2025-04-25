package com.joaowudarski.host;

import com.br.jvcw.annotation.SecurityToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaowudarski.media.AbstractMedia;
import com.joaowudarski.media.MediaType;
import com.joaowudarski.media.request.interfaces.RequestDto;
import com.joaowudarski.usecase.CreateMediaRegister;
import com.joaowudarski.usecase.SearchMedia;
import com.joaowudarski.usecase.UpdateMediaRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaEndpoint {

    private final CreateMediaRegister createMediaRegister;
    private final SearchMedia searchMedia;
    private final UpdateMediaRegister updateMediaRegister;

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

    @PutMapping("/{mediaName}/{mediaId}")
    @SecurityToken(permissionLevel = "USER")
    public ResponseEntity<String> modify(@PathVariable String mediaName, @PathVariable String mediaId, @RequestBody String mediaObject) {
        try {
            MediaType mediaType = MediaType.findByName(mediaName).orElseThrow();
            AbstractMedia mediaBd = searchMedia.byId(mediaType, mediaId).orElseThrow();

            RequestDto mediaNew = (RequestDto) new ObjectMapper().readValue(mediaObject, mediaType.getDtoInput());
            return ResponseEntity.ok(updateMediaRegister.execute(mediaBd, mediaNew.toEntity()));
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(String.format("Erro ao converter json: %s", e.getMessage()));
        }
    }
}
