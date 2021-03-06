package edu.hawaii.its.casdemo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import edu.hawaii.its.casdemo.type.ActionLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@DirtiesContext
public class ActionServiceSystemTest {

    @Autowired
    private ActionService actionService;

    @Test
    public void construction() {
        assertNotNull(actionService);
    }

    @Test
    @Transactional
    public void record() {
        int count = actionService.logCount();

        final int RUNS = 10;
        for (int i = 0; i < RUNS; i++) {
            ActionLog actionLog = new ActionLog();
            actionLog.setActionId(Long.valueOf(10 + (i % 3)));
            actionLog.setUserUhuuid(Long.valueOf(89999999));
            actionLog.setViewUhuuid(Long.valueOf(89999999));

            actionService.record(actionLog);
        }

        assertEquals(count + RUNS, actionService.logCount());
    }

}
