package test.ua.nure.koren.summaryTask4.web.command;

import org.junit.Test;
import ua.nure.koren.summaryTask4.web.command.AbstractCommand;
import ua.nure.koren.summaryTask4.web.command.ChangeTourCommand;
import ua.nure.koren.summaryTask4.web.command.CommandContainer;
import ua.nure.koren.summaryTask4.web.command.NoCommand;

import static org.junit.Assert.assertTrue;

public class CommandContainerTest {

    @Test
    public void get() {
        //given
        String name = "changeTour";

        //when
        AbstractCommand abstractCommand = CommandContainer.get(name);

        //then
        assertTrue(abstractCommand instanceof ChangeTourCommand);
    }

    @Test
    public void getCommandNameIsNull() {

        //when
        AbstractCommand abstractCommand = CommandContainer.get(null);

        //then
        assertTrue(abstractCommand instanceof NoCommand);
    }

    @Test
    public void getCommandNotExist() {
        //given
        String name = "wrongName";

        //when
        AbstractCommand abstractCommand = CommandContainer.get(name);

        //then
        assertTrue(abstractCommand instanceof NoCommand);
    }
}