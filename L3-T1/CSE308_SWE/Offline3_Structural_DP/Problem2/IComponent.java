import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IComponent {

    public void setName(String Name);
    public String getName();

    public void setSize(float Size);
    public float getSize();
    public void addToSize(float Size);
    public void subFromSize(float Size);

    public void setType(String Type);
    public String getType();

    public void setDirectory(String Directory);
    public String getDirectory();

    public void setCreationTime(LocalDateTime CTime);
    public LocalDateTime getCreationTime();

    public void setComponentCount(int Count);
    public int getComponentCount();

    public void setListOfComponents(ArrayList<IComponent> List);
    public ArrayList<IComponent> getListOfComponents();

    public void showDetails();
    public void showDetailsForComponent(String Name);
    public void showListOfComponents();
    public void addToComponentList(IComponent component);
    public IComponent changeDirectory(String newDirectoryName);
    public Boolean delete(String Name);
    public Boolean recursiveDelete(String Name);
    
}