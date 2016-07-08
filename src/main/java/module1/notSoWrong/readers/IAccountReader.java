package module1.notSoWrong.readers;

import module1.notSoWrong.account.IAccountOperator;

import java.util.List;

/**
 * Created by user on 08.07.2016.
 */
public interface IAccountReader {
    List<IAccountOperator> read();

}
