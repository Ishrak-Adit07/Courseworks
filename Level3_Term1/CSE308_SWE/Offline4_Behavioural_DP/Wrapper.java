import java.io.Serializable;

public class Wrapper implements Serializable, Cloneable{
    
    public String message;
    
    public void setMessage(String m) { message = m; }
    public String getMessage() { return message; }
    
    public Object clone() throws CloneNotSupportedException{  
        return super.clone();
    }  
}