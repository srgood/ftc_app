package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.*;

/**
 * Created by Derek on 11/27/2018.
 */
@TeleOp(name = "helphelphelp", group = "test")
public class newStyleMeccanumOp extends OpMode {
//    1  2
//    3  4

    private DcMotor frontLeft,frontRight,backLeft,backRight, frontWorms, backWorms;
    private float coef = 1;

    @Override
    public void init() {
        frontLeft  = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft   = hardwareMap.dcMotor.get("backLeft");
        backRight  = hardwareMap.dcMotor.get("backRight");

        frontWorms = hardwareMap.dcMotor.get("frontWorms");
        backWorms = hardwareMap.dcMotor.get("backWorms");



        frontWorms.setZeroPowerBehavior(BRAKE);
        backWorms.setZeroPowerBehavior(BRAKE);
    }

    @Override
    public void loop() {
        calcVolts(gamepad1.left_stick_y);
    }

    private void calcVolts(double speed) {
        frontWorms.setPower(-speed);
        backWorms.setPower(-speed);
        frontLeft.setPower(-speed * coef);
        frontRight.setPower(-speed * coef);
        backLeft.setPower(speed * coef);
        backRight.setPower(speed * coef);


    }
}
