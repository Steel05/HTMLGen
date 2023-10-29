import HTML.HtmlFile;
import HTML.Tags;
import HTML.Components.Component;
import Networking.ConnectionChecker;
import Utils.Lipsum;

public class Generator {
    private class Constants{
        public static String marshmallowURL = "https://dx1wqezomiae6.cloudfront.net/variants/wzd8a9i4przogrryn087k1kpu02y/bf2772c50628dda0b54b7611a6af4379aeccab0091d206e804a03175e7331d7c?response-content-disposition=inline%3B%20filename%3D%22marshmallow-flavor-cotton-candy-base.jpg%22%3B%20filename%2A%3DUTF-8%27%27marshmallow-flavor-cotton-candy-base.jpg&response-content-type=image%2Fjpeg&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIPJX7DQABALCI4GQ%2F20231024%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20231024T230352Z&X-Amz-Expires=300&X-Amz-SignedHeaders=host&X-Amz-Signature=be892911067d3130b7abefca15b91ce108e98a301f39b7b8e104479f11623858";
    }

    public static void main(String[] args){
        HtmlFile file = new HtmlFile();
        Component baseComp = file.getBodyComponent();

        baseComp.addChildren(Tags.h1("Sample Output"), Tags.hr(), Tags.h2("Hello World!"), Tags.hr());

        String[] lipsum = Lipsum.getText(3);
        for (String para : lipsum){
            baseComp.addChildren(Tags.p(para), Tags.hr());
        }

        baseComp.addChildren(Tags.h3("Marshmallows!"), Tags.img(Constants.marshmallowURL));

        file.save("index.html");
        ConnectionChecker.disposeAll();
    }
}
