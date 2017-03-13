package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    static TrainSensor trainSensor;
    static TrainController trainController;
    static TrainUser trainUser;

    @BeforeClass
    public static void init() {
        trainController = mock(TrainController.class);
        trainUser = mock(TrainUser.class);

        trainSensor = new TrainSensorImpl(trainController, trainUser);
    }

    @Test
    public void overrideSpeedLimit_BelowZero_UserAlarmTrue() {
        trainSensor.overrideSpeedLimit(-2);
        verify(trainUser, times(1)).setAlarmState(true);
        reset(trainUser);
    }

    @Test
    public void overrideSpeedLimit_Above500_UserAlarmTrue() {
        trainSensor.overrideSpeedLimit(600);
        verify(trainUser, times(1)).setAlarmState(true);
        reset(trainUser);
    }

    @Test
    public void overrideSpeedLimit_HighDiff_UserAlarmTrue() {
        trainSensor.overrideSpeedLimit(400);
        verify(trainUser, times(1)).setAlarmState(true);
        reset(trainUser);
    }

    @Test
    public void overrideSpeedLimit_NoDiff_UserAlarmFalse() {
        trainSensor.overrideSpeedLimit(0);
        verify(trainUser, times(0)).setAlarmState(true);
        reset(trainUser);
    }
}
