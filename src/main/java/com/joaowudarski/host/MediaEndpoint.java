package com.joaowudarski.host;

import com.br.jvcw.annotation.SecurityToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaowudarski.exception.InvalidTypeException;
import com.joaowudarski.media.AbstractMedia;
import com.joaowudarski.media.MediaType;
import com.joaowudarski.media.request.interfaces.RequestDto;
import com.joaowudarski.media.response.interfaces.ResponseDto;
import com.joaowudarski.usecase.CreateMediaRegister;
import com.joaowudarski.usecase.DeleteMediaRegister;
import com.joaowudarski.usecase.SearchMedia;
import com.joaowudarski.usecase.UpdateMediaRegister;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaEndpoint {

    private static final String MEDIA_NOT_FOUND_MESSAGE = "There is no media with name %s";
    private static final String MEDIA_ID_NOT_FOUND_MESSAGE = "Media not found with id: %s";
    private static final String JSON_CONVERSION_ERROR_MESSAGE = "Error when convert json: %s";

    private final CreateMediaRegister createMediaRegister;
    private final SearchMedia searchMedia;
    private final UpdateMediaRegister updateMediaRegister;
    private final DeleteMediaRegister deleteMediaRegister;

    @PostMapping("/{mediaName}")
    public ResponseEntity<String> create(@PathVariable String mediaName, @RequestBody String mediaObject) {
        try {
            Class<?> type = MediaType.findByName(mediaName).map(MediaType::getDtoInput)
                    .orElseThrow(() -> new InvalidTypeException(String.format(MEDIA_NOT_FOUND_MESSAGE, mediaName)));
            RequestDto parsedObject = (RequestDto) new ObjectMapper().readValue(mediaObject, type);
            return ResponseEntity.ok(createMediaRegister.execute(parsedObject.toEntity()));
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(String.format(JSON_CONVERSION_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @PutMapping("/{mediaName}/{mediaId}")
    @SecurityToken(permissionLevel = "USER")
    public ResponseEntity<String> modify(@PathVariable String mediaName, @PathVariable String mediaId, @RequestBody String mediaObject) {
        try {
            MediaType mediaType = MediaType.findByName(mediaName).orElseThrow(() ->
                    new InvalidTypeException(String.format(MEDIA_NOT_FOUND_MESSAGE, mediaName)));
            AbstractMedia mediaBd = searchMedia.byId(mediaType, mediaId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format(MEDIA_ID_NOT_FOUND_MESSAGE, mediaId)));

            RequestDto mediaNew = (RequestDto) new ObjectMapper().readValue(mediaObject, mediaType.getDtoInput());
            return ResponseEntity.ok(updateMediaRegister.execute(mediaBd, mediaNew.toEntity()));
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(String.format(JSON_CONVERSION_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @DeleteMapping("/{mediaName}/{mediaId}")
    @SecurityToken(permissionLevel = "USER")
    public ResponseEntity<Void> delete(@PathVariable String mediaName, @PathVariable String mediaId) {
        MediaType mediaType = MediaType.findByName(mediaName).orElseThrow(() ->
                new InvalidTypeException(String.format(MEDIA_NOT_FOUND_MESSAGE, mediaName)));
        
        deleteMediaRegister.execute(mediaType, mediaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{mediaName}/{mediaId}")
    public ResponseEntity<ResponseDto> getById(@PathVariable String mediaName, @PathVariable String mediaId) {
        MediaType mediaType = MediaType.findByName(mediaName).orElseThrow(() ->
                new InvalidTypeException(String.format(MEDIA_NOT_FOUND_MESSAGE, mediaName)));
        
        AbstractMedia media = searchMedia.byId(mediaType, mediaId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MEDIA_ID_NOT_FOUND_MESSAGE, mediaId)));

        return ResponseEntity.ok(mediaType.getNewOutputInstance().byEntity(media));
    }
}
