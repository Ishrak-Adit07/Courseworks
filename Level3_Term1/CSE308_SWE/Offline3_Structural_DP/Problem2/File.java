import java.time.LocalDateTime;
import java.util.ArrayList;

public class File implements IComponent{

    protected String name;
    protected float size;
    protected String type;
    protected String directory;
    protected int component_count;
    protected LocalDateTime creation_time;
    protected ArrayList<IComponent> listOfComponents;
    
    File(String Name, float Size){
        name = Name;
        type = "File";
        size = Size;
        component_count = 0;
        creation_time =  LocalDateTime.now();
        listOfComponents = null;
    }

    public void setName(String Name) { name = Name; }
    public String getName() { return name; }

    public void setSize(float Size) { size = Size; }
    public float getSize() { return size; }
    public void addToSize(float Size) {}
    public void subFromSize(float Size) {}

    public void setType(String Type) { type = Type; }
    public String getType() { return type; }

    public void setDirectory(String Directory) { directory = Directory; }
    public String getDirectory() { return directory; }

    public void setCreationTime(LocalDateTime CTime) { creation_time = CTime; }
    public LocalDateTime getCreationTime() { return creation_time; }

    public void setComponentCount(int Count) { component_count = Count; }
    public int getComponentCount() { return component_count; }

    public void setListOfComponents(ArrayList<IComponent> List) {}
    public ArrayList<IComponent> getListOfComponents() { return null; }

    public void showDetails(){
        System.out.println("\nName: "+name);
        System.out.println("Type: "+type);
        System.out.println("Size: "+size+" kB");
        System.out.println("Directory: "+directory);
        System.out.println("Component Count: "+component_count);
        System.out.println("Creation time: "+creation_time);
    }

    public void showDetailsForComponent(String Name){
        //No operation for file
    }

    public void showListOfComponents(){
        //No operation for file
    }

    public void addToComponentList(IComponent component){
        //No operation for file
    }

    public IComponent changeDirectory(String newDirectoryName){
        //No operation for file
        return null;
    }

    public Boolean delete(String Name){
        //No operation for file
        return false;
    }
    public Boolean recursiveDelete(String Name){
        //No operation for file
        return false;
    }
    
}