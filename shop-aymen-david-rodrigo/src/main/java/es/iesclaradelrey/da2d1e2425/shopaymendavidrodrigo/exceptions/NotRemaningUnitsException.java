package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NotRemaningUnitsException extends RuntimeException{

    public NotRemaningUnitsException(String msg){
        super(msg);
    }

}
