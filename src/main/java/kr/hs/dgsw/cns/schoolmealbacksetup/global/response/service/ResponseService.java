package kr.hs.dgsw.cns.schoolmealbacksetup.global.response.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public <T> ResponseEntity<T> getResponse(T data) {
        return getResponse(data, HttpStatus.OK);
    }

    public <T> ResponseEntity<T> getResponse(T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(data, httpStatus);
    }

    public ResponseEntity<?> getCommonResponse(HttpStatus httpStatus) {
        return new ResponseEntity<>(httpStatus);
    }

    public ResponseEntity<String> getResponseWithHeader(String data, String headerKey, String headerValue, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(headerKey, headerValue);
        return new ResponseEntity<>(data, headers, httpStatus);
    }

}
