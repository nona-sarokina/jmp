package module6.task9.cdi.output;

import module3.task1.beans.Person;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.io.IOException;

@Decorator
public class PersonOutputStreamDecorator implements IPersonOutputStream {
    @Any
    @Inject
    @Delegate
    protected IPersonOutputStream stream;

    @Override
    public void writePerson(Person person) {
        String name = person.getName();
        System.out.println("Output decorator checks first letter for name: " + name);
        if (!Character.isUpperCase(name.charAt(0))){
            final String firstSymbol = name.substring(0, 1);
            name = name.replaceFirst(firstSymbol, firstSymbol.toUpperCase());
            System.out.println("Name updated to " + name);
        }
        stream.writePerson(person);
    }
    @Override
    public void close() throws IOException {
        if (null != stream) {
            stream.close();
        }
    }

}
