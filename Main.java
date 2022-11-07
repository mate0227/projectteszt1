import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    public static int picIndex(List<String> list,String lookedFor)
    {
        int result = -1;
        for(int i = 0;i<list.size();i++)
        {
            if(list.get(i) == lookedFor)
            {
                result = i;
            }
        }
        if(result >=0)
        {
            return result;
        }
        else
        {
            return -1;
        }
    }

    public static String prevPic(List<String> list,String lookedFor)
    {
        int picIndex = picIndex(list,lookedFor)-1;
        if(picIndex>0)
        {
            return list.get(picIndex);
        }
        else
        {
            return list.get(picIndex+1);
        }
    }
    public static String nextPic(List<String> list,String lookedFor)
    {

        int picIndex2 = picIndex(list,lookedFor);


        if(picIndex2<list.size()-1)
        {
            return list.get(picIndex2+1);
        }
        else
        {
            return list.get(picIndex2);
        }
    }


    public static boolean isPic(File file)
    {
        if(file.getName().toLowerCase().endsWith(".jpg") || file.getName().toLowerCase().endsWith(".jpeg") || file.getName().toLowerCase().endsWith(".png"))
        {
            return true;
        }
        return false;
    }

    public static List<String> directoryList = new ArrayList<>();
    public static List<String> picList = new ArrayList<>();
    public static int picCounter = 0;
    public static int directoryCounter = 0;

    public static void showFilesmini(File[] files) {

        for (File file : files) {
            if (file.isDirectory()) {
                directoryList.add(file.getName());
                directoryCounter++;
                //System.out.println("Directory: " + file.getAbsolutePath());
                showFilesmini(file.listFiles()); // Meghívja önmagát.
            } else if(isPic(file)) {
                System.out.println("Kép: " + file.getName());
                picList.add(file.getName());
                picCounter++;
            }
        }
    }
    public static void showFiles(File[] files) {
        /*
        List<String> directoryList = new ArrayList<>();
        List<String> picList = new ArrayList<>();
        int picCounter = 0;
        int directoryCounter = 0;
        */
        showFilesmini(files);


        for (File file : files) {
            if (file.isDirectory()) {
                //directoryList.add(file.getName());
                //directoryCounter++;
                System.out.println("Directory: " + file.getAbsolutePath());
                showFiles(file.listFiles()); // Meghívja önmagát.
            } else if(isPic(file)) {
                //System.out.println("Kép: " + file.getName());
                //picList.add(file.getName());
                //picCounter++;



                try
                        (PrintStream out = new PrintStream(new FileOutputStream(file.toPath()  + ".html")))
                {
                    out.print("<html>" +
                            "<body>" +
                            "<div class =\"container\">"+
                            "<h2 class = \"heading\">"+file.getName()+  "</h2>"+
                            "<a href=\""+picList.get(picIndex(picList,file.getName())+1)+".html\">"+
                            "<img src=\"" + file.getName() + "\"  width=\"500\" height=\"600\">" +
                            "</a>"+
                            "</div>"+
                            "</body>" +
                            "</html>");
                }

                catch (FileNotFoundException ex)
                {
                    // insert code to run when exception occurs
                }

            }
        }
    }

    public static void main(String[] args)
    {

        File InputFiles = new File(args[0]);

        if(!InputFiles.isDirectory())
        {
            System.err.println("rossz a bemenet.");
            System.exit(1);
        }


        showFiles(InputFiles.listFiles());


        System.out.println("Hello world!");
    }
}