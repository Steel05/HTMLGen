import HTML.HtmlFile;
import HTML.Tags;
import HTML.Components.Component;
import Networking.ConnectionChecker;
import Utils.Lipsum;

public class Generator {
    public static void main(String[] args){
        HtmlFile file = new HtmlFile();
        Component baseComp = file.getBodyComponent();

        baseComp.addChildren(Tags.h1("Sample Output"), Tags.hr(), Tags.h2("Hello World!"), Tags.hr());

        String[] lipsum = Lipsum.getText(3);
        for (String para : lipsum){
            baseComp.addChild(Tags.p(para));
        }


        file.save("index.html");
        ConnectionChecker.disposeAll();
    }
}
