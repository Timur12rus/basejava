import model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, RuntimeException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field declaredField = r.getClass().getDeclaredFields()[0];
        declaredField.setAccessible(true);
        System.out.println(declaredField.getName());
        System.out.println(declaredField.get(r));
        declaredField.set(r, "new uuid");

        System.out.println(r);
        //invoke r.toString via reflection

        Method toString = r.getClass().getDeclaredMethod("toString");
        toString.setAccessible(true);
        System.out.println(toString.invoke(r));
    }
}
