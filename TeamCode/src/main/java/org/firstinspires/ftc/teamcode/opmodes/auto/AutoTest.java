package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmodes.base.RoverBase;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;

/**
 * Created by Derek on 1/16/2019.
 */
@Autonomous(name = "AutoTest4",group = "auto")
public class AutoTest extends RoverBase {
    @Override
    public void init() {
        super.init();
        A.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        B.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        C.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        D.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while (A.isBusy() || B.isBusy() || C.isBusy() || D.isBusy()) {

        }

        A.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        B.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        C.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        D.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }

    @Override
    public void loop() {
        A.setTargetPosition(1375);
        B.setTargetPosition(1375);
        C.setTargetPosition(1375);
        D.setTargetPosition(1375);

        A.setPower(0.1);
        B.setPower(0.1);
        C.setPower(0.1);
        D.setPower(0.1);
    }
}
