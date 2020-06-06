package Process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Work {

    public String Work() {
        try
        {
            String s=null;
            //Running the python script
            Process p=Runtime.getRuntime().exec("C:\\Users\\scara\\IdeaProjects\\FinalProject\\tensorflow-demo\\my_env\\Scripts\\python.exe C:\\Users\\scara\\IdeaProjects\\FinalProject\\tensorflow-demo\\my_env\\main.py");
            BufferedReader in=new BufferedReader(new InputStreamReader(p.getInputStream()));
            //Printing the predicted number
            while ((s=in.readLine())!=null)
            {
                if(s.contains("Prediction for test image:"))
                {
                    System.out.println(s.charAt(27));
                    if(s.charAt(27)==1)
                    {
                        return "C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\1.jpg";
                    }
                    else if(s.charAt(27)=='0')
                    {
                       return "C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\0.jpg";
                    }
                    else if(s.charAt(27)=='3')
                    {
                        return "C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\3.jpg";
                    }
                    else if(s.charAt(27)=='2')
                    {
                        return "C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\2.jpg";
                    }
                    else if(s.charAt(27)=='4')
                    {
                        return "C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\4.jpeg";
                    }
                    else if(s.charAt(27)=='5')
                    {
                        return "C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\5.jpg";
                    }
                    else if(s.charAt(27)=='6')
                    {
                        return "C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\6.jpg";
                    }
                    else if(s.charAt(27)=='7')
                    {
                        return "C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\7.jpg";
                    }
                    else if(s.charAt(27)=='8')
                    {
                        return "C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\8.png";
                    }
                    else if(s.charAt(27)=='9')
                    {
                        return "C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\9.jpg";
                    }
                }
            }
            return "C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\Error.jpg";
        }
        catch (IOException e)
        {
            return "C:\\Users\\scara\\IdeaProjects\\FinalProject\\Pictures\\Error.jpg";
        }

    }
}
