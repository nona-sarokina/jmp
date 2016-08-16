package module6.task9.cdi.input;

import module3.task1.beans.Person;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.io.IOException;

@Decorator
public class PersonInputStreamDecorator implements IPersonInputStream {
    @Any
    @Inject
    @Delegate
    protected IPersonInputStream stream;

    @Override
    public Person readPerson(String name) {
        System.out.println("Input decorator checks first letter for name: " + name);
        if (!Character.isLowerCase(name.charAt(0))) {
            final String firstSymbol = name.substring(0, 1);
            name = name.replaceFirst(firstSymbol, firstSymbol.toLowerCase());
            System.out.println("Name updated to " + name);
        }

        return stream.readPerson(name);
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }
}
