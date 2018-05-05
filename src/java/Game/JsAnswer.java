/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;


public class JsAnswer {
    
    public boolean returnBoolValue;
    public Exception exception;
    
    public JsAnswer(Boolean bool, Exception exc) {
        returnBoolValue = bool;
        exception = exc;
    }
}
