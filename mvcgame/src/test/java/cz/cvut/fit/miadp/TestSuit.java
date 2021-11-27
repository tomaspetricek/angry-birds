package cz.cvut.fit.miadp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        EducativeTestCase.class,
        EducativeTestCaseMock.class,
        ToggleMovingStrategyTestCase.class,
        ChangeCannonPowerTestCase.class,
        CreateGameInfoMock.class,
})

public class TestSuit {

}
