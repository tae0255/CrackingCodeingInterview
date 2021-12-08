import Cache.Question;
import SocialNetwork.QuestionA;
import SocialNetwork.QuestionB;
import SocialNetwork.Tester;

public class Main {
    public static void main(String[] args){

        System.out.println("** 소셜 네트워크 **");
        QuestionA.main(new String[]{});
        QuestionB.main(new String[]{});
        //Tester.main(new String[]{});

        System.out.println("** 캐시 **");
        Question.main(new String[]{});

    }
}
