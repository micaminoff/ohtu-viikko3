package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "011120775";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        url = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        bodyText = Request.Get(url).execute().returnContent().asString();
        Course c = mapper.fromJson(bodyText, Course.class);
        
        url = "https://studies.cs.helsinki.fi/ohtustats/stats";
        String statsResponse = Request.Get(url).execute().returnContent().asString();
        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();
        Stat sta = mapper.fromJson(parsittuData.get("1"), Stat.class);
        
        System.out.println("Kurssi: " + c.toString());
        System.out.println("StudentID: " + studentNr);
        int totalE = 0;
        int totalH = 0;
        
        for (Submission s : subs) {
            totalE += s.getExercises().size();
            totalH += s.getHours();
            System.out.println("Week: " + s.getWeek());
            System.out.println("  Exercises: " + s.getExercises().size() +
                    "(max " + c.max_ex().get(s.getWeek() - 1) + ")" + 
                    ", Duration: " + s.getHours() + "h, Exercises done: " + s.getExercises());
        }
        System.out.println("Total: " + totalE + " exercises done in " + totalH + " hours");
        System.out.println("");
        System.out.println("Course totals: \n  students: " + sta.getStudents() + 
                "\n  Exercises: " + sta.getExes() + "\n  Hours: " + sta.getHours());
    }
}