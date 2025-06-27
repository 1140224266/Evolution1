package temp.sequenceSumon;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.Arrays;

public class GsonTest {
    public static void main(String[] args)
    {
        JsonArray strength_value_range = new JsonArray();
        strength_value_range.add(Arrays.toString(new int[]{
                0, 100
        }));

    }
}
