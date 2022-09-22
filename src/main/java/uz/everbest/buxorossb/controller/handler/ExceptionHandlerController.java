package uz.everbest.buxorossb.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity handleExeption(ResponseStatusException e){
        e.printStackTrace();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", e.getStatus());
        body.put("error", e.getReason());
        body.put("message", e.getMessage());

        return ResponseEntity
                .status(e.getStatus())
                .headers(e.getResponseHeaders())
                .body(body);
    }

}
