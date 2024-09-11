import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Drive implements IComponent{

    protected String name;
    protected float size;
    protected String type;
    protected String directory;
    protected int component_count;
    protected LocalDateTime creation_time;
    
    protected ArrayList<IComponent> listOfComponents;

    Drive(String Name){
        name = Name+":\\";
        type = "Drive";
        size = 0;
        component_count = 0;
        creation_time =  LocalDateTime.now();

        listOfComponents = new ArrayList<IComponent>();
    }

    public void setName(String Name) { name = Name; }
    public String getName() { return name; }

    public void setSize(float Size) { size = Size; }
    public float getSize() { return size; }
    public void addToSize(float Size) { size += Size; }
    public void subFromSize(float Size) { size = Size; }

    public void setType(String Type) { type = Type; }
    public String getType() { return type; }

    public void setDirectory(String Directory) { directory = Directory; }
    public String getDirectory() { return directory; }

    public void setCreationTime(LocalDateTime CTime) { creation_time = CTime; }
    public LocalDateTime getCreationTime() { return creation_time; }

    public void setComponentCount(int Count) { component_count = Count; }
    public int getComponentCount() { return component_count; }

    public void setListOfComponents(ArrayList<IComponent> List) { listOfComponents = List; }
    public ArrayList<IComponent> getListOfComponents() { return listOfComponents; }

    public void showDetails(){
        System.out.println("\nName: "+name);
        System.out.println("Type: "+type);
        System.out.println("Size: "+size+" kB");
        System.out.println("Directory: "+directory);
        System.out.println("Component Count: "+component_count);
        System.out.println("Creation time: "+creation_time);
    }

    public void showListOfComponents(){
        for(IComponent component : listOfComponents){
            System.out.print(component.getName()+"    ");
            System.out.print(component.getSize()+" kB   ");
            System.out.println(component.getCreationTime());
        }
    }

    public void showDetailsForComponent(String Name){
        for(IComponent component : listOfComponents){
            if(component.getName().equalsIgnoreCase(Name)){
                component.showDetails();
                return;
            }
        }
        System.out.println("No such file, folder or directory found");
        return;
    }

    public void addToComponentList(IComponent component){
        listOfComponents.add(component);
        this.setComponentCount(listOfComponents.size());
        this.addToSize(component.getSize());

        component.setDirectory(this.getDirectory()+component.getName()+"\\");
    }

    public IComponent changeDirectory(String newDirectoryName){

        for(IComponent component : this.getListOfComponents()){
            if(component.getName().equalsIgnoreCase(newDirectoryName)){

                if(component.getType().equalsIgnoreCase("Drive") || component.getType().equalsIgnoreCase("Folder")){
                    return component;
                }
                else if(component.getType().equalsIgnoreCase("File")){
                    System.out.println("You cannot change directory to a file");
                    return null;
                }
            }
        }

        System.out.println("No such drive or folder found");
        return null;
    }

    public Boolean delete(String Name){
        for(IComponent component : listOfComponents){
            if(component.getName().equalsIgnoreCase(Name)){
                if(component.getType().equalsIgnoreCase("file")){

                    listOfComponents.remove(component);
                    this.setComponentCount(listOfComponents.size());
                    subFromSize(component.getSize());

                    return true;
                }

                else if(component.getType().equalsIgnoreCase("folder") || component.getType().equalsIgnoreCase("drive")){
                    if(component.getComponentCount() == 0){

                        listOfComponents.remove(component);
                        this.setComponentCount(listOfComponents.size());
                        subFromSize(component.getSize());

                        return true;
                    }
                    else{
                        System.out.println("Cannot delete file or folder with children components.");
                        return false;
                    }
                }
            }
        }

        System.out.println("No such file, folder or directory found.");
        return false;
    }
    
    public Boolean recursiveDelete(String Name){

        Iterator<IComponent> iterator = listOfComponents.iterator();
    
        while (iterator.hasNext()) {
            IComponent component = iterator.next();
            if(component.getName().equalsIgnoreCase(Name)){
    
                if(component.getType().equalsIgnoreCase("file")){
                    System.out.println("Warning for recursive delete of a file");
                    iterator.remove();  // Safely remove the current file
                    this.setComponentCount(listOfComponents.size());
                    subFromSize(component.getSize());
    
                    return true;
                }
    
                else if(component.getType().equalsIgnoreCase("folder") || component.getType().equalsIgnoreCase("drive")){
                    if(component.getComponentCount() > 0){
    
                        ArrayList<IComponent> listOfChildComponents = new ArrayList<>(component.getListOfComponents());
                        Iterator<IComponent> childIterator = listOfChildComponents.iterator();
    
                        while (childIterator.hasNext()) {
                            IComponent childComponent = childIterator.next();
                            component.recursiveDelete(childComponent.getName());
                        }
                    }
    
                    iterator.remove();  // Safely remove the current folder/drive
                    this.setComponentCount(listOfComponents.size());
                    subFromSize(component.getSize());
                    return true;
                }
            }
        }
    
        System.out.println("No such file, folder or directory found.");
        return false;
    }
    
}