package module1.wrong.streamOperators;

import module1.wrong.account.AccountOperator;

import java.util.List;

/**
 * Created by user on 08.07.2016.
 */
//interface segregation breaking
// let's imagine that only console reader should validate stream data some how.
// And there is a bit more such methods that can be used only by a little part of implementations.
// so. it's better to divide some functionality between more new interfaces and let only required classes implement it.
// as per this code is related to YAGNI principle I will describe what I would do if this functionality have to be done -
// I will create a new interface IStreamValidator and ConsoleReader class declaration will look like
// public class ConsoleReader implements IStreamOperator, IStreamValidator {
// other classes will stay without changes - only removing not anymore necessary method realisation.

public interface IStreamOperator {
    List<AccountOperator> doStreamOperation();
    boolean validateStreamData();


}
