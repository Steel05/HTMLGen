package HTML.Components;

import java.util.ArrayList;

import Exceptions.HierarchyException;
import HTML.ComponentChecker;

public abstract class Component {
    private Component parent = null;
    private ArrayList<Component> children = new ArrayList<>();

    //private String cssClass;

    private int childId;
    private String tag;

    protected Component(String tag){
        this.tag = tag;

        ComponentChecker.registerComponent(this);
    }

    /**
     * Gets the parent of this component.
     * @return The parent component
     */
    public Component getParent(){
        return parent;
    }
    /**
     * Checks if the component has a parent component.
     * @return Whether or not this component is a child
     */
    public boolean hasParent(){
        return parent != null;
    }
    /**
     * Registers this component to be a child of another component.
     * <p>NOTE: If this component already has a parent, a stack trace will be printed to the
     * console and the parent component of this component will not be changed.
     * @param parent The component for this component to become the child of
     */
    protected void registerParent(Component parent, int id){
        try{
            if (this.parent != null){
                throw new HierarchyException(this);
            }
            this.parent = parent;
            this.childId = id;
        }
        catch(HierarchyException e){
            e.printStackTrace();
        }
    }

    /**
     * Releases this component from its parent, making it a "free component". If this component
     * is already free, this function does nothing.
     * <p>If left freed, this component will get flagged when the HTML file is saved.
     */
    public void release(){
        if (parent == null){
            return;
        }
        parent.removeChild(this.childId);
        parent = null;
    }

    /**
     * Adds a child of to this component.
     * @param child The child component
     * @return This component to allow for call chaining
     */
    public Component addChild(Component child){
        children.add(child);
        childId = children.size() - 1;
        child.registerParent(this, children.size() - 1);

        return this;
    }
    /**
     * 
     * @param children The components to add
     */
    public void addChildren(Component... children){
        for (Component comp : children){
            addChild(comp);
        }
    }
    /**
     * Removes a child component.
     * @param id The id of the child to remove
     * @return The removed child component
     */
    public Component removeChild(int id){
        return children.remove(id);
    }

    /**
     * Gets the associated tag for this component.
     * @return This component's tag
     */
    public String getTag(){
        return tag;
    }

    /**
     * Converts this component into valid HTML code.
     * @return This component as HTML code
     */
    public String write(){
        String out = String.format("<%s>\n", tag);
        for (Component child : children){
            out += child.write() + "\n";
        }
        out += String.format("</%s>", tag);
        return out;
    }

    @Override
    public String toString(){
        return String.format("<%s> Component", tag);
    }
}
