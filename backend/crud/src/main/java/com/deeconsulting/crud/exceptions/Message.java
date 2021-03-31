package com.deeconsulting.crud.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

	private String menssage;
	
	public Message(String menssage) {
        this.menssage = menssage;
    }

}
