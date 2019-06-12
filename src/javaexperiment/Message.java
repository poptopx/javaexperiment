package javaexperiment;

import java.io.Serializable;
import java.util.Vector;
public class Message implements Serializable{
	String message;
    Vector<String> content;
    public Message(String message,Vector<String> content) {
    	this.message=message;
    	this.content=content;
    }
    public Vector<String> get_content() {
    	return content;
    }
    public String get_message() {
    	return message;
    }
}
