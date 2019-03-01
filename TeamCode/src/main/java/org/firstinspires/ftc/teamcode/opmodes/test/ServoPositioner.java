package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.opmodes.base.RoverBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Derek on 2/28/2019.
 */

@TeleOp(name = "ServoPositioner", group = "test")
public class ServoPositioner extends RoverBase {
    List<Servo> list = new ArrayList<>();

    int i = 0;
    double position = 0;

    @Override
    public void init() {
        list.clear();

        super.init();
        //i WISH we had java 8 :(
        //hardwareMap.servo.iterator().forEachRemaining(list::add);
        Iterator<Servo> iterator = hardwareMap.servo.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

    }

    @Override
    public void loop() {
        super.loop();
        if(gamepadWrapper1.DPAD_RIGHT.isPressed()) {
            i+=1;
            if (i > list.size() - 1) i = 0;
        }

        if(gamepadWrapper1.DPAD_LEFT.isPressed()) {
            i-=1;
            if (i < 0) i = list.size() - 1;
        }

        telemetry.addData("selected",list.get(i).getConnectionInfo());
        telemetry.addData("position",position);

        if (gamepadWrapper1.X.isPressed()) {
            position = 0;
        }

        if (gamepadWrapper1.Y.isPressed()) {
            position = 0.5;
        }

        if (gamepadWrapper1.B.isPressed()) {
            position = 1;
        }

        list.get(i).setPosition(position);
    }
}
