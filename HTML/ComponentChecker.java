package HTML;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import HTML.Components.Component;

/**
 * Manages all components, checks for free
 */
public class ComponentChecker {
    private static ArrayList<Component> components = new ArrayList<>();

    /**
     * Registers a component with the checker.
     * @param component
     */
    public static void registerComponent(Component component){
        components.add(component);
    }

    /**
     * Checks to see if any components are free. If any are found, a window will appear informing the user which
     * components do not have parents. The window will ask the user if they would like to save the file anyway.
     * <p> If no free components were found, this function will return true.
     * @return If the user would like to continue with saving the file
     */
    public static ArrayList<Component> checkForFreeComponents(){
        ArrayList<Component> freeComponents = new ArrayList<>();

        for (Component component : components){
            if (!component.hasParent()){
                freeComponents.add(component);
            }
        }

        return freeComponents;
    }

    public static boolean clearToSave(){
        ArrayList<Component> free = checkForFreeComponents();

        if (free.isEmpty()){
            return true;
        }

        StringBuilder content = new StringBuilder();
        content.append(String.format("%d free component%s found:\n", free.size(), free.size() == 1 ? "" : "s"));
        for (Component comp : free){
            content.append(comp.getTag() + " component\n");
        }
        content.append("\nWould you like to save anyway?");

        int response = JOptionPane.showConfirmDialog(null, content.toString(), 
            "Free components found", JOptionPane.YES_NO_OPTION);

        return response == JOptionPane.YES_OPTION;
    }
}
