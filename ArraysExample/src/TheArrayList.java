import java.util.ArrayList;

public class TheArrayList
{
    public static void main(String[] args)
    {
        ArrayList<String> names = new ArrayList<>();

        names.add("James");
        names.add("Peter");
        names.add("John");
        names.add("Jake");
        names.add(3, "Paul");
        names.set(1, "Peters");
        names.remove(0);

        for (int i = 0; i < names.size(); i++)
        {
            System.out.println(names.get(i));
        }
    }
}
