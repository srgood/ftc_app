package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmodes.base.RoverBase;

/**
 * Created by Derek on 2/28/2019.
 */

@Autonomous(name = "gamepadWrapperTest",group = "auto")
public class GamepadWrapperTest extends RoverBase {

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        super.loop();
        telemetry.addData("gamepadW1.a.state",gamepadWrapper1.A.getState().name());
        telemetry.addData("gamepadW2.a.state",gamepadWrapper2.A.getState().name());
    }
}
